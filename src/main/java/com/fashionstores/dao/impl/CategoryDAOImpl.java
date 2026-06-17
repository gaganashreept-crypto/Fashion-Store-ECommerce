package com.fashionstores.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstores.dao.CategoryDAO;
import com.fashionstores.model.Category;
import com.fashionstores.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    // CONNECTION OBJECT
    private Connection connection;

    // CONSTRUCTOR
    public CategoryDAOImpl() {

        connection = DBConnection.getConnection();

        // TEST
        if(connection == null) {

            System.out.println("Connection is NULL");
        }
        else {

            System.out.println("Connection Successful");
        }
    }

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM categories";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Category category = new Category();

                category.setCategoryId(
                        rs.getInt("category_id"));

                category.setCategoryName(
                        rs.getString("category_name"));

                categories.add(category);
            }

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return categories;
    }

	@Override
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
}