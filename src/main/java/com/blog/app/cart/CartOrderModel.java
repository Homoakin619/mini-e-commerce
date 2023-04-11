package com.blog.app.cart;

import java.util.List;

import com.blog.app.appuser.UserModel;
import com.blog.app.product.ProductModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_order")
public class CartOrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private CartModel cart;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
    private List<ProductModel> product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private boolean ordered = false;

    public Double getOrderTotal() {
        return product.getPrice() * quantity;
    }

}
