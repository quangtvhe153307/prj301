/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {
    private int productID;
    private String name, userID;
    private int categoryID;
    private String quantityPerUnit;
    private double price;
    private int unitsInStock, UnitsInOrder;
    private String image;
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public Product() {
    }

    public Product(int productID, String name, String userID, int categoryID, String quantityPerUnit, double price, int unitsInStock, int UnitsInOrder, String image, String brand) {
        this.productID = productID;
        this.name = name;
        this.userID = userID;
        this.categoryID = categoryID;
        this.quantityPerUnit = quantityPerUnit;
        this.price = price;
        this.unitsInStock = unitsInStock;
        this.UnitsInOrder = UnitsInOrder;
        this.image = image;
        this.brand = brand;
    }




    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsInOrder() {
        return UnitsInOrder;
    }

    public void setUnitsInOrder(int UnitsInOrder) {
        this.UnitsInOrder = UnitsInOrder;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    
}
