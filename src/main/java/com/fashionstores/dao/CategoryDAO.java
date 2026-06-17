package com.fashionstores.dao;

import java.util.List;

import com.fashionstores.model.Category;

public interface CategoryDAO {

    // Add Category
    boolean addCategory(Category category);

    // Update Category
    boolean updateCategory(Category category);

    // Delete Category
    boolean deleteCategory(int categoryId);

    // Get Category By ID
    Category getCategoryById(int categoryId);

    // Get All Categories
    List<Category> getAllCategories();
}