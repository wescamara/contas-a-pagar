package br.com.cbyk.contas_pagar.adapters.out;

import static br.com.cbyk.contas_pagar.application.core.domain.constants.Error.CONTAS_NOT_FOUND_TOTAL_BY_PERIOD;

import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.domain.exception.ContaNotFoundException;
import br.com.cbyk.contas_pagar.application.core.ports.out.TotalContasByPeriodOutputPort;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TotalContasByPeriodAdapter implements TotalContasByPeriodOutputPort {

    private final ContaRepository contaRepository;

    private final ContaMapper contaMapper;

    public TotalContasByPeriodAdapter(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }


    @Override
    public Page<Conta> find(Pageable pageable, Date dateOf, Date dateUntil) {
        Page<ContaEntity> contasEntity = contaRepository.findByDataVencimentoBetween(
            dateOf, dateUntil, pageable);

        final List<Conta> contas = contaMapper.toConta(contasEntity.getContent());

        if (contas.isEmpty()) {
            throw new ContaNotFoundException(CONTAS_NOT_FOUND_TOTAL_BY_PERIOD);
        }

        return new PageImpl<>(contas, pageable, contasEntity.getTotalElements());
    }
}
