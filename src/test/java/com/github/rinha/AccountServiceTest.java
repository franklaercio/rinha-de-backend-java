package com.github.rinha;

//@DisplayName("Account Service Unit Test")
//@ContextConfiguration(classes = {AccountService.class})
//@ExtendWith(SpringExtension.class)
class AccountServiceTest {

//    @Autowired
//    private AccountService accountService;
//
//    @MockBean
//    private AccountPersistence accountPersistence;
//
//    @Test
//    void shouldBeFindStatementByCustomerId() {
//        // Given
//        when(this.accountPersistence.findCustomerById(anyInt())).thenReturn(new Customer(1, 10, 10));
//        when(this.accountPersistence.findTransactionsByCustomerId(anyInt())).thenReturn(List.of(new TransactionResponse(1, "c", "test", "2024-02-19")));
//
//
//        // When
//        Mono<StatementDTO> statement = this.accountService.retrieveStatement(1);
//
//        // Then
//        StepVerifier.create(statement)
//                .expectNextMatches(s -> s.saldo().limite() == 10 && s.saldo().total() == 10 && s.ultimas_transacoes().size() == 1)
//                .verifyComplete();
//    }
//
//    @Test
//    void shouldBeUpdateBalanceAndInsertTransaction() {
//        // Given
//        when(this.accountPersistence.findCustomerForUpdate(anyInt())).thenReturn(new Customer(1, 10, 10));
//        doNothing().when(this.accountPersistence).updateBalanceAndInsertTransaction(anyInt(), anyInt(), anyInt(), anyString(), anyString());
//
//        // When
//        Mono<CustomerDTO> customer = this.accountService.processTransaction(1, 1, "c", "test");
//
//        // Then
//        StepVerifier.create(customer)
//                .expectNextMatches(c -> c.limite() == 10 && c.saldo() == 11)
//                .verifyComplete();
//    }
//
//    @Test
//    void shouldValidateBalanceOfCustomer() {
//        // Given
//        when(this.accountPersistence.findCustomerForUpdate(anyInt())).thenReturn(new Customer(1, 10, 10));
//        doNothing().when(this.accountPersistence).updateBalanceAndInsertTransaction(anyInt(), anyInt(), anyInt(), anyString(), anyString());
//
//        // When
//        Mono<CustomerDTO> customer = this.accountService.processTransaction(1, 10000, "d", "test");
//
//        // Then
//        StepVerifier.create(customer)
//                .expectErrorMatches(throwable -> throwable instanceof UnprocessableException)
//                .verify();
//    }
//
//    @Test
//    void shouldVerifyCustomerId() {
//        // Given
//        when(this.accountPersistence.existsCustomerById(anyInt())).thenReturn(true);
//
//        // When
//        Mono<Boolean> isCustomerValid = this.accountService.isValidCustomerId(1);
//
//        // Then
//        StepVerifier.create(isCustomerValid)
//                .expectNextMatches(b -> b.equals(true))
//                .verifyComplete();
//    }
//
//    @Test
//    void shouldVerifyIfIsInvalidCustomerId() {
//        // Given
//        when(this.accountPersistence.existsCustomerById(anyInt())).thenReturn(false);
//
//        // When
//        Mono<Boolean> isCustomerValid = this.accountService.isValidCustomerId(1);
//
//        // Then
//        StepVerifier.create(isCustomerValid)
//                .expectNextMatches(b -> b.equals(false))
//                .verifyComplete();
//    }
//
//    @Test
//    void shouldVerifyIfIsTransactionValid() {
//        // When
//        Mono<Boolean> isTransactionValid = this.accountService.isTransactionValid(new TransactionRequest("1", "c", "test"));
//
//        // Then
//        StepVerifier.create(isTransactionValid)
//                .expectNextMatches(b -> b.equals(true))
//                .verifyComplete();
//    }
//
//    @Test
//    void shouldVerifyIfIsTransactionInvalid() {
//        // When
//        Mono<Boolean> isTransactionValid = this.accountService.isTransactionValid(new TransactionRequest("c", "c", "test"));
//
//        // Then
//        StepVerifier.create(isTransactionValid)
//                .expectErrorMatches(throwable -> throwable instanceof UnprocessableException)
//                .verify();
//    }
}
