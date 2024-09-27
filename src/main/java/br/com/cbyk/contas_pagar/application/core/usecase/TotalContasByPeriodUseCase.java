package br.com.cbyk.contas_pagar.application.core.usecase;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import br.com.cbyk.contas_pagar.application.core.ports.in.TotalContasByPeriodInputPort;
import br.com.cbyk.contas_pagar.application.core.ports.out.TotalContasByPeriodOutputPort;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class TotalContasByPeriodUseCase implements TotalContasByPeriodInputPort {

    private final TotalContasByPeriodOutputPort totalContasByPeriodOutputPort;

    public TotalContasByPeriodUseCase(TotalContasByPeriodOutputPort totalContasByPeriodOutputPort) {
        this.totalContasByPeriodOutputPort = totalContasByPeriodOutputPort;
    }

    @Override
    public Page<Conta> find(Pageable pageable, Date dateOf, Date dateUntil) {
        return totalContasByPeriodOutputPort.find(pageable, dateOf, dateUntil);
    }
}
