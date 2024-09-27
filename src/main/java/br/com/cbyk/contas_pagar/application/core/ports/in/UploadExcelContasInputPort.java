package br.com.cbyk.contas_pagar.application.core.ports.in;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import java.util.List;

public interface UploadExcelContasInputPort {

    List<Conta> upload(List<Conta> contas);
}
