package com.blog.app.cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.blog.app.appuser.UserModel;
import com.blog.app.utilities.ReferenceCodeGenerator;

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
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartModel {
    @Id
    @SequenceGenerator(name = "cart_sequence", sequenceName = "cart_sequence", allocationSize = 1)
    @GeneratedValue(generator = "cart_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserModel user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartOrderModel> orderItems = new ArrayList<>();

    private boolean checkedOut = false;

    @Column(nullable = false)
    private String referenceId;
    private LocalDateTime orderDate;

    public CartModel() {
        this.referenceId = new ReferenceCodeGenerator().generateRefCode();
    }

    public void setCheckedOut(boolean ordered) {
        this.orderDate = LocalDateTime.now();
        this.checkedOut = ordered;
    }

    public void setOrderItems(CartOrderModel item) {
        this.orderItems.add(item);
    }

    public CartModel(UserModel user, boolean checkedOut) {
        this();
        this.user = user;
        this.checkedOut = checkedOut;
    }

    public Double getCartTotal() {
        Double total = 0.0;
        for (CartOrderModel order : orderItems) {
            total += order.getOrderTotal();
        }
        return total;
    }
}
