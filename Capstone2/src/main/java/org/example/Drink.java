package org.example;

import enums.DrinkSize;

public class Drink extends Product {
    private DrinkSize size;

    public Drink(DrinkSize size) {
        super("Drink", 0.0);
        this.size = size;
        this.price = determinePrice();
    }

    private double determinePrice() {
        return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };
    }

    @Override
    public double calculatePrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Drink (%s) - $%.2f", size, price);
    }

    @Override
    public void displayDetails() {
        System.out.println(toString());
    }
}