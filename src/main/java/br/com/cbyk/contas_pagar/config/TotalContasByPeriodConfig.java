package br.com.cbyk.contas_pagar.config;

import br.com.cbyk.contas_pagar.adapters.out.TotalContasByPeriodAdapter;
import br.com.cbyk.contas_pagar.application.core.usecase.TotalContasByPeriodUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TotalContasByPeriodConfig {

    @Bean
    public TotalContasByPeriodUseCase totalContasByPeriodUseCase(TotalContasByPeriodAdapter totalContasByPeriodAdapter) {
        return new TotalContasByPeriodUseCase(totalContasByPeriodAdapter);
    }
}
