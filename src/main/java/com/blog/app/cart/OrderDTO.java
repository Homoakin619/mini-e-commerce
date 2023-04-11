package com.blog.app.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private String email;
    private Long productId;
    private Integer quantity;

}
