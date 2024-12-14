package com.example.aud1.model;

import com.example.aud1.model.enumeration.ShoppingCartStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

//    public ShoppingCart() {
//        this.id = (long) (Math.random() * 1000);
//    }

    public ShoppingCart(User user) {
        //this.id = (long) (Math.random() * 1000);
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
