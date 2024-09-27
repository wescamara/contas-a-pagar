package br.com.cbyk.contas_pagar.config;

import br.com.cbyk.contas_pagar.adapters.out.CreateContaAdapter;
import br.com.cbyk.contas_pagar.application.core.usecase.CreateContaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateContaConfig {


    @Bean
    public CreateContaUseCase createContaUseCase(
        CreateContaAdapter createContaAdapter) {
        return new CreateContaUseCase(createContaAdapter);
    }
}
