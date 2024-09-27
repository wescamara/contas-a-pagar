package br.com.cbyk.contas_pagar.usecases;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.out.FindContaByIdOutputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.TotalContasByPeriodOutputPort;
import br.com.cbyk.contas_pagar.application.core.usecase.FindContaByIdUseCase;
import br.com.cbyk.contas_pagar.application.core.usecase.TotalContasByPeriodUseCase;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class TotalContaByPeriodUseCaseTest {

    @InjectMocks
    private TotalContasByPeriodUseCase totalContasByPeriodUseCase;

    @Mock
    private Page<Conta> contaPageMock;

    @Mock
    private Pageable pageable;

    @Mock
    private TotalContasByPeriodOutputPort totalContasByPeriodOutputPort;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindContaWithSuccess() {

        Long id = 1l;

        List<Conta> contaListMock = List.of(new Conta());

        PageImpl<Conta> contaPageable = new PageImpl<>(contaListMock, pageable, contaPageMock.getTotalElements());

        doReturn(10L).when(contaPageMock).getTotalElements();
        doReturn(contaPageable)
            .when(totalContasByPeriodOutputPort)
            .find(any(), any(), any());

        Page<Conta> contaPage = totalContasByPeriodUseCase.find(pageable, new Date(),
            new Date());

        Assertions.assertEquals(contaPageable, contaPage);
        verify(this.totalContasByPeriodOutputPort, times(1)).find(any(), any(), any());
    }

}
