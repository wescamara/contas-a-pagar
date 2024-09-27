package br.com.cbyk.contas_pagar.application.core.domain.exception;

public class ContaNotFoundException extends RuntimeException {

    public ContaNotFoundException(String message) {
        super(message);
    }
}
