package com.fashionstores.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstores.dao.CartDAO;
import com.fashionstores.dao.OrderDAO;
import com.fashionstores.dao.impl.CartDAOImpl;
import com.fashionstores.dao.impl.OrderDAOImpl;
import com.fashionstores.model.Cart;
import com.fashionstores.model.CartItem;
import com.fashionstores.model.Order;
import com.fashionstores.model.OrderItem;
import com.fashionstores.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getUserId();

        // CHECKOUT PAGE
        if ("checkout".equals(action)) {
            CartDAO cartDAO = new CartDAOImpl();
            Cart cart = cartDAO.getCartByUserId(userId);
            List<CartItem> cartItems = cartDAO.getCartItems(cart.getCartId());

            double total = 0;
            for (CartItem item : cartItems) {
                total += item.getPrice() * item.getQuantity();
            }

            request.setAttribute("cartItems", cartItems);
            request.setAttribute("totalAmount", total);

            request.getRequestDispatcher("/WEB-INF/checkout.jsp")
                   .forward(request, response);
        }

        // VIEW ORDERS
        else if ("view".equals(action)) {
            OrderDAO orderDAO = new OrderDAOImpl();
            List<Order> orders = orderDAO.getOrdersByUserId(userId);

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/WEB-INF/orders.jsp")
                   .forward(request, response);
        }

        // TRACK ORDER
        else if ("track".equals(action)) {
            String orderIdParam = request.getParameter("orderId");
            if (orderIdParam == null) {
                response.sendRedirect("order?action=view");
                return;
            }

            int orderId = Integer.parseInt(orderIdParam);
            OrderDAO orderDAO = new OrderDAOImpl();
            Order order = orderDAO.getOrderById(orderId);

            if (order != null) {
                request.setAttribute("order", order);
                request.getRequestDispatcher("/WEB-INF/track-order.jsp")
                       .forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getUserId();
        String address = request.getParameter("address");

        CartDAO cartDAO = new CartDAOImpl();
        Cart cart = cartDAO.getCartByUserId(userId);
        List<CartItem> cartItems = cartDAO.getCartItems(cart.getCartId());

        double totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }

        // CREATE ORDER
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setStatus("PLACED");
        order.setAddress(address);

        OrderDAO orderDAO = new OrderDAOImpl();
        int orderId = orderDAO.placeOrder(order);

        // SAVE ORDER ITEMS
        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setVariantId(item.getVariantId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());
            orderDAO.addOrderItem(orderItem);
        }

        // CLEAR CART
        cartDAO.clearCart(cart.getCartId());

        response.sendRedirect("order?action=view");
    }
}
