package br.com.cbyk.contas_pagar.usecases;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.usecase.CreateContaUseCase;
import br.com.cbyk.contas_pagar.application.core.ports.out.CreateContaOutputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateContaUseCaseTest {

    @InjectMocks
    private CreateContaUseCase createContaUseCase;

    @Mock
    private Conta contaMock;

    @Mock
    private CreateContaOutputPort createContaOutputPortMock;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateContaWithSuccess() {

        doReturn(contaMock)
            .when(createContaOutputPortMock)
            .create(any());

        Conta conta = createContaUseCase.create(contaMock);

        Assertions.assertEquals(contaMock,conta);
        verify(this.createContaOutputPortMock,times(1)).create(any());
    }

}
