package com.fashionstores.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.fashionstores.dao.CartDAO;
import com.fashionstores.model.Cart;
import com.fashionstores.model.CartItem;
import com.fashionstores.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    private Connection connection;

    public CartDAOImpl() {

        connection = DBConnection.getConnection();
    }

    // CREATE CART

    @Override
    public boolean createCart(int userId) {

        boolean isCreated = false;

        String query =
            "INSERT INTO cart(user_id) VALUES (?)";

        try {

            PreparedStatement preparedStatement =
                connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            int rowsAffected =
                preparedStatement.executeUpdate();

            if(rowsAffected > 0){

                isCreated = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isCreated;
    }

    // GET CART BY USER ID

    @Override
    public Cart getCartByUserId(int userId) {

        Cart cart = null;

        String query =
            "SELECT * FROM cart WHERE user_id = ?";

        try {

            PreparedStatement preparedStatement =
                connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet =
                preparedStatement.executeQuery();

            if(resultSet.next()){

                cart = new Cart();

                cart.setCartId(
                    resultSet.getInt("cart_id"));

                cart.setUserId(
                    resultSet.getInt("user_id"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return cart;
    }

    // ADD ITEM TO CART

    @Override
    public boolean addItemToCart(CartItem cartItem) {

        boolean isAdded = false;

        String query =
            "INSERT INTO cart_items(cart_id, variant_id, quantity) VALUES (?, ?, ?)";

        try {

            PreparedStatement preparedStatement =
                connection.prepareStatement(query);

            preparedStatement.setInt(
                1,
                cartItem.getCartId());

            preparedStatement.setInt(
                2,
                cartItem.getVariantId());

            preparedStatement.setInt(
                3,
                cartItem.getQuantity());

            int rowsAffected =
                preparedStatement.executeUpdate();

            if(rowsAffected > 0){

                isAdded = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isAdded;
    }

    // UPDATE QUANTITY

    @Override
    public boolean updateCartItemQuantity(int cartItemId,
                                          int quantity) {

        boolean isUpdated = false;

        String query =
            "UPDATE cart_items SET quantity = ? WHERE cart_item_id = ?";

        try {

            PreparedStatement preparedStatement =
                connection.prepareStatement(query);

            preparedStatement.setInt(1, quantity);

            preparedStatement.setInt(2, cartItemId);

            int rowsAffected =
                preparedStatement.executeUpdate();

            if(rowsAffected > 0){

                isUpdated = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isUpdated;
    }

    // REMOVE ITEM

    @Override
    public boolean removeItemFromCart(int cartItemId) {

        boolean isRemoved = false;

        String query =
            "DELETE FROM cart_items WHERE cart_item_id = ?";

        try {

            PreparedStatement preparedStatement =
                connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItemId);

            int rowsAffected =
                preparedStatement.executeUpdate();

            if(rowsAffected > 0){

                isRemoved = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isRemoved;
    }

    // GET CART ITEMS

    @Override
    public List<CartItem> getCartItems(int cartId) {

        List<CartItem> cartItems =
            new ArrayList<>();

        String query =
            "SELECT ci.*, p.name, p.price, p.image_url " +
            "FROM cart_items ci " +
            "JOIN products p " +
            "ON ci.variant_id = p.product_id " +
            "WHERE ci.cart_id = ?";

        try {

            PreparedStatement preparedStatement =
                connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet =
                preparedStatement.executeQuery();

            while(resultSet.next()){

                CartItem cartItem =
                    new CartItem();

                cartItem.setCartItemId(
                    resultSet.getInt("cart_item_id"));

                cartItem.setCartId(
                    resultSet.getInt("cart_id"));

                cartItem.setVariantId(
                    resultSet.getInt("variant_id"));

                cartItem.setQuantity(
                    resultSet.getInt("quantity"));

                cartItem.setProductName(
                    resultSet.getString("name"));

                cartItem.setPrice(
                    resultSet.getDouble("price"));

                cartItem.setImageUrl(
                    resultSet.getString("image_url"));

                cartItem.setSize("M");

                cartItems.add(cartItem);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return cartItems;
    }

    // CLEAR CART

    @Override
    public boolean clearCart(int cartId) {

        boolean isCleared = false;

        String query =
            "DELETE FROM cart_items WHERE cart_id = ?";

        try {

            PreparedStatement preparedStatement =
                connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            int rowsAffected =
                preparedStatement.executeUpdate();

            if(rowsAffected >= 0){

                isCleared = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isCleared;
    }
}