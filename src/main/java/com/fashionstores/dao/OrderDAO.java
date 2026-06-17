package com.fashionstores.dao;

import java.util.List;

import com.fashionstores.model.Order;
import com.fashionstores.model.OrderItem;

public interface OrderDAO {

    // Place Order
	int placeOrder(Order order);

    // Add Order Item
    boolean addOrderItem(OrderItem orderItem);

    // Get Order By ID
    Order getOrderById(int orderId);

    // Get Orders By User ID
    List<Order> getOrdersByUserId(int userId);

    // Get Order Items By Order ID
    List<OrderItem> getOrderItemsByOrderId(int orderId);

    // Update Order Status
    boolean updateOrderStatus(int orderId, String status);

    // Cancel Order
    boolean cancelOrder(int orderId);
}
