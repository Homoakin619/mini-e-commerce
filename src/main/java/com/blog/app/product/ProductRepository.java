package com.blog.app.product;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    // @Query("SELECT a FROM ProductModel a WHERE a.category= :category")
    // List<ProductModel> findByCategory(String category);
    List<ProductModel> findByCategory(ProductCategory category);

    @Query("SELECT a FROM ProductModel a WHERE a.id= ?1")
    ProductModel getProductById(Long id);

}
