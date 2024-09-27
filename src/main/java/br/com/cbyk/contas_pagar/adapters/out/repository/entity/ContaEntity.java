package br.com.cbyk.contas_pagar.adapters.out.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_vencimento", nullable = false)
    private Date dataVencimento;

    @Column(name = "data_pagamento", nullable = false)
    private Date dataPagamento;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name = "situacao", nullable = false, length = 50)
    private String situacao;
}
