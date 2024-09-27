package br.com.cbyk.contas_pagar.config;

import br.com.cbyk.contas_pagar.adapters.out.UploadExcelContasAdapter;
import br.com.cbyk.contas_pagar.application.core.ports.out.UploadExcelContasOutputPort;
import br.com.cbyk.contas_pagar.application.core.usecase.UploadExcelContasUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadExcelContasConfig {


    @Bean
    public UploadExcelContasUseCase uploadExcelContasUseCase(
        UploadExcelContasAdapter uploadExcelContasAdapter) {
        return new UploadExcelContasUseCase(uploadExcelContasAdapter);
    }
}
