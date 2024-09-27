package br.com.cbyk.contas_pagar.adapters;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.adapters.out.TotalContasByPeriodAdapter;
import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
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

public class TotalContaByPeriodAdapterTest {

    @InjectMocks
    private TotalContasByPeriodAdapter totalContasByPeriodAdapter;

    @Mock
    private Page<Conta> contaPageMock;

    @Mock
    private Pageable pageable;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ContaMapper contaMapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldTotalContasByPeriodWithSuccess() {

        List<Conta> contaListMock = List.of(new Conta());
        PageImpl<Conta> contaPageable = new PageImpl<>(contaListMock, pageable, contaPageMock.getTotalElements());

        doReturn(10L).when(contaPageMock).getTotalElements();
        doReturn(contaPageable)
            .when(contaRepository)
            .findByDataVencimentoBetween(any(), any(), any());

        doReturn(contaListMock).when(contaMapper).toConta(anyList());

        Page<Conta> contaPage = totalContasByPeriodAdapter.find(pageable, new Date(),
            new Date());

        Assertions.assertEquals(contaPageable, contaPage);
        verify(this.contaRepository, times(1)).findByDataVencimentoBetween(any(), any(), any());
        verify(this.contaMapper, times(1)).toConta(anyList());
    }

}
