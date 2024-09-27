package br.com.cbyk.contas_pagar.application.core.usecase;

import static br.com.cbyk.contas_pagar.application.core.domain.constants.Error.NOT_IS_EXCEL;
import static br.com.cbyk.contas_pagar.application.core.utils.ExcelUtil.hasCSVFormat;
import static br.com.cbyk.contas_pagar.application.core.utils.ExcelUtil.headerConta;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.domain.ContaExcel;
import br.com.cbyk.contas_pagar.application.core.domain.ContaInvalid;
import br.com.cbyk.contas_pagar.application.core.domain.exception.ExcelException;
import br.com.cbyk.contas_pagar.application.core.domain.exception.NotIsExcelException;
import br.com.cbyk.contas_pagar.application.core.ports.in.ExcelConvertToContasInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.ExcelValidCamposInputPort;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class ExcelConvertToContasUseCase implements ExcelConvertToContasInputPort {

    private static final String DATE_PATTERN = "yyyy/MM/dd";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

    private final ExcelValidCamposInputPort excelValidCamposInputPort;

    public ExcelConvertToContasUseCase(ExcelValidCamposInputPort excelValidCamposInputPort) {
        this.excelValidCamposInputPort = excelValidCamposInputPort;
    }

    @Override
    public ContaExcel convert(MultipartFile file) {
        try {

            if (hasCSVFormat(file)) {
                final ContaExcel contas = this.csvToContaExcel(file.getInputStream());
                return contas;
            } else {
                throw new NotIsExcelException(NOT_IS_EXCEL);
            }
        } catch (Exception e) {
            throw new ExcelException(e.getMessage());
        }
    }

    private ContaExcel csvToContaExcel(InputStream inputStream) {

        List<Conta> contas = new ArrayList<>();
        List<ContaInvalid> contasInvalid = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,
                CSVFormat.DEFAULT
                    .builder().setDelimiter(";").build()
                    .withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<String> header = List.of(headerConta());
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            int row = 0;
            for (CSVRecord csvRecord : csvRecords) {

                final ContaInvalid contaInvalid = excelValidCamposInputPort.valid(csvRecord);
                contaInvalid.setRow(row);
                if (!contaInvalid.getReasons().isEmpty()) {
                    contasInvalid.add(contaInvalid);
                } else {
                    Conta conta = new Conta(formatter.parse(csvRecord.get(header.get(0))),
                        formatter.parse(csvRecord.get(header.get(1))),
                        Double.valueOf(csvRecord.get(header.get(2))),
                        csvRecord.get(header.get(3)),
                        csvRecord.get(header.get(4)));
                    contas.add(conta);
                }
                row++;
            }

            return new ContaExcel(contas, contasInvalid);
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
