package com.renatoschlogel.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDPEDIDO")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "IDPRODUTO")
    private Produto produto;

    @Column(length = 20, precision = 2)
    private Integer quantidade;

}
