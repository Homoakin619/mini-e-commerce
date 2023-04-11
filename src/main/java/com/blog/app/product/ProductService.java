package com.blog.app.product;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductModel getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(
                        () -> new IllegalStateException("Product of id " + productId + " does not exist"));
    }

    public List<ProductModel> getProductByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }

    public String createProducts(ProductModel[] products) {
        // productRepository.save(products);
        for (ProductModel item : products) {
            productRepository.save(item);
        }

        return "Products Created!!";
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }
}
