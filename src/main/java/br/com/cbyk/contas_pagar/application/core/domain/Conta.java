package br.com.cbyk.contas_pagar.application.core.domain;

import java.time.LocalDateTime;
import java.util.Date;


public class Conta {


    private Long id;
    private Date dataVencimento;
    private Date dataPagamento;
    private Double valor;
    private String descricao;
    private String situacao;

    public Conta() {
    }

    public Conta(Date dataVencimento, Date dataPagamento, Double valor, String descricao, String situacao) {
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public Conta(Long id, Date dataVencimento, Double valor, Date dataPagamento, String descricao,
        String situacao) {
        this.id = id;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
