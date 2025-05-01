package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    static final String FILE_NAME = "ledger.csv";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nHome Screen:");
            System.out.println("1. Add Deposit");
            System.out.println("2. Make Payment");
            System.out.println("3. View Ledger");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addDeposit();
                    break;
                case "2":
                    makePayment();
                    break;
                case "3":
                    viewLedger();
                    break;
                case "4":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void addDeposit() {
        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (amount <= 0) {
            System.out.println("Deposit must be a positive amount.");
            return;
        }
        saveTransaction("Deposit", amount);
        System.out.println("Deposit added.");
    }

    public static void makePayment() {
        System.out.print("Enter payment amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -Math.abs(amount); // Ensure it's negative
        saveTransaction("Payment", amount);
        System.out.println("Payment recorded.");
    }

    public static void viewLedger() {
        List<String[]> transactions = loadTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\nView Options:");
        System.out.println("1. All Transactions");
        System.out.println("2. Deposits Only");
        System.out.println("3. Payments Only");
        System.out.print("Choose an option: ");
        String filterChoice = scanner.nextLine();

        transactions.sort(Comparator.comparing((String[] t) -> t[0]).reversed());

        for (String[] t : transactions) {
            String type = t[1];
            if (("2".equals(filterChoice) && !"Deposit".equals(type)) ||
                    ("3".equals(filterChoice) && !"Payment".equals(type))) {
                continue;
            }
            System.out.printf("%s | %-8s | %s%n", t[0], t[1], t[2]);
        }
    }

    public static void saveTransaction(String type, double amount) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            String line = String.format("%s,%s,%.2f%n", LocalDateTime.now(), type, amount);
            fw.write(line);
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static List<String[]> loadTransactions() {
        List<String[]> transactions = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return transactions;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) transactions.add(parts);
            }
        } catch (IOException e) {
            System.out.println("Error reading ledger: " + e.getMessage());
        }
        return transactions;
    }
}

































