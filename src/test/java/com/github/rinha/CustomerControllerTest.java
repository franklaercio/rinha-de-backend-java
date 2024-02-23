package com.github.rinha;

//@DisplayName("Customer Controller Unit Test")
//@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

//    @Mock
//    private AccountService accountService;
//
//    @InjectMocks
//    private CustomerController controller;
//
//    @Test
//    void shouldBeRetrieveStatement() {
//        // Given
//        when(accountService.isValidCustomerId(anyInt())).thenReturn(Mono.just(true));
//        when(accountService.retrieveStatement(anyInt())).thenReturn(Mono.just(new StatementDTO(new BalanceDTO(10, 10), List.of(new TransactionResponse(1, "c", "test", "2024-02-19")))));
//
//        // When and Then
//        controller.getExtratoByClienteId(123)
//                .doOnNext(response -> {
//                    assert response.getStatusCodeValue() == HttpStatus.OK.value();
//                    verify(accountService, times(1)).isValidCustomerId(123);
//                    verify(accountService, times(1)).retrieveStatement(123);
//                })
//                .block();
//    }
//
//    @Test
//    void shouldBeReturnNotFoundWhenCustomerIdIsInvalidForStatement() {
//        // Given
//        when(accountService.isValidCustomerId(anyInt())).thenReturn(Mono.just(false));
//
//        // When and Then
//        controller.getExtratoByClienteId(123)
//                .doOnNext(response -> {
//                    assert response.getStatusCodeValue() == HttpStatus.NOT_FOUND.value();
//                })
//                .block();
//    }
//
//    @Test
//    void shouldBeCreateTransaction() {
//        // Given
//        TransactionRequest transaction = new TransactionRequest("100", "c", "test");
//
//        when(accountService.isValidCustomerId(anyInt())).thenReturn(Mono.just(true));
//        when(accountService.isTransactionValid(transaction)).thenReturn(Mono.just(true));
//        when(accountService.processTransaction(anyInt(), anyInt(), anyString(), anyString()))
//                .thenReturn(Mono.just(new CustomerDTO(123, 10)));
//
//        // When and Then
//        controller.transacionar(123, transaction)
//                .doOnNext(response -> {
//                    assert response.getStatusCodeValue() == HttpStatus.OK.value();
//                    verify(accountService, times(1)).isValidCustomerId(123);
//                    verify(accountService, times(1)).isTransactionValid(transaction);
//                    verify(accountService, times(1)).processTransaction(123, transaction.parseValueToInt(), transaction.tipo(), transaction.descricao());
//                })
//                .block();
//    }
//
//    @Test
//    void shouldBeReturnNotFoundWhenCustomerIdIsInvalid() {
//        // Given
//        TransactionRequest transaction = new TransactionRequest("100", "c", "test");
//
//        when(accountService.isValidCustomerId(anyInt())).thenReturn(Mono.just(false));
//
//        // When and Then
//        controller.transacionar(123, transaction)
//                .doOnNext(response -> {
//                    assert response.getStatusCodeValue() == HttpStatus.NOT_FOUND.value();
//                })
//                .block();
//    }
//
//    @Test
//    void shouldBeReturnUnprocessableEntityWhenTransactionIsInvalid() {
//        // Given
//        TransactionRequest transaction = new TransactionRequest("100", "c", "test");
//
//        when(accountService.isValidCustomerId(anyInt())).thenReturn(Mono.just(true));
//        when(accountService.isTransactionValid(transaction)).thenReturn(Mono.just(false));
//
//        // When and Then
//        controller.transacionar(123, transaction)
//                .doOnNext(response -> {
//                    assert response.getStatusCodeValue() == HttpStatus.UNPROCESSABLE_ENTITY.value();
//                })
//                .block();
//    }
}
