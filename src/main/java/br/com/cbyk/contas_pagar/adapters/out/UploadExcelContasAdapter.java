package br.com.cbyk.contas_pagar.adapters.out;

import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.out.UploadExcelContasOutputPort;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UploadExcelContasAdapter implements UploadExcelContasOutputPort {

    private final ContaRepository contaRepository;

    private final ContaMapper contaMapper;

    public UploadExcelContasAdapter(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    @Override
    public List<Conta> upload(List<Conta> contas) {

        final List<ContaEntity> contasEntity = contaMapper.toContasEntity(contas);

        final List<ContaEntity> contaEntities = contaRepository.saveAll(contasEntity);

        return contaMapper.toConta(contaEntities);
    }
}
