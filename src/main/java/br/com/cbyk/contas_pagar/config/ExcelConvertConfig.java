package br.com.cbyk.contas_pagar.config;

import br.com.cbyk.contas_pagar.application.core.ports.in.ExcelValidCamposInputPort;
import br.com.cbyk.contas_pagar.application.core.usecase.ExcelConvertToContasUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelConvertConfig {


    @Bean
    public ExcelConvertToContasUseCase excelConvertToContasUseCase(
        ExcelValidCamposInputPort excelValidCamposInputPort) {
        return new ExcelConvertToContasUseCase(excelValidCamposInputPort);
    }
}
