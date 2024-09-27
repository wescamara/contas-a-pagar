package br.com.cbyk.contas_pagar.usecases;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindContaByIdInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.UpdateSituationContaOutputPort;
import br.com.cbyk.contas_pagar.application.core.usecase.UpdateSituationContaUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateSituationContaUseCaseTest {

    @InjectMocks
    private UpdateSituationContaUseCase updateSituationContaUseCase;

    @Mock
    private Conta contaMock;

    @Mock
    private UpdateSituationContaOutputPort updateSituationContaOutputPort;

    @Mock
    private FindContaByIdInputPort findContaByIdInputPort;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateSituationContaWithSuccess() {

        Long id = 1l;
        String situation = "PAGO";

        doReturn(contaMock).when(findContaByIdInputPort).find(anyLong());

        doNothing()
            .when(updateSituationContaOutputPort)
            .update(any());

        updateSituationContaUseCase.update(id, situation);

        verify(this.updateSituationContaOutputPort, times(1)).update(any());
        verify(this.findContaByIdInputPort, times(1)).find(anyLong());
    }

    @Test
    void shouldNotUpdateSituationConta() {

        Long id = 1l;
        String situation = "PAGO";
        Exception exception = null;

        doReturn(null).when(findContaByIdInputPort).find(anyLong());

        try {
            updateSituationContaUseCase.update(id, situation);
        } catch (Exception e) {
            exception = e;
        }

        Assertions.assertNotNull(exception);

        verify(this.updateSituationContaOutputPort, never()).update(any());
        verify(this.findContaByIdInputPort, times(1)).find(anyLong());
    }


}
