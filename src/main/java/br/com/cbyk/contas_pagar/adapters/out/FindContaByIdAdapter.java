package br.com.cbyk.contas_pagar.adapters.out;

import static br.com.cbyk.contas_pagar.application.core.domain.constants.Error.CONTA_NOT_FOUND;

import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.domain.exception.ContaNotFoundException;
import br.com.cbyk.contas_pagar.application.core.ports.out.FindContaByIdOutputPort;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class FindContaByIdAdapter implements FindContaByIdOutputPort {

    private final ContaRepository contaRepository;

    private final ContaMapper contaMapper;

    public FindContaByIdAdapter(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    @Override
    public Conta find(Long id) {
        ContaEntity contaEntity = contaRepository.findById(id).orElse(null);

        if (Objects.isNull(contaEntity)) {
            throw new ContaNotFoundException(CONTA_NOT_FOUND);
        }
        return contaMapper.toConta(contaEntity);
    }
}
