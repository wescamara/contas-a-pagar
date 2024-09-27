package br.com.cbyk.contas_pagar.adapters.in.controller.mapper;

import br.com.cbyk.contas_pagar.adapters.in.controller.response.ContaInvalidResponse;
import br.com.cbyk.contas_pagar.application.core.domain.ContaInvalid;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaInvalidMapperResponse {

    List<ContaInvalidResponse> toContaInvalid(List<ContaInvalid> contas);

}
