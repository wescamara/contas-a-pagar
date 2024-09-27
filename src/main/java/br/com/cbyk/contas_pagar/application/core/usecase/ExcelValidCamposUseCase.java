package br.com.cbyk.contas_pagar.application.core.usecase;

import static br.com.cbyk.contas_pagar.application.core.utils.ExcelUtil.headerConta;

import br.com.cbyk.contas_pagar.application.core.domain.ContaInvalid;
import br.com.cbyk.contas_pagar.application.core.ports.in.ExcelValidCamposInputPort;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.csv.CSVRecord;

public class ExcelValidCamposUseCase implements ExcelValidCamposInputPort {


    @Override
    public ContaInvalid valid(CSVRecord csvRecord) {

        ContaInvalid contaInvalid = new ContaInvalid();
        List<Map<String, String>> reasons = new ArrayList<>();
        List<String> headers = List.of(headerConta());

        if (Objects.isNull(csvRecord.get(0)) || csvRecord.get(0).isEmpty()) {
            validateField(csvRecord, headers, reasons, 0);
        }

        if (Objects.isNull(csvRecord.get(1)) || csvRecord.get(1).isEmpty()) {
            validateField(csvRecord, headers, reasons, 1);
        }

        if (Objects.isNull(csvRecord.get(2)) || csvRecord.get(2).isEmpty()) {
            validateField(csvRecord, headers, reasons, 2);
        }

        if (Objects.isNull(csvRecord.get(3)) || csvRecord.get(3).isEmpty()) {
            validateField(csvRecord, headers, reasons, 3);
        }

        if (Objects.isNull(csvRecord.get(4)) || csvRecord.get(4).isEmpty()) {
            validateField(csvRecord, headers, reasons, 4);
        }

        contaInvalid.setReasons(reasons);
        return contaInvalid;
    }

    private static void validateField(CSVRecord csvRecord, List<String> headers, List<Map<String, String>> reasons,
        int position) {
        Map<String, String> reason = new HashMap<>();
        reason.put(headers.get(position), csvRecord.get(position));
        reasons.add(reason);
    }
}
