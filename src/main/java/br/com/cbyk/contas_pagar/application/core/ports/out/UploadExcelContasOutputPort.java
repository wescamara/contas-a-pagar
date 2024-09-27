package br.com.cbyk.contas_pagar.application.core.ports.out;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import java.util.List;

public interface UploadExcelContasOutputPort {

    List<Conta> upload(List<Conta> contas);
}
