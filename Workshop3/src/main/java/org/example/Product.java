package org.example;

public class Product {
    private String SKU;
    private String ProductName;
    private String Department;
    private double Price;

    public Product(String SKU, String productName, String department, double price) {
        this.SKU = SKU;
        ProductName = productName;
        Department = department;
        Price = price;
    }









    public Product(String SKU) {
        this.SKU = SKU;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }



}
