package br.com.cbyk.contas_pagar.adapters.in.controller;

import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaInvalidMapperResponse;
import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperResponse;
import br.com.cbyk.contas_pagar.adapters.in.controller.response.ContaInvalidResponse;
import br.com.cbyk.contas_pagar.adapters.in.controller.response.ContaResponse;
import br.com.cbyk.contas_pagar.adapters.in.controller.response.ExcelResponse;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.domain.ContaExcel;
import br.com.cbyk.contas_pagar.application.core.ports.in.ExcelConvertToContasInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.UploadExcelContasInputPort;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/contas-a-pagar/excel")
public class ContaPagarExcelController {

    private final UploadExcelContasInputPort uploadExcelContasInputPort;

    private final ExcelConvertToContasInputPort excelConvertToContasInputPort;

    private final ContaMapperResponse contaMapperResponse;

    private final ContaInvalidMapperResponse contaInvalidMapperResponse;

    public ContaPagarExcelController(UploadExcelContasInputPort uploadExcelContasInputPort,
        ExcelConvertToContasInputPort excelConvertToContasInputPort, ContaMapperResponse contaMapperResponse,
        ContaInvalidMapperResponse contaInvalidMapperResponse) {
        this.uploadExcelContasInputPort = uploadExcelContasInputPort;
        this.excelConvertToContasInputPort = excelConvertToContasInputPort;
        this.contaMapperResponse = contaMapperResponse;
        this.contaInvalidMapperResponse = contaInvalidMapperResponse;
    }

    @RequestMapping(
        path = "/upload",
        method = RequestMethod.POST,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ExcelResponse> upload(@RequestParam("file") MultipartFile file) {

        ContaExcel contaExcel = excelConvertToContasInputPort.convert(file);
        List<Conta> contasCreated = uploadExcelContasInputPort.upload(contaExcel.getContas());
        List<ContaResponse> contaResponse = contaMapperResponse.toContaResponse(contasCreated);
        List<ContaInvalidResponse> contaInvalid = contaInvalidMapperResponse.toContaInvalid(
            contaExcel.getContasInvalid());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ExcelResponse(contaResponse, contaInvalid));
    }


}
