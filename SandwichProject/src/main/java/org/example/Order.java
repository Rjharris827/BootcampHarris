package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order {
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<String> drinks = new ArrayList<>();
    private List<String> chips = new ArrayList<>();

    public void addSandwich(Sandwich s) {
        sandwiches.add(s);
    }

    public void addDrink(String d) {
        drinks.add(d);
    }

    public void addChips(String c) {
        chips.add(c);
    }

    public void checkout() {
        double total = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Order Summary:\n");
        for (Sandwich s : sandwiches) {
            sb.append(s.toString()).append("\n");
            total += s.getPrice();
        }

        sb.append("Drinks: ").append(drinks).append("\n");
        sb.append("Chips: ").append(chips).append("\n");
        total += drinks.size() * 2.50;
        total += chips.size() * 1.50;

        sb.append("Total: $").append(String.format("%.2f", total)).append("\n");

        System.out.println(sb);
        saveReceipt(sb.toString());
    }

    private void saveReceipt(String content) {
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        File dir = new File("receipts");
        if (!dir.exists()) dir.mkdir();

        try (PrintWriter writer = new PrintWriter(new File(dir, timestamp + ".txt"))) {
            writer.print(content);
            System.out.println("Receipt saved as: " + timestamp + ".txt");
        } catch (IOException e) {
            System.out.println("Failed to save receipt.");
        }
    }
}