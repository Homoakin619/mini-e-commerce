package com.blog.app.cart;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.app.appuser.UserModel;
import com.blog.app.product.ProductModel;
import com.blog.app.product.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CartOrderService {
    private final CartOrderRepository orderRepository;
    private final CartModelRepository cartRepository;
    private final ProductRepository productRepository;
    // private final CartModelService cartService;

    @Transactional
    public void createOrder(CartOrderModel order, ProductModel product, UserModel user) {
        // CartModel cartExists = cartRepository.getCartExists(order.getUser(), false)
        // .orElse(
        // new CartModel());

        boolean cartExists = cartRepository.getCartExists(user, false).isPresent();

        if (cartExists) {
            System.out.println("Cart exists!!");
            CartModel cart = cartRepository.getCart(user, false);

            System.out.println("userId: " + user.getId());

            boolean orderExists = orderRepository.findByOrderedAndUserAndProduct(false, user, product).isPresent();
            System.out.println(orderExists);
            if (orderExists) {
                System.out.println("orderExists");
                CartOrderModel orderFetch = orderRepository.findByOrderedAndUserAndProduct(false, user, product)
                        .orElseThrow(
                                () -> new IllegalStateException("Order does not exist!"));
                Integer quantity = orderFetch.getQuantity() + order.getQuantity();
                System.out.println("ARrived here");
                System.out.println(quantity);
                orderFetch.setQuantity(orderFetch.getQuantity() + order.getQuantity());
                orderRepository.save(orderFetch);
            } else {
                System.out.println("This is a new order item!!");
                order.setCart(cart);
                order.setUser(user);
                product.setOrderItem(order);
                productRepository.save(product);
                orderRepository.save(order);
            }
        } else {
            CartModel newCart = new CartModel();
            newCart.setUser(user);
            order.setUser(user);
            order.setCart(newCart);
            orderRepository.save(order);
            product.setOrderItem(order);
            productRepository.save(product);
            cartRepository.save(newCart);

        }

    }
}
