package br.com.cbyk.contas_pagar.adapters.in.controller.mapper;

import br.com.cbyk.contas_pagar.adapters.in.controller.response.ContaResponse;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapperResponse {

    ContaResponse toContaResponse(Conta conta);


    List<ContaResponse> toContaResponse(List<Conta> contas);
}
