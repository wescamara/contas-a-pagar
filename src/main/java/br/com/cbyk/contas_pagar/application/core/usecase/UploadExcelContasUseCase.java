package br.com.cbyk.contas_pagar.application.core.usecase;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.UploadExcelContasInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.UploadExcelContasOutputPort;
import java.util.List;

public class UploadExcelContasUseCase implements UploadExcelContasInputPort {

    private final UploadExcelContasOutputPort uploadExcelContasOutputPort;

    public UploadExcelContasUseCase(UploadExcelContasOutputPort uploadExcelContasOutputPort) {
        this.uploadExcelContasOutputPort = uploadExcelContasOutputPort;
    }

    @Override
    public List<Conta> upload(List<Conta> contas) {
        return uploadExcelContasOutputPort.upload(contas);
    }
}
