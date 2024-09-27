package br.com.cbyk.contas_pagar.adapters;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.adapters.out.UpdateContaAdapter;
import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.out.UpdateContaOutputPort;
import br.com.cbyk.contas_pagar.application.core.usecase.UpdateContaUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateContaAdapterTest {

    @InjectMocks
    private UpdateContaAdapter updateContaAdapter;

    @Mock
    private Conta contaMock;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ContaEntity contaEntityMock;

    @Mock
    private ContaMapper contaMapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateContaWithSuccess() {

        doReturn(contaEntityMock)
            .when(contaRepository)
            .save(any());

        doReturn(contaEntityMock).when(contaMapper).toContaEntity(any());

        updateContaAdapter.update(contaMock);

        verify(this.contaRepository,times(1)).save(any());
        verify(this.contaMapper,times(1)).toContaEntity(any());
    }

}
