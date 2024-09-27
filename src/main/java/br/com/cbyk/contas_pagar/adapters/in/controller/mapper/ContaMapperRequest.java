package br.com.cbyk.contas_pagar.adapters.in.controller.mapper;

import br.com.cbyk.contas_pagar.adapters.in.controller.request.ContaRequest;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContaMapperRequest {

    @Mapping(target = "id", ignore = true)
    Conta toConta(ContaRequest request);
}
