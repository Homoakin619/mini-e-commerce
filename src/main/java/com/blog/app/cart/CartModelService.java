package com.blog.app.cart;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CartModelService {
    private final CartModelRepository cartRepository;

    public void createCart(CartModel cart) {
        cartRepository.save(cart);
    }

    @Transactional
    public void updateCart(Long id, ArrayList<CartOrderModel> items) {
        CartModel cart = cartRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("cart does not exist"));
        // cart.setOrderItems(items);
    }

    public CartModel getOrderCart(CartOrderModel order) {
        return cartRepository.fetchCart(order);
    }

}
