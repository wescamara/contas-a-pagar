package br.com.cbyk.contas_pagar.config;


import br.com.cbyk.contas_pagar.adapters.out.UpdateSituationContaAdapter;
import br.com.cbyk.contas_pagar.application.core.usecase.UpdateSituationContaUseCase;
import br.com.cbyk.contas_pagar.application.core.ports.in.FindContaByIdInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateContaSituationConfig {

    @Bean
    public UpdateSituationContaUseCase updateSituationContaUseCase(
        UpdateSituationContaAdapter updateSituationContaAdapter,
        FindContaByIdInputPort findContaByIdInputPort) {
        return new UpdateSituationContaUseCase(updateSituationContaAdapter, findContaByIdInputPort);
    }
}
