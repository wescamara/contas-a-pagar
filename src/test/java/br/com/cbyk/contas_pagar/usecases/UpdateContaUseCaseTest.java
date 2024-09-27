package br.com.cbyk.contas_pagar.usecases;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.usecase.UpdateContaUseCase;
import br.com.cbyk.contas_pagar.application.core.ports.out.UpdateContaOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateContaUseCaseTest {

    @InjectMocks
    private UpdateContaUseCase updateContaUseCase;

    @Mock
    private Conta contaMock;

    @Mock
    private UpdateContaOutputPort updateContaOutputPort;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateContaWithSuccess() {

        doNothing()
            .when(updateContaOutputPort)
            .update(any());

        updateContaUseCase.update(contaMock);

        verify(this.updateContaOutputPort, times(1)).update(any());
    }

}
