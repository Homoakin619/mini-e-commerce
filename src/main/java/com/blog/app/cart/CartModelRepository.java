package com.blog.app.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.app.appuser.UserModel;

public interface CartModelRepository extends JpaRepository<CartModel, Long> {
    @Query("SELECT c FROM CartModel c WHERE c.user = ?1 AND c.checkedOut = ?2")
    Optional<CartModel> getCartExists(UserModel user, boolean ordered);

    @Query("SELECT c FROM CartModel c WHERE c.user = ?1 AND c.checkedOut = ?2")
    CartModel getCart(UserModel user, boolean ordered);

    @Query("SELECT c FROM CartModel c WHERE c.user = ?1 AND c.checkedOut = ?2")
    CartModel fetchCart(CartOrderModel order);
}
