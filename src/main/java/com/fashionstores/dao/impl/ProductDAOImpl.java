package com.fashionstores.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstores.dao.ProductDAO;
import com.fashionstores.model.Product;
import com.fashionstores.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private Connection connection;



    // CONSTRUCTOR

    public ProductDAOImpl() {

        connection = DBConnection.getConnection();
    }



    // ADD PRODUCT

    @Override
    public boolean addProduct(Product product) {

        try {

            String sql =

                    "INSERT INTO products " +

                    "(name, description, price, image_url, category_id) " +

                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, product.getName());

            ps.setString(2, product.getDescription());

            ps.setDouble(3, product.getPrice());

            ps.setString(4, product.getImageUrl());

            ps.setInt(5, product.getCategoryId());

            int rows = ps.executeUpdate();

            return rows > 0;

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }



    // UPDATE PRODUCT

    @Override
    public boolean updateProduct(Product product) {

        try {

            String sql =

                    "UPDATE products " +

                    "SET name=?, description=?, price=?, " +

                    "image_url=?, category_id=? " +

                    "WHERE product_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, product.getName());

            ps.setString(2, product.getDescription());

            ps.setDouble(3, product.getPrice());

            ps.setString(4, product.getImageUrl());

            ps.setInt(5, product.getCategoryId());

            ps.setInt(6, product.getProductId());

            int rows = ps.executeUpdate();

            return rows > 0;

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }



    // DELETE PRODUCT

    @Override
    public boolean deleteProduct(int productId) {

        try {

            String sql =
                    "DELETE FROM products WHERE product_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, productId);

            int rows = ps.executeUpdate();

            return rows > 0;

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }



    // GET PRODUCT BY ID

    @Override
    public Product getProductById(int productId) {

        Product product = null;

        try {

            String sql =

                    "SELECT p.*, c.category_name " +

                    "FROM products p " +

                    "JOIN categories c " +

                    "ON p.category_id = c.category_id " +

                    "WHERE p.product_id = ?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                product = new Product();

                product.setProductId(
                        rs.getInt("product_id"));

                product.setName(
                        rs.getString("name"));

                product.setDescription(
                        rs.getString("description"));

                product.setPrice(
                        rs.getDouble("price"));

                product.setImageUrl(
                        rs.getString("image_url"));

                product.setCategoryId(
                        rs.getInt("category_id"));

                product.setCategoryName(
                        rs.getString("category_name"));
            }

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return product;
    }



    // GET ALL PRODUCTS

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        try {

            String sql =

                    "SELECT p.*, c.category_name " +

                    "FROM products p " +

                    "JOIN categories c " +

                    "ON p.category_id = c.category_id";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Product product = new Product();

                product.setProductId(
                        rs.getInt("product_id"));

                product.setName(
                        rs.getString("name"));

                product.setDescription(
                        rs.getString("description"));

                product.setPrice(
                        rs.getDouble("price"));

                product.setImageUrl(
                        rs.getString("image_url"));

                product.setCategoryId(
                        rs.getInt("category_id"));

                product.setCategoryName(
                        rs.getString("category_name"));

                products.add(product);
            }

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return products;
    }



    // GET PRODUCTS BY CATEGORY

    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> products = new ArrayList<>();

        try {

            String sql =

                    "SELECT p.*, c.category_name " +

                    "FROM products p " +

                    "JOIN categories c " +

                    "ON p.category_id = c.category_id " +

                    "WHERE p.category_id = ?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Product product = new Product();

                product.setProductId(
                        rs.getInt("product_id"));

                product.setName(
                        rs.getString("name"));

                product.setDescription(
                        rs.getString("description"));

                product.setPrice(
                        rs.getDouble("price"));

                product.setImageUrl(
                        rs.getString("image_url"));

                product.setCategoryId(
                        rs.getInt("category_id"));

                product.setCategoryName(
                        rs.getString("category_name"));

                products.add(product);
            }

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return products;
    }



    // SEARCH PRODUCTS

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> products = new ArrayList<>();

        try {

            String sql =

                    "SELECT p.*, c.category_name " +

                    "FROM products p " +

                    "JOIN categories c " +

                    "ON p.category_id = c.category_id " +

                    "WHERE p.name LIKE ?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Product product = new Product();

                product.setProductId(
                        rs.getInt("product_id"));

                product.setName(
                        rs.getString("name"));

                product.setDescription(
                        rs.getString("description"));

                product.setPrice(
                        rs.getDouble("price"));

                product.setImageUrl(
                        rs.getString("image_url"));

                product.setCategoryId(
                        rs.getInt("category_id"));

                product.setCategoryName(
                        rs.getString("category_name"));

                products.add(product);
            }

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return products;
    }
}