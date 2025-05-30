package org.example;
import enums.*;
import java.util.*;

public class Sandwich extends Product {
    private BreadType bread;
    private SizeType size;
    private List<MeatType> meats = new ArrayList<>();
    private List<CheeseType> cheeses = new ArrayList<>();
    private List<ToppingType> toppings = new ArrayList<>();
    private boolean toasted;

    public Sandwich(BreadType bread, SizeType size) {
        this.bread = bread;
        this.size = size;
        this.name = "Custom Sandwich";
    }

    public void addMeat(MeatType meat) { meats.add(meat); }

    public void addCheese(CheeseType cheese) { cheeses.add(cheese); }

    public void addTopping(ToppingType topping) { toppings.add(topping); }

    public void setToasted(boolean toasted) { this.toasted = toasted; }

    @Override
    public double calculatePrice() {
        double basePrice = switch (size) {
            case FOUR_INCH -> 5.50;
            case EIGHT_INCH -> 7.00;
            case TWELVE_INCH -> 8.50;
        };

        double meatPrice = meats.size() * switch (size) {
            case FOUR_INCH -> 1.00;
            case EIGHT_INCH -> 2.00;
            case TWELVE_INCH -> 3.00;
        };

        double cheesePrice = cheeses.size() * switch (size) {
            case FOUR_INCH -> 0.75;
            case EIGHT_INCH -> 1.50;
            case TWELVE_INCH -> 2.25;
        };

        // Regular toppings and sauces are free
        this.price = basePrice + meatPrice + cheesePrice;
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s sandwich on %s bread\nMeats: %s\nCheeses: %s\nToppings: %s\nToasted: %s\nPrice: $%.2f",
                size, bread, meats, cheeses, toppings, toasted ? "Yes" : "No", calculatePrice());
    }
}