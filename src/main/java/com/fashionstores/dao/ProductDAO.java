package com.fashionstores.dao;

import java.util.List;

import com.fashionstores.model.Product;

public interface ProductDAO {

    // Add Product
    boolean addProduct(Product product);

    // Update Product
    boolean updateProduct(Product product);

    // Delete Product
    boolean deleteProduct(int productId);

    // Get Product By ID
    Product getProductById(int productId);

    // Get All Products
    List<Product> getAllProducts();

    // Get Products By Category
    List<Product> getProductsByCategory(int categoryId);

    // Search Products
    List<Product> searchProducts(String keyword);
}