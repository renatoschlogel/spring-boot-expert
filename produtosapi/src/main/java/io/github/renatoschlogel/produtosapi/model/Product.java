package io.github.renatoschlogel.produtosapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "PRODUTO")
public class Product {

    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Double price;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desecription='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
