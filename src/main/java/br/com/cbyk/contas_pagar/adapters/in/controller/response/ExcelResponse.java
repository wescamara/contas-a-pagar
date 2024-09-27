package br.com.cbyk.contas_pagar.adapters.in.controller.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelResponse {

    private List<ContaResponse> contasCreated;
    private List<ContaInvalidResponse> contasInvalid;
}
