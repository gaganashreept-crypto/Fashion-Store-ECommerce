package com.fashionstores.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.fashionstores.dao.OrderDAO;
import com.fashionstores.model.Order;
import com.fashionstores.model.OrderItem;
import com.fashionstores.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {
	
	private Connection connection;

	public OrderDAOImpl() {

	    connection = DBConnection.getConnection();
	}
//  placeOrder

	@Override
	public int placeOrder(Order order) {

	    int orderId = 0;

	    String query =
	        "INSERT INTO orders(user_id, total_amount, status, address) VALUES (?, ?, ?, ?)";

	    try {

	        PreparedStatement preparedStatement =
	            connection.prepareStatement(
	                query,
	                PreparedStatement.RETURN_GENERATED_KEYS);

	        preparedStatement.setInt(
	            1,
	            order.getUserId());

	        preparedStatement.setDouble(
	            2,
	            order.getTotalAmount());

	        preparedStatement.setString(
	            3,
	            order.getStatus());

	        preparedStatement.setString(
	            4,
	            order.getAddress());

	        int rowsAffected =
	            preparedStatement.executeUpdate();

	        if(rowsAffected > 0){

	            ResultSet resultSet =
	                preparedStatement.getGeneratedKeys();

	            if(resultSet.next()){

	                orderId =
	                    resultSet.getInt(1);
	            }
	        }

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return orderId;
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

    // Get Order By ID
    @Override
    public Order getOrderById(int orderId) {

        Order order = null;

        String query = "SELECT * FROM orders WHERE order_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                order = new Order();

                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                order.setStatus(resultSet.getString("status"));
                order.setAddress(resultSet.getString("address"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return order;
    }

    // Get Orders By User ID
    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders WHERE user_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Order order = new Order();

                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                order.setStatus(resultSet.getString("status"));
                order.setAddress(resultSet.getString("address"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));

                orders.add(order);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return orders;
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

    // Update Order Status
    @Override
    public boolean updateOrderStatus(int orderId, String status) {

        boolean isUpdated = false;

        String query = "UPDATE orders SET status = ? WHERE order_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, orderId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isUpdated = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isUpdated;
    }

    // Cancel Order
    @Override
    public boolean cancelOrder(int orderId) {

        boolean isCancelled = false;

        String query = "UPDATE orders SET status = 'Cancelled' WHERE order_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isCancelled = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isCancelled;
    }
}
