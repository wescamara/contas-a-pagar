package br.com.cbyk.contas_pagar.application.core.domain;

import java.util.List;


public class ContaExcel {

    private List<Conta> contas;
    private List<ContaInvalid> contasInvalid;

    public ContaExcel() {
    }

    public ContaExcel(List<Conta> contas, List<ContaInvalid> contasInvalid) {
        this.contas = contas;
        this.contasInvalid = contasInvalid;
    }

    public List<ContaInvalid> getContasInvalid() {
        return contasInvalid;
    }

    public void setContasInvalid(
        List<ContaInvalid> contasInvalid) {
        this.contasInvalid = contasInvalid;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}
