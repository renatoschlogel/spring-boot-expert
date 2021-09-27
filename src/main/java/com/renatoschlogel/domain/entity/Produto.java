package com.renatoschlogel.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Campo descricao do produto é de preenchimento obrigatório.")
    private String descricao;

    @NotNull(message = "O Preço unitário do produto é de preenchimento obrigatório.")
    private BigDecimal precoUnitatio;

}
