package br.com.cbyk.contas_pagar.controllers.contasPagar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cbyk.contas_pagar.adapters.in.controller.ContaPagarController;
import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperRequest;
import br.com.cbyk.contas_pagar.adapters.in.controller.mapper.ContaMapperResponse;
import br.com.cbyk.contas_pagar.adapters.in.controller.request.ContaRequest;
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


class UpdateContaTest {

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
    void testUpdate() throws Exception {
        Long id = 1L;
        ContaRequest request = new ContaRequest();
        request.setDescricao("Test");
        request.setSituacao("Pago");
        request.setValor(30.0);
        request.setDataVencimento(new Date());
        request.setDataPagamento(new Date());
        Conta conta = new Conta();

        when(contaMapperRequest.toConta(request)).thenReturn(conta);
        doNothing().when(updateContaInputPort).update(any());
        mockMvc.perform(put("/contas-a-pagar/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk());

        verify(updateContaInputPort).update(conta);
    }


}
