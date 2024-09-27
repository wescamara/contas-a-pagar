package br.com.cbyk.contas_pagar.adapters;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.adapters.out.CreateContaAdapter;
import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.out.CreateContaOutputPort;
import br.com.cbyk.contas_pagar.application.core.usecase.CreateContaUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateContaAdapterTest {

    @InjectMocks
    private CreateContaAdapter createContaAdapter;

    @Mock
    private Conta contaMock;

    @Mock
    private ContaEntity contaEntityMock;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ContaMapper contaMapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateContaWithSuccess() {

        doReturn(contaEntityMock)
            .when(contaRepository)
            .save(any());

        doReturn(contaEntityMock).when(contaMapper).toContaEntity(any());

        doReturn(contaMock).when(contaMapper).toConta(contaEntityMock);

        Conta conta = createContaAdapter.create(contaMock);

        Assertions.assertEquals(contaMock,conta);
        verify(this.contaRepository,times(1)).save(any());
        verify(this.contaMapper,times(1)).toConta(contaEntityMock);
        verify(this.contaMapper,times(1)).toContaEntity(any());
    }

}
