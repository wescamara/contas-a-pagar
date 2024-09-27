package br.com.cbyk.contas_pagar.adapters.out.repository.mapper;

import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    ContaEntity toContaEntity(Conta conta);

    Conta toConta(ContaEntity contaEntity);

    List<Conta> toConta(List<ContaEntity> contaEntity);

    List<ContaEntity> toContasEntity(List<Conta> contas);

}

