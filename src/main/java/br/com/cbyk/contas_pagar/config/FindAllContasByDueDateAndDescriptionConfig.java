package br.com.cbyk.contas_pagar.config;

import br.com.cbyk.contas_pagar.adapters.out.FindAllContasByDueDateAndDescriptionAdapter;
import br.com.cbyk.contas_pagar.application.core.usecase.FindAllContasByDueDateAndDescriptionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindAllContasByDueDateAndDescriptionConfig {

    @Bean
    public FindAllContasByDueDateAndDescriptionUseCase findAllContasByDueDateAndDescriptionUseCase(
        FindAllContasByDueDateAndDescriptionAdapter findAllContasByDueDateAndDescriptionAdapter) {
        return new FindAllContasByDueDateAndDescriptionUseCase(findAllContasByDueDateAndDescriptionAdapter);
    }
}
