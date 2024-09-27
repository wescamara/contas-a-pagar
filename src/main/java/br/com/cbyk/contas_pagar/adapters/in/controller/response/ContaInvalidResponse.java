package br.com.cbyk.contas_pagar.adapters.in.controller.response;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaInvalidResponse {

    private List<Map<String, String>> reasons;
    private int row;
}
