package com.fashionstores.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.fashionstores.dao.OrderItemDAO;
import com.fashionstores.model.OrderItem;
import com.fashionstores.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {
	
	private Connection connection;

	public OrderItemDAOImpl() {

	    connection = DBConnection.getConnection();
	}

    // Add Order Item
    @Override
    public boolean addOrderItem(OrderItem orderItem) {

        boolean isAdded = false;

        String query = "INSERT INTO order_items(order_id, variant_id, quantity, price) VALUES (?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getVariantId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isAdded = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isAdded;
    }

    // Update Order Item
    @Override
    public boolean updateOrderItem(OrderItem orderItem) {

        boolean isUpdated = false;

        String query = "UPDATE order_items SET order_id = ?, variant_id = ?, quantity = ?, price = ? WHERE order_item_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getVariantId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getPrice());
            preparedStatement.setInt(5, orderItem.getOrderItemId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isUpdated = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isUpdated;
    }

    // Delete Order Item
    @Override
    public boolean deleteOrderItem(int orderItemId) {

        boolean isDeleted = false;

        String query = "DELETE FROM order_items WHERE order_item_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderItemId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isDeleted = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isDeleted;
    }

    // Get Order Item By ID
    @Override
    public OrderItem getOrderItemById(int orderItemId) {

        OrderItem orderItem = null;

        String query = "SELECT * FROM order_items WHERE order_item_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderItemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                orderItem = new OrderItem();

                orderItem.setOrderItemId(resultSet.getInt("order_item_id"));
                orderItem.setOrderId(resultSet.getInt("order_id"));
                orderItem.setVariantId(resultSet.getInt("variant_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setPrice(resultSet.getDouble("price"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return orderItem;
    }

    // Get Order Items By Order ID
    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> orderItems = new ArrayList<>();

        String query = "SELECT * FROM order_items WHERE order_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                OrderItem orderItem = new OrderItem();

                orderItem.setOrderItemId(resultSet.getInt("order_item_id"));
                orderItem.setOrderId(resultSet.getInt("order_id"));
                orderItem.setVariantId(resultSet.getInt("variant_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setPrice(resultSet.getDouble("price"));

                orderItems.add(orderItem);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return orderItems;
    }
}