package com.fashionstores.dao;

import java.util.List;

import com.fashionstores.model.OrderItem;

public interface OrderItemDAO {

    // Add Order Item
    boolean addOrderItem(OrderItem orderItem);

    // Update Order Item
    boolean updateOrderItem(OrderItem orderItem);

    // Delete Order Item
    boolean deleteOrderItem(int orderItemId);

    // Get Order Item By ID
    OrderItem getOrderItemById(int orderItemId);

    // Get Order Items By Order ID
    List<OrderItem> getOrderItemsByOrderId(int orderId);
}