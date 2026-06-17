package com.fashionstores.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstores.dao.ProductDAO;
import com.fashionstores.dao.impl.ProductDAOImpl;
import com.fashionstores.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // DAO OBJECT
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // FETCH PRODUCTS
        List<Product> products =
                productDAO.getAllProducts();

        // SEND TO JSP
        request.setAttribute(
                "products",
                products);

        // FORWARD TO JSP
        request.getRequestDispatcher(
                "/WEB-INF/products.jsp")
                .forward(request, response);
    }
}