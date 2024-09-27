package br.com.cbyk.contas_pagar.application.core.ports.in;

import br.com.cbyk.contas_pagar.application.core.domain.ContaExcel;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelConvertToContasInputPort {

    ContaExcel convert(MultipartFile file);

}
