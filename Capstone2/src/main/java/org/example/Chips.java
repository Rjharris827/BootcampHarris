package org.example;

public class Chips extends Product {
    public Chips() {
        super("Chips", 1.50);
    }

    @Override
    public void displayDetails() {
        System.out.println("Chips");
        System.out.println("Price: $" + price);
    }
}
