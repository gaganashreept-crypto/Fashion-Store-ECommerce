package com.fashionstores.dao;

import java.util.List;

import com.fashionstores.model.Cart;
import com.fashionstores.model.CartItem;

public interface CartDAO {

    // Create Cart
    boolean createCart(int userId);

    // Get Cart By User ID
    Cart getCartByUserId(int userId);

    // Add Item To Cart
    boolean addItemToCart(CartItem cartItem);

    // Update Cart Item Quantity
    boolean updateCartItemQuantity(int cartItemId, int quantity);

    // Remove Item From Cart
    boolean removeItemFromCart(int cartItemId);

    // Get Cart Items
    List<CartItem> getCartItems(int cartId);

    // Clear Cart
    boolean clearCart(int cartId);
}