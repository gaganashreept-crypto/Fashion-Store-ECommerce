package com.fashionstores.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.fashionstores.dao.ProductVariantDAO;
import com.fashionstores.model.ProductVariant;
import com.fashionstores.util.DBConnection;

public class ProductVariantDAOImpl implements ProductVariantDAO {
	
	private Connection connection;

	public ProductVariantDAOImpl() {

	    connection = DBConnection.getConnection();
	}

    // Add Product Variant
    @Override
    public boolean addProductVariant(ProductVariant productVariant) {

        boolean isAdded = false;

        String query = "INSERT INTO product_variants(product_id, size, stock_quantity) VALUES (?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productVariant.getProductId());
            preparedStatement.setString(2, productVariant.getSize());
            preparedStatement.setInt(3, productVariant.getStockQuantity());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isAdded = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isAdded;
    }

    // Update Product Variant
    @Override
    public boolean updateProductVariant(ProductVariant productVariant) {

        boolean isUpdated = false;

        String query = "UPDATE product_variants SET product_id = ?, size = ?, stock_quantity = ? WHERE variant_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productVariant.getProductId());
            preparedStatement.setString(2, productVariant.getSize());
            preparedStatement.setInt(3, productVariant.getStockQuantity());
            preparedStatement.setInt(4, productVariant.getVariantId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isUpdated = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isUpdated;
    }

    // Delete Product Variant
    @Override
    public boolean deleteProductVariant(int variantId) {

        boolean isDeleted = false;

        String query = "DELETE FROM product_variants WHERE variant_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, variantId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isDeleted = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isDeleted;
    }

    // Get Variant By ID
    @Override
    public ProductVariant getVariantById(int variantId) {

        ProductVariant productVariant = null;

        String query = "SELECT * FROM product_variants WHERE variant_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, variantId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                productVariant = new ProductVariant();

                productVariant.setVariantId(resultSet.getInt("variant_id"));
                productVariant.setProductId(resultSet.getInt("product_id"));
                productVariant.setSize(resultSet.getString("size"));
                productVariant.setStockQuantity(resultSet.getInt("stock_quantity"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return productVariant;
    }

    // Get Variants By Product ID
    @Override
    public List<ProductVariant> getVariantsByProductId(int productId) {

        List<ProductVariant> productVariants = new ArrayList<>();

        String query = "SELECT * FROM product_variants WHERE product_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ProductVariant productVariant = new ProductVariant();

                productVariant.setVariantId(resultSet.getInt("variant_id"));
                productVariant.setProductId(resultSet.getInt("product_id"));
                productVariant.setSize(resultSet.getString("size"));
                productVariant.setStockQuantity(resultSet.getInt("stock_quantity"));

                productVariants.add(productVariant);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return productVariants;
    }

    // Update Stock Quantity
    @Override
    public boolean updateStockQuantity(int variantId, int stockQuantity) {

        boolean isUpdated = false;

        String query = "UPDATE product_variants SET stock_quantity = ? WHERE variant_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, stockQuantity);
            preparedStatement.setInt(2, variantId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isUpdated = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isUpdated;
    }
}
