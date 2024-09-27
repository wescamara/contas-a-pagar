package br.com.cbyk.contas_pagar.config;

import br.com.cbyk.contas_pagar.adapters.out.FindContaByIdAdapter;
import br.com.cbyk.contas_pagar.application.core.usecase.FindContaByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindContaByIdConfig {

    @Bean
    public FindContaByIdUseCase findContaByIdUseCase(FindContaByIdAdapter findContaByIdAdapter) {
        return new FindContaByIdUseCase(findContaByIdAdapter);
    }
}
