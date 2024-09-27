package br.com.cbyk.contas_pagar.application.core.ports.in;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;

public interface CreateContaInputPort {

    Conta create(Conta conta);
}
