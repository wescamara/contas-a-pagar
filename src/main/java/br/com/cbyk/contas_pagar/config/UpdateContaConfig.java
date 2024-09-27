package br.com.cbyk.contas_pagar.config;

import br.com.cbyk.contas_pagar.adapters.out.UpdateContaAdapter;
import br.com.cbyk.contas_pagar.application.core.usecase.UpdateContaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateContaConfig {

    @Bean
    public UpdateContaUseCase updateContaUseCase(UpdateContaAdapter updateContaAdapter) {
        return new UpdateContaUseCase(updateContaAdapter);
    }
}
