package com.fashionstores.dao;

import java.util.List;

import com.fashionstores.model.CartItem;

public interface CartItemDAO {

    // Add Cart Item
    boolean addCartItem(CartItem cartItem);

    // Update Cart Item Quantity
    boolean updateCartItemQuantity(int cartItemId, int quantity);

    // Remove Cart Item
    boolean removeCartItem(int cartItemId);

    // Get Cart Item By ID
    CartItem getCartItemById(int cartItemId);

    // Get Cart Items By Cart ID
    List<CartItem> getCartItemsByCartId(int cartId);

    // Clear Cart Items
    boolean clearCartItems(int cartId);
}