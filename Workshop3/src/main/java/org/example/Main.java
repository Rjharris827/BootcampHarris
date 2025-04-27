package org.example;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args){
    System.out.println("Loading csv file");
    List<Product> products = FileLoader.readFile();

    ShoppingCart cart = new ShoppingCart();

    Product foundProduct = searchProductBySKU(products, "AC100");
    cart.addProductToCart(foundProduct);

        while (true) {
            System.out.println("Welcome to the store! Choose an option:");
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
                    displayProducts(allProducts);
                    break;
                case 2:
                    System.out.print("Enter SKU: ");
                    String skuSearch = scanner.nextLine();
                    Product foundProduct = findBySku(allProducts, skuSearch);
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
                    List<Product> filteredByPrice = filterByPriceRange(allProducts, min, max);
                    displayProducts(filteredByPrice);
                    break;
                case 4:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    List<Product> nameMatches = searchByName(allProducts, name);
                    displayProducts(nameMatches);
                    break;
                case 5:
                    System.out.print("Enter SKU to add to cart: ");
                    String skuToAdd = scanner.nextLine();
                    Product productToAdd = findBySku(allProducts, skuToAdd);
                    if (productToAdd != null) {
                        cart.addToCart(productToAdd);
                        System.out.println("Added to cart.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter SKU to remove from cart: ");
                    String skuToRemove = scanner.nextLine();
                    cart.removeFromCart(skuToRemove);
                    System.out.println("Removed from cart if it existed.");
                    break;
                case 7:
                    cart.viewCart();
                    break;
                case 8:
                    cart.viewCart();
                    System.out.println("Total: $" + cart.getTotal());
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
                    product.getSku(), product.getName(), product.getPrice(), product.getDepartment());
        }
    }

    public static Product findBySku(List<Product> products, String sku) {
        for (Product product : products) {
            if (product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }
        return null;
    }

    public static List<Product> searchByName(List<Product> products, String name) {
        List<Product> matches = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
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





