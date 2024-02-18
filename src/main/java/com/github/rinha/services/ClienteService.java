package com.github.rinha.services;

import com.github.rinha.domain.Cliente;
import com.github.rinha.dtos.ExtratoResponse;
import com.github.rinha.dtos.TransacaoResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final JdbcTemplate jdbcTemplate;

    public ClienteService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Mono<ExtratoResponse> getExtratoByClienteId(int id) {
        Cliente cliente = jdbcTemplate.queryForObject(
                "SELECT id, limite, saldo FROM cliente WHERE id = ?",
                new Object[]{id},
                (rs, rowNum) -> new Cliente(
                        rs.getInt("id"),
                        rs.getInt("limite"),
                        rs.getInt("saldo")
                )
        );

        List<TransacaoResponse> transacoes = new ArrayList<>();

        try {
            transacoes = jdbcTemplate.query(
                    "SELECT id, valor, tipo, descricao, data FROM transacao WHERE id_cliente = ? ORDER BY data DESC LIMIT 10",
                    new Object[]{id},
                    (rs, rowNum) -> new TransacaoResponse(
                            rs.getInt("valor"),
                            rs.getString("tipo"),
                            rs.getString("descricao"),
                            rs.getTimestamp("data").toLocalDateTime().atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ"))
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
        }

        return Mono.just(new ExtratoResponse(
                new ExtratoResponse.Saldo(cliente.getSaldo(), LocalDateTime.now().atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")), cliente.getLimite()),
                transacoes));
    }

    public Mono<Cliente> updateSaldoAndInsertTransacao(int id, int valor, String tipo, String descricao) {
        Cliente cliente = jdbcTemplate.queryForObject(
                "SELECT id, limite, saldo FROM cliente WHERE id = ? FOR UPDATE",
                new Object[]{id},
                (rs, rowNum) -> new Cliente(
                        rs.getInt("id"),
                        rs.getInt("limite"),
                        rs.getInt("saldo")
                )
        );

        if (tipo.equals("d") && cliente.hasSaldoInsuficiente(valor)) {
            return Mono.error(new IllegalArgumentException("Saldo insuficiente"));
        }

        int saldo = tipo.equals("d") ? cliente.getSaldo() - valor : cliente.getSaldo() + valor;

        jdbcTemplate.update(
                "INSERT INTO transacao (id_cliente, valor, tipo, descricao, data) VALUES (?, ?, ?, ?, ?)",
                id,
                valor,
                tipo,
                descricao,
                Timestamp.valueOf(LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDateTime())
        );

        jdbcTemplate.update(
                "UPDATE cliente SET saldo = ? WHERE id = ?",
                saldo,
                id
        );

        return Mono.just(new Cliente(cliente.getLimite(), saldo));
    }
}
