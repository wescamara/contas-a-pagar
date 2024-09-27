package br.com.cbyk.contas_pagar.controllers.contasPagar;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cbyk.contas_pagar.adapters.in.controller.ContaPagarController;
import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperRequest;
import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperResponse;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


class UpdateSituationContaTest {

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
    void testUpdateSituation() throws Exception {
        Long id = 1L;
        String situation = "ACTIVE";

        doNothing().when(updateSituationContaInputPort).update(anyLong(), anyString());

        mockMvc.perform(put("/contas-a-pagar/update-situation/{id}", id)
                .param("situation", situation))
            .andExpect(status().isOk());

        verify(updateSituationContaInputPort).update(anyLong(), anyString());
    }

}
