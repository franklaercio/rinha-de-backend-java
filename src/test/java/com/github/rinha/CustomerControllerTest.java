package com.github.rinha;

import com.github.rinha.controllers.CustomerController;
import com.github.rinha.domain.dtos.*;
import com.github.rinha.services.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Customer Controller Unit Test")
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private CustomerController controller;

    @Test
    void shouldBeRetrieveStatement() {
        // Given
        when(accountService.isValidCustomerId(anyInt())).thenReturn(true);
        when(accountService.findStatementByCustomerId(anyInt())).thenReturn(Mono.just(new StatementDTO(new BalanceDTO(10, 10), List.of(new TransactionResponse(1, "c", "test", "2024-02-19")))));

        // When and Then
        controller.getExtratoByClienteId(123)
                .doOnNext(response -> {
                    assert response.getStatusCodeValue() == HttpStatus.OK.value();
                    verify(accountService, times(1)).isValidCustomerId(123);
                    verify(accountService, times(1)).findStatementByCustomerId(123);
                })
                .block();
    }

    @Test
    void shouldBeReturnNotFoundWhenCustomerIdIsInvalidForStatement() {
        // Given
        when(accountService.isValidCustomerId(anyInt())).thenReturn(false);

        // When and Then
        controller.getExtratoByClienteId(123)
                .doOnNext(response -> {
                    assert response.getStatusCodeValue() == HttpStatus.NOT_FOUND.value();
                })
                .block();
    }

    @Test
    void shouldBeCreateTransaction() {
        // Given
        TransactionRequest transaction = new TransactionRequest("100", "c", "test");

        when(accountService.isValidCustomerId(anyInt())).thenReturn(true);
        when(accountService.updateBalanceAndInsertTransaction(anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(Mono.just(new CustomerDTO(123, 10)));

        // When and Then
        controller.transacionar(123, transaction)
                .doOnNext(response -> {
                    assert response.getStatusCodeValue() == HttpStatus.OK.value();
                    verify(accountService, times(1)).isValidCustomerId(123);
                    verify(accountService, times(1)).updateBalanceAndInsertTransaction(123, transaction.parseValueToInt(), transaction.tipo(), transaction.descricao());
                })
                .block();
    }

    @Test
    void shouldBeReturnNotFoundWhenCustomerIdIsInvalid() {
        // Given
        TransactionRequest transaction = new TransactionRequest("100", "c", "test");

        when(accountService.isValidCustomerId(anyInt())).thenReturn(false);

        // When and Then
        controller.transacionar(123, transaction)
                .doOnNext(response -> {
                    assert response.getStatusCodeValue() == HttpStatus.NOT_FOUND.value();
                })
                .block();
    }

    @Test
    void shouldBeReturnUnprocessableEntityWhenTransactionIsInvalid() {
        // Given
        TransactionRequest transaction = new TransactionRequest("100", "e", "test");
        when(accountService.isValidCustomerId(anyInt())).thenReturn(true);

        // When and Then
        controller.transacionar(123, transaction)
                .doOnNext(response -> {
                    assert response.getStatusCodeValue() == HttpStatus.UNPROCESSABLE_ENTITY.value();
                })
                .block();
    }
}
