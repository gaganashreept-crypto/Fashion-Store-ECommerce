package com.fashionstores.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.fashionstores.dao.CartItemDAO;
import com.fashionstores.model.CartItem;
import com.fashionstores.util.DBConnection;

public class CartItemDAOImpl implements CartItemDAO {
	
	private Connection connection;

	public CartItemDAOImpl() {

	    connection = DBConnection.getConnection();
	}

    // Add Cart Item
    @Override
    public boolean addCartItem(CartItem cartItem) {

        boolean isAdded = false;

        String query = "INSERT INTO cart_items(cart_id, variant_id, quantity) VALUES (?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItem.getCartId());
            preparedStatement.setInt(2, cartItem.getVariantId());
            preparedStatement.setInt(3, cartItem.getQuantity());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isAdded = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isAdded;
    }

    // Update Cart Item Quantity
    @Override
    public boolean updateCartItemQuantity(int cartItemId, int quantity) {

        boolean isUpdated = false;

        String query = "UPDATE cart_items SET quantity = ? WHERE cart_item_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, cartItemId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isUpdated = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isUpdated;
    }

    // Remove Cart Item
    @Override
    public boolean removeCartItem(int cartItemId) {

        boolean isRemoved = false;

        String query = "DELETE FROM cart_items WHERE cart_item_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItemId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isRemoved = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isRemoved;
    }

    // Get Cart Item By ID
    @Override
    public CartItem getCartItemById(int cartItemId) {

        CartItem cartItem = null;

        String query = "SELECT * FROM cart_items WHERE cart_item_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setVariantId(resultSet.getInt("variant_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return cartItem;
    }

    // Get Cart Items By Cart ID
    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {

        List<CartItem> cartItems = new ArrayList<>();

        String query = "SELECT * FROM cart_items WHERE cart_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                CartItem cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setVariantId(resultSet.getInt("variant_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));

                cartItems.add(cartItem);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return cartItems;
    }

    // Clear Cart Items
    @Override
    public boolean clearCartItems(int cartId) {

        boolean isCleared = false;

        String query = "DELETE FROM cart_items WHERE cart_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected >= 0) {

                isCleared = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isCleared;
    }
}
