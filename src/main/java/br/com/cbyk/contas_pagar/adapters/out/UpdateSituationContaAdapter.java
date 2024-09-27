package br.com.cbyk.contas_pagar.adapters.out;

import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.out.UpdateSituationContaOutputPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateSituationContaAdapter implements UpdateSituationContaOutputPort {

    private final ContaRepository contaRepository;

    private final ContaMapper contaMapper;

    public UpdateSituationContaAdapter(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }


    @Override
    public void update(Conta conta) {
        final ContaEntity contaEntity = contaMapper.toContaEntity(conta);
        contaRepository.save(contaEntity);
    }
}
