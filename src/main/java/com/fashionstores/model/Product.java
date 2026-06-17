package com.fashionstores.model;

public class Product {

    private int productId;

    private String name;

    private String description;

    private double price;

    private String imageUrl;

    private int categoryId;

    // NEW
    private String categoryName;



    // DEFAULT CONSTRUCTOR

    public Product() {

    }



    // PARAMETERIZED CONSTRUCTOR

    public Product(int productId,
                   String name,
                   String description,
                   double price,
                   String imageUrl,
                   int categoryId,
                   String categoryName) {

        this.productId = productId;

        this.name = name;

        this.description = description;

        this.price = price;

        this.imageUrl = imageUrl;

        this.categoryId = categoryId;

        this.categoryName = categoryName;
    }



    // GETTERS & SETTERS

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }



    // CATEGORY NAME

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    // TO STRING

    @Override
    public String toString() {

        return "Product [productId=" + productId
                + ", name=" + name
                + ", description=" + description
                + ", price=" + price
                + ", imageUrl=" + imageUrl
                + ", categoryId=" + categoryId
                + ", categoryName=" + categoryName
                + "]";
    }
}