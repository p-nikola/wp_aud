package com.example.aud1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToMany(mappedBy = "products")
    private List<ShoppingCart>carts;


    public Product( String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        //this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }
}
