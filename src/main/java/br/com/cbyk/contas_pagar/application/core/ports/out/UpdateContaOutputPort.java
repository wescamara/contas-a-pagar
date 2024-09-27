package br.com.cbyk.contas_pagar.application.core.ports.out;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;

public interface UpdateContaOutputPort {

    void update (Conta conta);
}
