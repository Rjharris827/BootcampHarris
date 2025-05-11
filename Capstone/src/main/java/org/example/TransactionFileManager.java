package org.example;
import java.io.*;

public class TransactionFileManager {

    private static final File file = new File("src/main/resources/transaction.csv");

    // Method to read transactions (optional based on your needs)
    public static void readTransactions() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // You can parse and handle this line as needed
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions.");
            e.printStackTrace();
        }
    }

    public static void saveTransaction(Transaction transaction) {
        try {
            // Ensure parent folder exists
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Check if file is new or empty
            boolean fileExists = file.exists();
            boolean isEmpty = !fileExists || file.length() == 0;

            // Write to file
            FileWriter writer = new FileWriter(file, true);

            // Write header if file is empty
            if (isEmpty) {
                writer.write("date|time|description|vendor|amount\n");
            }

            // Write transaction line
            writer.write(transaction.toString() + "\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving the transaction.");
            e.printStackTrace();
        }
    }
}