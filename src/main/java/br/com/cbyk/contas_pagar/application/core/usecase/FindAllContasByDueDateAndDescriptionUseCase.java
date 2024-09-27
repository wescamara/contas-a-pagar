package br.com.cbyk.contas_pagar.application.core.usecase;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindAllContasByDueDateAndDescriptionInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.FindAllContasByDueDateAndDescriptionOutputPort;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FindAllContasByDueDateAndDescriptionUseCase implements FindAllContasByDueDateAndDescriptionInputPort {


    private final FindAllContasByDueDateAndDescriptionOutputPort findAllContasByDueDateAndDescriptionOutputPort;

    public FindAllContasByDueDateAndDescriptionUseCase(
        FindAllContasByDueDateAndDescriptionOutputPort findAllContasByDueDateAndDescriptionOutputPort) {
        this.findAllContasByDueDateAndDescriptionOutputPort = findAllContasByDueDateAndDescriptionOutputPort;
    }

    @Override
    public Page<Conta> find(Pageable pageable, Date dueDate, String description) {
        return findAllContasByDueDateAndDescriptionOutputPort.find(pageable, dueDate, description);
    }
}
