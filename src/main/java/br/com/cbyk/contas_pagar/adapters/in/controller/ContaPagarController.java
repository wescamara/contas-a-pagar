package br.com.cbyk.contas_pagar.adapters.in.controller;

import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperRequest;
import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperResponse;
import br.com.cbyk.contas_pagar.adapters.in.controller.request.ContaRequest;
import br.com.cbyk.contas_pagar.adapters.in.controller.response.ContaResponse;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.CreateContaInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindAllContasByDueDateAndDescriptionInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindContaByIdInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.TotalContasByPeriodInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.UpdateContaInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.UpdateSituationContaInputPort;
import jakarta.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas-a-pagar")
public class ContaPagarController {


    private static final String DATE_PATTERN = "yyyy/MM/dd";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);



    private final CreateContaInputPort createContaInputPort;

    private final UpdateContaInputPort updateContaInputPort;

    private final UpdateSituationContaInputPort updateSituationContaInputPort;

    private final FindContaByIdInputPort findContaByIdInputPort;

    private final TotalContasByPeriodInputPort totalContasByPeriodInputPort;

    private final FindAllContasByDueDateAndDescriptionInputPort findAllContasByDueDateAndDescriptionInputPort;

    private final ContaMapperResponse contaMapperResponse;

    private final ContaMapperRequest contaMapperRequest;

    public ContaPagarController(CreateContaInputPort createContaInputPort, UpdateContaInputPort updateContaInputPort,
        UpdateSituationContaInputPort updateSituationContaInputPort, FindContaByIdInputPort findContaByIdInputPort,
        TotalContasByPeriodInputPort totalContasByPeriodInputPort,
        FindAllContasByDueDateAndDescriptionInputPort findAllContasByDueDateAndDescriptionInputPort,
        ContaMapperResponse contaMapperResponse, ContaMapperRequest contaMapperRequest) {
        this.createContaInputPort = createContaInputPort;
        this.updateContaInputPort = updateContaInputPort;
        this.updateSituationContaInputPort = updateSituationContaInputPort;
        this.findContaByIdInputPort = findContaByIdInputPort;
        this.totalContasByPeriodInputPort = totalContasByPeriodInputPort;
        this.findAllContasByDueDateAndDescriptionInputPort = findAllContasByDueDateAndDescriptionInputPort;
        this.contaMapperResponse = contaMapperResponse;
        this.contaMapperRequest = contaMapperRequest;
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ContaResponse> findById(
        @PathVariable("id") Long id) {

        final Conta conta = findContaByIdInputPort.find(id);
        final ContaResponse contaResponse = contaMapperResponse.toContaResponse(conta);
        return ResponseEntity.status(HttpStatus.OK).body(contaResponse);
    }

    @GetMapping("/find-by-dueDate-and-description")
    public ResponseEntity<Page<ContaResponse>> findByDueDateAndDescription(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam String dueDate,
        @RequestParam String description) throws ParseException {

        Date dueDateFormat = formatter.parse(dueDate);

        Pageable pageable = PageRequest.of(page, size);
        final Page<Conta> contaPageable = findAllContasByDueDateAndDescriptionInputPort.find(pageable, dueDateFormat,
            description);
        final List<ContaResponse> contasResponse = contaMapperResponse.toContaResponse(contaPageable.getContent());
        return ResponseEntity.status(HttpStatus.OK)
            .body(new PageImpl<>(contasResponse, pageable, contaPageable.getTotalElements()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id,
        @Valid @RequestBody ContaRequest request) {

        Conta conta = contaMapperRequest.toConta(request);
        conta.setId(id);
        updateContaInputPort.update(conta);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update-situation/{id}")
    public ResponseEntity<Void> updateSituation(@PathVariable("id") Long id,
        @RequestParam String situation) {

        updateSituationContaInputPort.update(id, situation);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/create")
    public ResponseEntity<ContaResponse> create(@Valid @RequestBody ContaRequest request) {

        final Conta conta = contaMapperRequest.toConta(request);
        final Conta contaCreated = createContaInputPort.create(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaMapperResponse.toContaResponse(contaCreated));
    }

    @GetMapping("/total-by-period")
    public ResponseEntity<Page<ContaResponse>> totalByPeriod(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam String dateOf,
        @RequestParam String dateUntil) throws ParseException {

        final Date dateOfFormat = formatter.parse(dateOf);
        final Date dateUntilFormat = formatter.parse(dateUntil);

        Pageable pageable = PageRequest.of(page, size);
        final Page<Conta> contaPageable = totalContasByPeriodInputPort.find(pageable, dateOfFormat, dateUntilFormat);
        final List<ContaResponse> contasResponse = contaMapperResponse.toContaResponse(contaPageable.getContent());
        return ResponseEntity.status(HttpStatus.OK)
            .body(new PageImpl<>(contasResponse, pageable, contaPageable.getTotalElements()));
    }

}
