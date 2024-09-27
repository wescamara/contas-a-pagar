package br.com.cbyk.contas_pagar.application.core.usecase;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindContaByIdInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.FindContaByIdOutputPort;

public class FindContaByIdUseCase implements FindContaByIdInputPort {

    private final FindContaByIdOutputPort findContaByIdOutputPort;

    public FindContaByIdUseCase(FindContaByIdOutputPort findContaByIdOutputPort) {
        this.findContaByIdOutputPort = findContaByIdOutputPort;
    }

    @Override
    public Conta find(Long id) {
        return findContaByIdOutputPort.find(id);
    }
}
