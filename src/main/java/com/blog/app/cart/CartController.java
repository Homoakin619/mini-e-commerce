package com.blog.app.cart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.appuser.AppUserRepository;
import com.blog.app.appuser.UserModel;
import com.blog.app.product.ProductModel;
import com.blog.app.product.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("api/v1/carts")
@RestController
public class CartController {
    private final CartOrderService orderService;
    private final AppUserRepository userRepository;
    private final ProductRepository productRepository;

    @PostMapping
    public String createOrderItem(@RequestBody OrderDTO order) {
        CartOrderModel orderItem = new CartOrderModel();
        UserModel user = userRepository.findUserWithEmail(order.getEmail());
        ProductModel product = productRepository.getProductById(order.getProductId());
        // product.setOrderItem(orderItem);
        // productRepository.save(product);

        orderItem.setQuantity(order.getQuantity());
        orderService.createOrder(orderItem, product, user);
        return "order Item created!";
    }
}
