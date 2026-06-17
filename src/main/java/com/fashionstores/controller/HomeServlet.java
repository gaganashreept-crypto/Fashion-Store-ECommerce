package com.fashionstores.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstores.dao.CategoryDAO;
import com.fashionstores.dao.ProductDAO;
import com.fashionstores.dao.impl.CategoryDAOImpl;
import com.fashionstores.dao.impl.ProductDAOImpl;
import com.fashionstores.model.Category;
import com.fashionstores.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // DAO OBJECTS
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {

        categoryDAO = new CategoryDAOImpl();
        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // FETCH DATA
        List<Category> categories =
                categoryDAO.getAllCategories();

        List<Product> products =
                productDAO.getAllProducts();

        // SEND DATA TO JSP
        request.setAttribute("categories", categories);

        request.setAttribute("products", products);

        // FORWARD
        request.getRequestDispatcher(
                "/WEB-INF/home.jsp")
                .forward(request, response);
    }
}