package br.com.cbyk.contas_pagar.adapters.out;

import static br.com.cbyk.contas_pagar.application.core.domain.constants.Error.CONTAS_NOT_FOUND_BY_DUEDATE_AND_DESCRIPITON;

import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.domain.exception.ContaNotFoundException;
import br.com.cbyk.contas_pagar.application.core.ports.out.FindAllContasByDueDateAndDescriptionOutputPort;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class FindAllContasByDueDateAndDescriptionAdapter implements FindAllContasByDueDateAndDescriptionOutputPort {

    private final ContaRepository contaRepository;

    private final ContaMapper contaMapper;

    public FindAllContasByDueDateAndDescriptionAdapter(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }


    @Override
    public Page<Conta> find(Pageable pageable, Date dueDate, String description) {

        Page<ContaEntity> contasEntity = contaRepository.findByDataVencimentoAndDescricao(
            dueDate, description, pageable);

        final List<Conta> contas = contaMapper.toConta(contasEntity.getContent());

        if (contas.isEmpty()) {
            throw new ContaNotFoundException(CONTAS_NOT_FOUND_BY_DUEDATE_AND_DESCRIPITON);
        }

        return new PageImpl<>(contas, pageable, contasEntity.getTotalElements());
    }
}
