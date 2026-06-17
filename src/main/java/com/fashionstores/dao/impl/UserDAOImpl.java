package com.fashionstores.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.fashionstores.dao.UserDAO;
import com.fashionstores.model.User;
import com.fashionstores.util.DBConnection;

public class UserDAOImpl implements UserDAO {
	
	private Connection connection;

	public UserDAOImpl() {

	    connection = DBConnection.getConnection();
	}

    // Register User
    @Override
    public boolean registerUser(User user) {

        boolean isRegistered = false;

        String query = "INSERT INTO users(name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isRegistered = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isRegistered;
    }

    // Login User
    @Override
    public User loginUser(String email, String password) {

        User user = null;

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    // Get User By ID
    @Override
    public User getUserById(int userId) {

        User user = null;

        String query = "SELECT * FROM users WHERE user_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    // Update User
    @Override
    public boolean updateUser(User user) {

        boolean isUpdated = false;

        String query = "UPDATE users SET name = ?, email = ?, password = ?, phone = ?, address = ? WHERE user_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setInt(6, user.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isUpdated = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isUpdated;
    }

    // Delete User
    @Override
    public boolean deleteUser(int userId) {

        boolean isDeleted = false;

        String query = "DELETE FROM users WHERE user_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                isDeleted = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isDeleted;
    }

    // Get All Users
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));

                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }

    // Check Email Exists
    @Override
    public boolean isEmailExists(String email) {

        boolean exists = false;

        String query = "SELECT * FROM users WHERE email = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                exists = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return exists;
    }
}
