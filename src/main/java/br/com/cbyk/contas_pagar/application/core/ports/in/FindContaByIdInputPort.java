package br.com.cbyk.contas_pagar.application.core.ports.in;

import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import org.springframework.data.domain.Page;

public interface FindContaByIdInputPort {

    Conta find(Long id);

}
