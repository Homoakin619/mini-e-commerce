package com.blog.app.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(generator = "product_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public ProductModel(String title, Double price, ProductCategory category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

}
