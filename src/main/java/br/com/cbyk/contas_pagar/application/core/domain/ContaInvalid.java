package br.com.cbyk.contas_pagar.application.core.domain;

import java.util.List;
import java.util.Map;


public class ContaInvalid {

    private List<Map<String, String>> reasons;
    private Integer row;

    public ContaInvalid() {
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public ContaInvalid(List<Map<String, String>> reasons, Integer row) {
        this.reasons = reasons;
        this.row = row;
    }

    public List<Map<String, String>> getReasons() {
        return reasons;
    }

    public void setReasons(List<Map<String, String>> reasons) {
        this.reasons = reasons;
    }
}
