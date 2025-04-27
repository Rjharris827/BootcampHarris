package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    // Add product to cart
    public void addProductToCart(Product product) {
        products.add(product);
        System.out.println(product.getProductName() + " added to cart.");
    }

    // Remove product from cart by SKU
    public void removeProduct(String SKU) {
        Product toRemove = null;
        for (Product product : products) {
            if (product.getSKU().equalsIgnoreCase(SKU)) {
                toRemove = product;
                break;
            }
        }
        if (toRemove != null) {
            products.remove(toRemove);
            System.out.println(toRemove.getProductName() + " removed from cart.");
        } else {
            System.out.println("Product with SKU " + SKU + " not found in cart.");
        }
    }

    // Get total cost of items in cart
    public double getCartTotal() {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    // Display all items in the cart
    public void displayItems() {
        if (products.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (Product product : products) {
                System.out.printf("SKU: %s | Name: %s | Price: $%.2f | Department: %s%n",
                        product.getSKU(), product.getProductName(), product.getPrice(), product.getDepartment());
            }
        }
    }
}