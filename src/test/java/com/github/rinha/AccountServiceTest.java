package com.github.rinha;

import com.github.rinha.domain.Customer;
import com.github.rinha.domain.dtos.CustomerDTO;
import com.github.rinha.domain.dtos.StatementDTO;
import com.github.rinha.domain.dtos.TransactionResponse;
import com.github.rinha.domain.exceptions.UnprocessableException;
import com.github.rinha.persistence.AccountPersistence;
import com.github.rinha.services.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DisplayName("Account Service Unit Test")
@ContextConfiguration(classes = {AccountService.class})
@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountPersistence accountPersistence;

    @Test
    void shouldBeFindStatementByCustomerId() {
        // Given
        when(this.accountPersistence.findCustomerById(anyInt())).thenReturn(new Customer(1, 10, 10));
        when(this.accountPersistence.findTransactionsByCustomerId(anyInt())).thenReturn(List.of(new TransactionResponse(1, "c", "test", "2024-02-19")));


        // When
        Mono<StatementDTO> statement = this.accountService.findStatementByCustomerId(1);

        // Then
        StepVerifier.create(statement)
                .expectNextMatches(s -> s.saldo().limite() == 10 && s.saldo().total() == 10 && s.ultimas_transacoes().size() == 1)
                .verifyComplete();
    }

    @Test
    void shouldBeUpdateBalanceAndInsertTransaction() {
        // Given
        when(this.accountPersistence.findCustomerForUpdate(anyInt())).thenReturn(new Customer(1, 10, 10));
        doNothing().when(this.accountPersistence).updateBalanceAndInsertTransaction(anyInt(), anyInt(), anyInt(), anyString(), anyString());

        // When
        Mono<CustomerDTO> customer = this.accountService.updateBalanceAndInsertTransaction(1, 1, "c", "test");

        // Then
        StepVerifier.create(customer)
                .expectNextMatches(c -> c.limite() == 10 && c.saldo() == 11)
                .verifyComplete();
    }

    @Test
    void shouldValidateBalanceOfCustomer() {
        // Given
        when(this.accountPersistence.findCustomerForUpdate(anyInt())).thenReturn(new Customer(1, 10, 10));
        doNothing().when(this.accountPersistence).updateBalanceAndInsertTransaction(anyInt(), anyInt(), anyInt(), anyString(), anyString());

        // When
        Mono<CustomerDTO> customer = this.accountService.updateBalanceAndInsertTransaction(1, 10000, "d", "test");

        // Then
        StepVerifier.create(customer)
                .expectErrorMatches(throwable -> throwable instanceof UnprocessableException)
                .verify();
    }

    @Test
    void shouldVerifyCustomerId() {
        // Given
        when(this.accountPersistence.existsCustomerById(anyInt())).thenReturn(true);

        // When
        Boolean isCustomerValid = this.accountService.isValidCustomerId(1);

        // Then
        assertEquals(true, isCustomerValid);
    }

    @Test
    void shouldVerifyIfIsInvalidCustomerId() {
        // Given
        when(this.accountPersistence.existsCustomerById(anyInt())).thenReturn(false);

        // When
        Boolean isCustomerValid = this.accountService.isValidCustomerId(1);

        // Then
        assertEquals(false, isCustomerValid);
    }
}
