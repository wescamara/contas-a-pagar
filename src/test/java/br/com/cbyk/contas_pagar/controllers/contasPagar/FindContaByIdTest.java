package br.com.cbyk.contas_pagar.controllers.contasPagar;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cbyk.contas_pagar.adapters.in.controller.ContaPagarController;
import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperRequest;
import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperResponse;
import br.com.cbyk.contas_pagar.adapters.in.controller.response.ContaResponse;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.CreateContaInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindAllContasByDueDateAndDescriptionInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindContaByIdInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.TotalContasByPeriodInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.UpdateContaInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.in.UpdateSituationContaInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindContaByIdTest {

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
    void testFindById() throws Exception {
        Long id = 1L;
        Conta conta = new Conta();
        ContaResponse contaResponse = new ContaResponse();

        when(findContaByIdInputPort.find(id)).thenReturn(conta);
        when(contaMapperResponse.toContaResponse(conta)).thenReturn(contaResponse);

        mockMvc.perform(get("/contas-a-pagar/find-by-id/{id}", id))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(contaResponse.getId()));

        verify(findContaByIdInputPort).find(id);
        verify(contaMapperResponse).toContaResponse(conta);
    }


}
