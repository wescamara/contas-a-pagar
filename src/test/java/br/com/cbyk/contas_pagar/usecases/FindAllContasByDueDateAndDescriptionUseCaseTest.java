package br.com.cbyk.contas_pagar.usecases;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.out.FindAllContasByDueDateAndDescriptionOutputPort;
import br.com.cbyk.contas_pagar.application.core.usecase.FindAllContasByDueDateAndDescriptionUseCase;
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

public class FindAllContasByDueDateAndDescriptionUseCaseTest {

    @InjectMocks
    private FindAllContasByDueDateAndDescriptionUseCase findAllContasByDueDateAndDescriptionUseCase;

    @Mock
    private Page<Conta> contaPageMock;

    @Mock
    private Pageable pageable;

    @Mock
    private FindAllContasByDueDateAndDescriptionOutputPort findAllContasByDueDateAndDescriptionOutputPort;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAllContasWithSuccess() {

        String description = "Test";

        List<Conta> contaListMock = List.of(new Conta());

        PageImpl<Conta> contaPageable = new PageImpl<>(contaListMock, pageable, contaPageMock.getTotalElements());

        doReturn(10L).when(contaPageMock).getTotalElements();
        doReturn(contaPageable)
            .when(findAllContasByDueDateAndDescriptionOutputPort)
            .find(any(), any(), anyString());

        Page<Conta> contaPage = findAllContasByDueDateAndDescriptionUseCase.find(pageable, new Date(),
            description);

        Assertions.assertEquals(contaPageable, contaPage);
        verify(this.findAllContasByDueDateAndDescriptionOutputPort, times(1)).find(any(), any(), anyString());
    }

}
