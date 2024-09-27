package br.com.cbyk.contas_pagar.application.core.ports.in;

public interface UpdateSituationContaInputPort {

    void update(Long id, String situation);
}
