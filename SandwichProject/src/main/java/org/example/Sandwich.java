package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private Size size;
    private BreadType bread;
    private boolean toasted = false;
    private List<Meat> meats = new ArrayList<>();
    private List<Cheese> cheeses = new ArrayList<>();

    public Sandwich(Size size, BreadType bread) {
        this.size = size;
        this.bread = bread;
    }

    public void addMeat(Meat meat) {
        meats.add(meat);
    }

    public void addCheese(Cheese cheese) {
        cheeses.add(cheese);
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public double getPrice() {
        double basePrice;
        double meatPrice;
        double cheesePrice;

        // Get base sandwich price
        switch (size) {
            case FOUR -> basePrice = 5.50;
            case EIGHT -> basePrice = 7.00;
            case TWELVE -> basePrice = 8.50;
            default -> throw new IllegalArgumentException("Invalid size.");
        }

        // Premium meat price per unit
        switch (size) {
            case FOUR -> meatPrice = 1.00;
            case EIGHT -> meatPrice = 2.00;
            case TWELVE -> meatPrice = 3.00;
            default -> meatPrice = 0;
        }

        // Premium cheese price per unit
        switch (size) {
            case FOUR-> cheesePrice = 0.75;
            case EIGHT -> cheesePrice = 1.50;
            case TWELVE -> cheesePrice = 2.25;
            default -> cheesePrice = 0;
        }

        double total = basePrice + (meatPrice * meats.size()) + (cheesePrice * cheeses.size());
        return total;
    }

    @Override
    public String toString() {
        return size + " sandwich on " + bread + " bread\n" +
                "Toasted: " + (toasted ? "Yes" : "No") + "\n" +
                "Meats: " + meats + "\n" +
                "Cheeses: " + cheeses + "\n" +
                "Price: $" + String.format("%.2f", getPrice()) + "\n";
    }
}