package com.fashionstores.util;


import java.util.List;

import com.fashionstores.dao.ProductDAO;
import com.fashionstores.dao.impl.ProductDAOImpl;
import com.fashionstores.model.Product;

public class TestProductDAO {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAOImpl();

        List<Product> products = productDAO.getAllProducts();

        if (products.isEmpty()) {

            System.out.println("No Products Found");

        } else {

            for (Product product : products) {

                System.out.println("----------------------------------");

                System.out.println("Product ID : " + product.getProductId());
                System.out.println("Name       : " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Price      : " + product.getPrice());
                System.out.println("Category ID: " + product.getCategoryId());
                System.out.println("Image URL  : " + product.getImageUrl());
            }
        }
    }
}