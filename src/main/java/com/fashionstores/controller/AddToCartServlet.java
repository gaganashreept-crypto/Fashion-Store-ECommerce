package com.fashionstores.controller;

import java.io.IOException;

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

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // SESSION

        HttpSession session =
                request.getSession();

        // CHECK LOGIN

        User user =
                (User) session.getAttribute("loggedInUser");

        if(user == null){

            response.sendRedirect("login");

            return;
        }

        // GET USER ID

        int userId =
                user.getUserId();

        // GET FORM DATA

        int variantId =
                Integer.parseInt(
                        request.getParameter("variantId"));

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity"));

        // DAO OBJECT

        CartDAO cartDAO =
                new CartDAOImpl();

        // GET USER CART

        Cart cart =
                cartDAO.getCartByUserId(userId);

        // CREATE CART IF NOT EXISTS

        if(cart == null){

            cartDAO.createCart(userId);

            // FETCH CREATED CART AGAIN

            cart =
                    cartDAO.getCartByUserId(userId);
        }

        // CREATE CART ITEM

        CartItem cartItem =
                new CartItem();

        cartItem.setCartId(
                cart.getCartId());

        cartItem.setVariantId(
                variantId);

        cartItem.setQuantity(
                quantity);

        // ADD ITEM TO CART

        cartDAO.addItemToCart(cartItem);

        // REDIRECT TO CART PAGE

        response.sendRedirect("cart");
    }
}