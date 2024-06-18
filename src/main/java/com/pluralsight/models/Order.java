package com.pluralsight.models;

import com.pluralsight.util.Text;

import java.util.*;

public class Order {
    protected final List<Sandwich> sandwiches;
    protected final List<Drink> drinks;
    protected final List<Chip> chips;
    protected final List<Side> sideList;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.sideList = new ArrayList<>();
    }

    public final Map<String, String> premiumToppings = new TreeMap<>(Map.of(
            "1", "Steak",
            "2", "Ham",
            "3", "Salami",
            "4", "Roast Beef",
            "5", "Chicken",
            "6", "Bacon"
    ));

    public final Map<String, String> regularToppings = new TreeMap<>(Map.of(
            "1", "Lettuce",
            "2", "Peppers",
            "3", "Onions",
            "4", "Tomatoes",
            "5", "Jalapenos",
            "6", "Cucumbers",
            "7", "Pickles",
            "8", "Guacamole",
            "9", "Mushrooms"
    ));

    public final Map<String, String> sauces = new TreeMap<>(Map.of(
            "1", "Mayo",
            "2", "Mustard",
            "3", "Ketchup",
            "4", "Ranch",
            "5", "Thousand Islands",
            "6", "Vinaigrette"

    ));

    public final Map<String, String> drinksList = new TreeMap<>(Map.of(
            "1", "Coke",
            "2", "Root Beer",
            "3", "Pepsi",
            "4", "Dr. Pepper",
            "5", "Fanta",
            "6", "Lemonade"
    ));

    public final Map<String, String> chipsList = new TreeMap<>(Map.of(
            "1", "Kettle Cooked Jalapeno",
            "2", "Lays Classic",
            "3", "Cheetos",
            "4", "Doritos Cool Ranch",
            "5", "Doritos Nacho Cheese"
    ));

    public final Map<String, String> sides = new TreeMap<>(Map.of(
            "1", "au jus",
            "2", "sauce"
    ));

    public final Map<Integer, String> cheeses = new TreeMap<>(Map.of(
            1, "American",
            2, "Provolone",
            3, "Cheddar",
            4, "Swiss"
    ));


    //Methods

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich );
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chip chip) {
        chips.add(chip);
    }

    public void addSide(Side side) {
        sideList.add(side);
    }


    //Getters and Setters
    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Side> getSidesList() {
        return sideList;
    }

    public List<Chip> getChips() {
        return chips;
    }

    @Override
    public String toString() {
        double subtotal = 0;
        StringBuilder output = new StringBuilder();
        output.append("""
                                      Your Order
                ===================================================
                """);
        if (!this.sandwiches.isEmpty()) {
            for (Sandwich sandwich : sandwiches) {
                subtotal += sandwich.getPrice();
                output.append(sandwich);
            }
        }

        if (!this.drinks.isEmpty()) {
            output.append(Text.createHeader("Drinks"));
            String drinkAbbrev = "";
            for (Drink drink : drinks) {
                switch (drink.getSize()) {
                    case "Small" -> drinkAbbrev = "Sm";
                    case "Medium" -> drinkAbbrev = "Md";
                    case "Large" -> drinkAbbrev = "Lg";
                }
                subtotal += drink.getPrice();
                output.append(String.format(" %s drink%-35s$%.2f\n %s\n\n", drinkAbbrev, " ", drink.getPrice(), drink.getBrand()));
            }
        }

        if (!this.chips.isEmpty()) {
            output.append((Text.createHeader("Chips")));
            for (Chip chip : chips) {
                double chipsCost = chip.getPrice();
                subtotal += chipsCost;
                output.append(String.format("""
                     %-22s%-21s$%.2f
                    """, chip.getName(), " ", chipsCost));
            }
        }

        if (!this.sideList.isEmpty()) {
            output.append(Text.createHeader("Sides"));
            sideList.forEach(side -> output.append(String.format(" %s\n", side.getName())));
        }

        output.append("\n-----------------------------------------++--------\n");

        double tax = subtotal * 0.07;
        double total = tax + subtotal;

        output.append(String.format("""
                 Subtotal                                || $%.2f
                 Tax (7%%)                                || $%.2f
                 Total                                   || $%.2f
                """, subtotal, tax, total));
        output.append("-----------------------------------------++--------\n");

        return output.toString();
    }
}
