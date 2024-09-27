package br.com.cbyk.contas_pagar.application.core.usecase;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.UpdateContaInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.UpdateContaOutputPort;

public class UpdateContaUseCase implements UpdateContaInputPort {

    private final UpdateContaOutputPort updateContaOutputPort;

    public UpdateContaUseCase(UpdateContaOutputPort updateContaOutputPort) {
        this.updateContaOutputPort = updateContaOutputPort;
    }

    @Override
    public void update(Conta conta) {
        updateContaOutputPort.update(conta);
    }
}
