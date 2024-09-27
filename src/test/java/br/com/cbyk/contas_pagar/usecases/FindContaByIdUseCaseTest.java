package br.com.cbyk.contas_pagar.usecases;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.out.FindContaByIdOutputPort;
import br.com.cbyk.contas_pagar.application.core.usecase.FindContaByIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FindContaByIdUseCaseTest {

    @InjectMocks
    private FindContaByIdUseCase findContaByIdUseCase;

    @Mock
    private Conta contaMock;

    @Mock
    private FindContaByIdOutputPort findContaByIdOutputPort;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindContaWithSuccess() {

        Long id = 1l;

        doReturn(contaMock)
            .when(findContaByIdOutputPort)
            .find(any());

        Conta conta = findContaByIdUseCase.find(id);

        Assertions.assertEquals(contaMock, conta);
        verify(this.findContaByIdOutputPort, times(1)).find(any());
    }

}
