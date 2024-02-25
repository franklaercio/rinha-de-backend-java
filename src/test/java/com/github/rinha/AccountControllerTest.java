package com.github.rinha;

import static com.github.rinha.DomainFactory.createAccount;
import static com.github.rinha.DomainFactory.createStatement;
import static com.github.rinha.DomainFactory.createTransact;
import static com.github.rinha.DomainFactory.invalidTransact;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.rinha.controllers.AccountController;
import com.github.rinha.services.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@DisplayName("Account Controller Unit Test")
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

  @Mock
  private AccountService accountService;

  @InjectMocks
  private AccountController controller;

  @Test
  void shouldBeRetrieveStatement() {
    // Given
    when(accountService.retrieveStatement(anyInt())).thenReturn(Mono.just(createStatement()));

    // When and Then
    var response = controller.retrieveStatement(5).block();

    // Then
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    verify(accountService, times(1)).retrieveStatement(5);
  }

  @Test
  void shouldBeReturnNotFoundWhenAccountIdIsInvalidForStatement() {
    // When and Then
    var response = controller.retrieveStatement(-1).block();

    //Then
    assertNotNull(response);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void shouldBeCreateTransaction() {
    // Given
    when(accountService.processTransaction(anyInt(), anyInt(), anyString(), anyString()))
        .thenReturn(Mono.just(createAccount()));

    // When
    var response = controller.transact(5, createTransact()).block();

    // Then
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void shouldBeReturnNotFoundWhenAccountIdIsInvalid() {
    // When
    var response = controller.transact(-1, createTransact()).block();

    // Then
    assertNotNull(response);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void shouldBeReturnUnprocessableEntityWhenTransactionIsInvalid() {
    // When
    var response = controller.transact(5, invalidTransact()).block();

    // Then
    assertNotNull(response);
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
  }
}
