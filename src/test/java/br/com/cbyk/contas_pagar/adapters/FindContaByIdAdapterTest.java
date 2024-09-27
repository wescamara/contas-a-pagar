package br.com.cbyk.contas_pagar.adapters;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.adapters.out.FindContaByIdAdapter;
import br.com.cbyk.contas_pagar.adapters.out.repository.ContaRepository;
import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import br.com.cbyk.contas_pagar.adapters.out.repository.mapper.ContaMapper;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FindContaByIdAdapterTest {

    @InjectMocks
    private FindContaByIdAdapter findContaByIdAdapter;

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
    void shouldFindContaWithSuccess() {

        Long id = 1l;

        doReturn(Optional.of(contaEntityMock))
            .when(contaRepository)
            .findById(anyLong());

        doReturn(contaMock)
            .when(contaMapper)
            .toConta(contaEntityMock);

        Conta conta = findContaByIdAdapter.find(id);

        Assertions.assertEquals(contaMock, conta);
        verify(this.contaRepository, times(1)).findById(anyLong());
        verify(this.contaMapper, times(1)).toConta(contaEntityMock);
    }

    @Test
    void shouldNotFindContaWithSuccess() {

        Long id = 1l;
        Exception exception = null;

        doThrow(new RuntimeException())
            .when(contaRepository)
            .findById(anyLong());

        try {
           findContaByIdAdapter.find(id);
        }catch (Exception e){
            exception = e;
        }


        Assertions.assertNotNull(exception);
        verify(this.contaRepository, times(1)).findById(anyLong());
        verify(this.contaMapper, never()).toConta(contaEntityMock);
    }

}
