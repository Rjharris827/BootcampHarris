package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Product> currentOrder = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Welcome to the Sandwich Shop ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> startNewOrder();
                case "0" -> {
                    System.out.println("Thank you! Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void startNewOrder() {
        currentOrder.clear();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n--- Order Menu ---");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink (coming soon)");
            System.out.println("3) Add Chips (coming soon)");
            System.out.println("4) View Order");
            System.out.println("5) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> addSandwich(); // Stub method
                case "2" -> System.out.println("Add Drink feature coming soon.");
                case "3" -> System.out.println("Add Chips feature coming soon.");
                case "4" -> viewOrder();
                case "5" -> {
                    checkout();
                    ordering = false;
                }
                case "0" -> {
                    System.out.println("Order canceled. Returning to home.");
                    ordering = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void addSandwich() {
        // Placeholder implementation
        System.out.println("Add Sandwich feature is not yet implemented.");
    }

    static void viewOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        double total = 0;
        System.out.println("\n--- Your Order ---");
        for (Product p : currentOrder) {
            System.out.println(p.getName() + " - $" + String.format("%.2f", p.calculatePrice()));
            total += p.getPrice();
        }
        System.out.println("Order Total: $" + String.format("%.2f", total));
    }

    static void checkout() {
        viewOrder();
        if (currentOrder.isEmpty()) return;

        System.out.print("Confirm checkout? (y/n): ");
        if (!scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Checkout cancelled.");
            return;
        }

        saveReceipt();
        System.out.println("Order confirmed and saved.");
    }

    static void saveReceipt() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            File folder = new File("receipts");
            if (!folder.exists()) folder.mkdir();

            FileWriter writer = new FileWriter("receipts/" + timestamp + ".txt");
            double total = 0;
            for (Product p : currentOrder) {
                writer.write(p.getName() + " - $" + String.format("%.2f", p.calculatePrice()) + "\n");
                total += p.getPrice();
            }
            writer.write("TOTAL: $" + String.format("%.2f", total));
            writer.close();

            System.out.println("Receipt saved as " + timestamp + ".txt");
        } catch (IOException e) {
            System.out.println("Failed to save receipt: " + e.getMessage());
        }
    }
}







