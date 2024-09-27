package br.com.cbyk.contas_pagar.application.core.usecase;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.CreateContaInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.CreateContaOutputPort;

public class CreateContaUseCase implements CreateContaInputPort {

    private final CreateContaOutputPort createContaOutputPort;

    public CreateContaUseCase(CreateContaOutputPort createContaOutputPort) {
        this.createContaOutputPort = createContaOutputPort;
    }

    @Override
    public Conta create(Conta conta) {
       return createContaOutputPort.create(conta);
    }
}
