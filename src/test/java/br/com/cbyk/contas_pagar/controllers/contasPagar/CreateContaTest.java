package br.com.cbyk.contas_pagar.controllers.contasPagar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cbyk.contas_pagar.adapters.in.controller.ContaPagarController;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CreateContaTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FindContaByIdInputPort findContaByIdInputPort;

    @Mock
    private ContaMapperResponse contaMapperResponse;
    @Mock
    private ContaMapperRequest contaMapperRequest;

    @Mock
    private UpdateContaInputPort updateContaInputPort;

    @Mock
    private UpdateSituationContaInputPort updateSituationContaInputPort;

    @Mock
    private CreateContaInputPort createContaInputPort;

    @Mock
    private FindAllContasByDueDateAndDescriptionInputPort findAllContasByDueDateAndDescriptionInputPort;
    @Mock
    private TotalContasByPeriodInputPort totalContasByPeriodInputPort;

    @Mock
    private Conta contaMock;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ContaPagarController(
            createContaInputPort,
            updateContaInputPort,
            updateSituationContaInputPort,
            findContaByIdInputPort,
            totalContasByPeriodInputPort,
            findAllContasByDueDateAndDescriptionInputPort,
            contaMapperResponse,
            contaMapperRequest
        )).build();
    }


    @Test
    void testCreate() throws Exception {
        ContaRequest request = new ContaRequest();
        request.setDescricao("Test");
        request.setSituacao("Pago");
        request.setValor(30.0);
        request.setDataVencimento(new Date());
        request.setDataPagamento(new Date());
        ContaResponse contaResponse = new ContaResponse();

        when(contaMapperRequest.toConta(request)).thenReturn(contaMock);
        when(createContaInputPort.create(any())).thenReturn(contaMock);
        when(contaMapperResponse.toContaResponse(contaMock)).thenReturn(contaResponse);

        mockMvc.perform(post("/contas-a-pagar/create")
                .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated());

        verify(createContaInputPort).create(contaMock);
        verify(contaMapperResponse).toContaResponse(contaMock);
        verify(contaMapperRequest).toConta(request);
    }
}
