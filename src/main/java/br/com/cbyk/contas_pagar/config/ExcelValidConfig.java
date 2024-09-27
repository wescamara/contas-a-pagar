package br.com.cbyk.contas_pagar.config;

import br.com.cbyk.contas_pagar.application.core.usecase.ExcelValidCamposUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelValidConfig {


    @Bean
    public ExcelValidCamposUseCase excelValidCamposUseCase() {
        return new ExcelValidCamposUseCase();
    }
}
