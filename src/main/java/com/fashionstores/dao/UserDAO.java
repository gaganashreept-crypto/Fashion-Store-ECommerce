package com.fashionstores.dao;

import java.util.List;

import com.fashionstores.model.User;

public interface UserDAO {

    // Register User
    boolean registerUser(User user);

    // Login User
    User loginUser(String email, String password);

    // Get User By ID
    User getUserById(int userId);

    // Update User
    boolean updateUser(User user);

    // Delete User
    boolean deleteUser(int userId);

    // Get All Users
    List<User> getAllUsers();

    // Check Email Exists
    boolean isEmailExists(String email);
}