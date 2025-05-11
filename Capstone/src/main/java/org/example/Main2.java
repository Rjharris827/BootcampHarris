package org.example;


import java.io.*;
import java.util.*;

public class Main2 {

    private static final String CSV_FILE = "ledger.csv";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Ensure that CSV file exists
        createFileIfNotExist();

        // Display home screen options
        while (true) {
            System.out.println("\n----- Home Screen -----");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    viewLedger();
                    break;
                case "X":
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Ensure CSV file exists or create one
    private static void createFileIfNotExist() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (FileWriter writer = new FileWriter(CSV_FILE, true);
                     BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    bufferedWriter.write("date|time|description|vendor|amount\n");  // Header
                }
            } catch (IOException e) {
                System.out.println("Error creating the file: " + e.getMessage());
            }
        }
    }

    // Add deposit to the CSV file
    private static void addDeposit() {
        System.out.print("Enter deposit description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount (positive number): ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount < 0) {
            System.out.println("Amount must be positive for deposits.");
            return;
        }

        String dateTime = getCurrentDateTime();
        saveTransactionToFile(dateTime, description, vendor, amount);
        System.out.println("Deposit added successfully!");
    }

    // Make payment (debit) and save to the CSV file
    private static void makePayment() {
        System.out.print("Enter payment description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount (negative number for debit): ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > 0) {
            System.out.println("Amount must be negative for payments.");
            return;
        }

        String dateTime = getCurrentDateTime();
        saveTransactionToFile(dateTime, description, vendor, amount);
        System.out.println("Payment made successfully!");
    }

    // Display the ledger (CSV contents)
    private static void viewLedger() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    // Helper method to get the current date and time in the format YYYY-MM-DD|HH:MM:SS
    private static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return String.format("%04d-%02d-%02d|%02d:%02d:%02d", year, month, day, hour, minute, second);
    }

    // Helper method to save a transaction to the CSV file
    private static void saveTransactionToFile(String dateTime, String description, String vendor, double amount) {
        try (FileWriter writer = new FileWriter(CSV_FILE, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(String.format("%s|%s|%s|%s|%.2f\n", dateTime.split("\\|")[0], dateTime.split("\\|")[1], description, vendor, amount));
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}

