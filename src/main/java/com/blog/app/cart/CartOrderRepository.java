package com.blog.app.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.app.appuser.UserModel;
import com.blog.app.product.ProductModel;

public interface CartOrderRepository extends JpaRepository<CartOrderModel, Long> {
    @Query("SELECT u FROM CartOrderModel u WHERE u.user =?1 AND u.product =?2 AND u.ordered =?3")
    List<CartOrderModel> getExistingOrder(UserModel user, ProductModel product, boolean ordered);

    @Query("SELECT u FROM CartOrderModel u JOIN u.product p WHERE u.user =?1 AND p =?2 AND u.ordered =?3")
    Optional<CartOrderModel> getOrderExists(UserModel user, ProductModel product, boolean ordered);

    @Query("SELECT o FROM CartOrderModel o WHERE o.user = ?1 AND o.ordered = ?2 AND EXISTS(SELECT p FROM ProductModel p WHERE o.product = p AND p = ?3)")
    List<CartOrderModel> findExistingOrder(UserModel user, boolean ordered, ProductModel product);

    @Query("SELECT u FROM CartOrderModel u WHERE u.user =?1 AND u.ordered =?2")
    Optional<CartOrderModel> getOrderExists(UserModel user, boolean ordered);

    @Query("SELECT c FROM CartOrderModel c WHERE c.ordered = :ordered AND c.user = :user AND :product MEMBER OF c.product")
    Optional<CartOrderModel> findByOrderedAndUserAndProduct(@Param("ordered") boolean ordered,
            @Param("user") UserModel user, @Param("product") ProductModel product);

}