package org.example;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Sandwich Shop ---");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> handleNewOrder();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: Please enter a number.");
            }
        }
        System.out.println("Thanks for visiting!");
    }

    private static void handleNewOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n--- Order Menu ---");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 -> {
                        Sandwich sandwich = buildSandwich();
                        order.addSandwich(sandwich);
                    }
                    case 2 -> {
                        System.out.print("Drink size (small/medium/large): ");
                        order.addDrink(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Type of chips: ");
                        order.addChips(scanner.nextLine());
                    }
                    case 4 -> {
                        order.checkout();
                        ordering = false;
                    }
                    case 0 -> {
                        System.out.println("Order cancelled.");
                        ordering = false;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static Sandwich buildSandwich() {
        System.out.print("Select sandwich size (4, 8, 12): ");
        Size size = Size.fromInt(Integer.parseInt(scanner.nextLine()));

        System.out.println("Select bread type:");
        for (BreadType b : BreadType.values()) {
            System.out.println("- " + b);
        }
        BreadType bread = BreadType.valueOf(scanner.nextLine().toUpperCase());

        Sandwich sandwich = new Sandwich(size, bread);

        System.out.println("Add meats (type 'done' to finish):");
        for (Meat m : Meat.values()) {
            System.out.println("- " + m);
        }
        while (true) {
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("DONE")) break;
            try {
                sandwich.addMeat(Meat.valueOf(input));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid meat.");
            }
        }

        System.out.println("Add cheese (type 'done' to finish):");
        for (Cheese c : Cheese.values()) {
            System.out.println("- " + c);
        }
        while (true) {
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("DONE")) break;
            try {
                sandwich.addCheese(Cheese.valueOf(input));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid cheese.");
            }
        }

        System.out.print("Toasted? (yes/no): ");
        sandwich.setToasted(scanner.nextLine().equalsIgnoreCase("yes"));

        return sandwich;
    }
}






