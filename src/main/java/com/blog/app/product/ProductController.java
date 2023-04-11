package com.blog.app.product;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/v1/product")
@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public List<ProductModel> allProduct() {
        return productService.getAllProducts();
    }

    @PostMapping
    public String createProducts(@RequestBody ProductModel[] products) {
        return productService.createProducts(products);
    }

    @GetMapping(path = "/query")
    public List<ProductModel> getByCategory(@RequestParam ProductCategory category) {
        return productService.getProductByCategory(category);
    }
}
