package br.com.cbyk.contas_pagar.application.core.ports.out;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TotalContasByPeriodOutputPort {

    Page<Conta> find (Pageable pageable, Date dateOf, Date dateUntil);
}
