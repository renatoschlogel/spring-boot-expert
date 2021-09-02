package com.renatoschlogel.domain.entity;

import java.math.BigDecimal;

public class Produto {

    private Integer id;
    private String descricao;
    private BigDecimal precoUnitatio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitatio() {
        return precoUnitatio;
    }

    public void setPrecoUnitatio(BigDecimal precoUnitatio) {
        this.precoUnitatio = precoUnitatio;
    }
}
