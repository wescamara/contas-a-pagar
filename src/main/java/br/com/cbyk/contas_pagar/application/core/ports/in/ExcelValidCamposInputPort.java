package br.com.cbyk.contas_pagar.application.core.ports.in;

import br.com.cbyk.contas_pagar.application.core.domain.ContaInvalid;
import org.apache.commons.csv.CSVRecord;

public interface ExcelValidCamposInputPort {

    ContaInvalid valid(CSVRecord csvRecord );

}
