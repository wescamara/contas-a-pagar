package br.com.cbyk.contas_pagar.controllers.contasPagar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class FindAllContasByDueDateAndDescriptionTest {

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
    void testFindByDueDateAndDescription() throws Exception {
        String dueDate = "2021/02/22";
        String description = "Test description";
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<Conta> contaPageable = new PageImpl<>(Collections.emptyList());
        List<ContaResponse> contasResponse = Collections.emptyList();

        when(findAllContasByDueDateAndDescriptionInputPort.find(any(), any(), any())).thenReturn(
            contaPageable);
        when(contaMapperResponse.toContaResponse(contaPageable.getContent())).thenReturn(contasResponse);

        mockMvc.perform(
                get("/contas-a-pagar/find-by-dueDate-and-description")
                    .param("page", String.valueOf(page))
                    .param("size", String.valueOf(size))
                    .param("dueDate", String.valueOf(dueDate))
                    .param("description", description))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(findAllContasByDueDateAndDescriptionInputPort).find(any(), any(), any());
        verify(contaMapperResponse).toContaResponse(contaPageable.getContent());
    }


}
