package org.example;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> products;

    public Order() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayOrderDetails() {
        double total = 0.0;
        System.out.println("Order Details:");
        for (Product product : products) {
            product.displayDetails();
            total += product.price;
        }
        System.out.println("Total Price: $" + total);
    }
}