package br.com.cbyk.contas_pagar.application.core.usecase;

import static br.com.cbyk.contas_pagar.application.core.domain.constants.Error.CONTA_NOT_FOUND;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.domain.exception.ContaNotFoundException;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindContaByIdInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.UpdateSituationContaInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.UpdateSituationContaOutputPort;
import java.util.Objects;

public class UpdateSituationContaUseCase implements UpdateSituationContaInputPort {

    private final UpdateSituationContaOutputPort updateSituationContaOutputPort;

    private final FindContaByIdInputPort findContaByIdInputPort;

    public UpdateSituationContaUseCase(UpdateSituationContaOutputPort updateSituationContaOutputPort,
        FindContaByIdInputPort findContaByIdInputPort) {
        this.updateSituationContaOutputPort = updateSituationContaOutputPort;
        this.findContaByIdInputPort = findContaByIdInputPort;
    }

    @Override
    public void update(Long id, String situation) {

        Conta conta = findContaByIdInputPort.find(id);

        if (Objects.isNull(conta)) {
            throw new ContaNotFoundException(CONTA_NOT_FOUND);
        }

        conta.setSituacao(situation);
        updateSituationContaOutputPort.update(conta);
    }
}
