package com.fashionstores.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstores.dao.CartDAO;
import com.fashionstores.dao.impl.CartDAOImpl;
import com.fashionstores.model.Cart;
import com.fashionstores.model.CartItem;
import com.fashionstores.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // SHOW CART

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
            request.getSession();

        User user =
            (User) session.getAttribute("loggedInUser");

        if(user == null){

            response.sendRedirect("login");

            return;
        }

        int userId =
            user.getUserId();

        CartDAO cartDAO =
            new CartDAOImpl();

        Cart cart =
            cartDAO.getCartByUserId(userId);

        if(cart != null){

            List<CartItem> cartItems =
                cartDAO.getCartItems(
                    cart.getCartId());

            request.setAttribute(
                "cartItems",
                cartItems);
        }

        request.getRequestDispatcher(
            "/WEB-INF/cart.jsp")
            .forward(request, response);
    }

    // UPDATE / REMOVE

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action =
            request.getParameter("action");

        CartDAO cartDAO =
            new CartDAOImpl();

        // REMOVE ITEM

        if(action.equals("remove")){

            int cartItemId =
                Integer.parseInt(
                    request.getParameter(
                        "cartItemId"));

            cartDAO.removeItemFromCart(
                cartItemId);
        }

        // UPDATE QUANTITY

        if(action.equals("update")){

            int cartItemId =
                Integer.parseInt(
                    request.getParameter(
                        "cartItemId"));

            int quantity =
                Integer.parseInt(
                    request.getParameter(
                        "quantity"));

            cartDAO.updateCartItemQuantity(
                cartItemId,
                quantity);
        }

        response.sendRedirect("cart");
    }
}