package org.example;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Loading csv file");
        List<Product> products = FileLoader.readFile(); // Load product list
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        Product foundProduct = findBySku(products, "AC100");
        if (foundProduct != null) {
            cart.addProductToCart(foundProduct);
        }

        while (true) {
            System.out.println("\nWelcome to the store! Choose an option:");
            System.out.println("1. View all products");
            System.out.println("2. Search by SKU");
            System.out.println("3. Search by price range");
            System.out.println("4. Search by name");
            System.out.println("5. Add to cart");
            System.out.println("6. Remove from cart");
            System.out.println("7. View cart");
            System.out.println("8. Checkout");
            System.out.println("9. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayProducts(products);
                    break;
                case 2:
                    System.out.print("Enter SKU: ");
                    String skuSearch = scanner.nextLine();
                    foundProduct = findBySku(products, skuSearch);
                    if (foundProduct != null) {
                        displayProducts(List.of(foundProduct));
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter minimum price: ");
                    double min = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter maximum price: ");
                    double max = Double.parseDouble(scanner.nextLine());
                    List<Product> filteredByPrice = filterByPriceRange(products, min, max);
                    displayProducts(filteredByPrice);
                    break;
                case 4:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    List<Product> nameMatches = searchByName(products, name);
                    displayProducts(nameMatches);
                    break;
                case 5:
                    System.out.print("Enter SKU to add to cart: ");
                    String skuToAdd = scanner.nextLine();
                    Product productToAdd = findBySku(products, skuToAdd);
                    if (productToAdd != null) {
                        cart.addProductToCart(productToAdd);
                        System.out.println("Added to cart.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter SKU to remove from cart: ");
                    String skuToRemove = scanner.nextLine();
                    cart.removeProduct(skuToRemove);
                    break;
                case 7:
                    cart.displayItems();
                    break;
                case 8:
                    cart.displayItems();
                    System.out.printf("Total: $%.2f%n", cart.getCartTotal());
                    System.out.println("Thank you for shopping!");
                    return;
                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products to display.");
            return;
        }
        for (Product product : products) {
            System.out.printf("SKU: %s | Name: %s | Price: $%.2f | Dept: %s%n",
                    product.getSKU(), product.getProductName(), product.getPrice(), product.getDepartment());
        }
    }

    public static Product findBySku(List<Product> products, String sku) {
        for (Product product : products) {
            if (product.getSKU().equalsIgnoreCase(sku)) {
                return product;
            }
        }
        return null;
    }

    public static List<Product> searchByName(List<Product> products, String name) {
        List<Product> matches = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                matches.add(product);
            }
        }
        return matches;
    }

    public static List<Product> filterByPriceRange(List<Product> products, double min, double max) {
        List<Product> matches = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                matches.add(product);
            }
        }
        return matches;
    }
}



