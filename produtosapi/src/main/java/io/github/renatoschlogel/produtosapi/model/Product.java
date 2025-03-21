package io.github.renatoschlogel.produtosapi.model;

import lombok.Getter;

@Getter
public class Product {

    private String id;
    private String name;
    private String description;
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
