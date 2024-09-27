package br.com.cbyk.contas_pagar.adapters.out;

import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.out.CreateContaOutputPort;
import org.springframework.stereotype.Component;

@Component
public class CreateContaAdapter implements CreateContaOutputPort {

    private final ContaRepository contaRepository;

    private final ContaMapper contaMapper;

    public CreateContaAdapter(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    @Override
    public Conta create(Conta conta) {

        final ContaEntity contaEntity = contaMapper.toContaEntity(conta);

        ContaEntity contaCreated = contaRepository.save(contaEntity);

        return contaMapper.toConta(contaCreated);

    }
}
