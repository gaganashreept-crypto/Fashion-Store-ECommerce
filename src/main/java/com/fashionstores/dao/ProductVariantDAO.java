package com.fashionstores.dao;

import java.util.List;

import com.fashionstores.model.ProductVariant;

public interface ProductVariantDAO {

    // Add Product Variant
    boolean addProductVariant(ProductVariant productVariant);

    // Update Product Variant
    boolean updateProductVariant(ProductVariant productVariant);

    // Delete Product Variant
    boolean deleteProductVariant(int variantId);

    // Get Variant By ID
    ProductVariant getVariantById(int variantId);

    // Get Variants By Product ID
    List<ProductVariant> getVariantsByProductId(int productId);

    // Update Stock Quantity
    boolean updateStockQuantity(int variantId, int stockQuantity);
}