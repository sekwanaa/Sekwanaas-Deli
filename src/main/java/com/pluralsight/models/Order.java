package com.pluralsight.models;

import com.pluralsight.Utilities.Utilities;

import java.util.*;

public class Order {
    private final List<Sandwich> sandwiches;
    private final List<Drinks> drinks;
    int chips;
    private final Set<String> sidesList;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = 0;
        this.sidesList = new HashSet<>();
    }

    public final Map<Integer, String> sides = new TreeMap<>(Map.of(
            1, "au jus",
            2, "sauce"
    ));

    public final Map<Integer, String> premiumToppings = new TreeMap<>(Map.of(
            1, "Steak",
            2, "Ham",
            3, "Salami",
            4, "Roast Beef",
            5, "Chicken",
            6, "Bacon"
    ));

    public final Map<Integer, String> regularToppings = new TreeMap<>(Map.of(
            1, "Lettuce",
            2, "Peppers",
            3, "Onions",
            4, "Tomatoes",
            5, "Jalapenos",
            6, "Cucumbers",
            7, "Pickles",
            8, "Guacamole",
            9, "Mushrooms"
    ));
    public final Map<Integer, String> cheeses = new TreeMap<>(Map.of(
            1, "American",
            2, "Provolone",
            3, "Cheddar",
            4, "Swiss"
    ));
    public final Map<Integer, String> sauces = new TreeMap<>(Map.of(
            1, "Mayo",
            2, "Mustard",
            3, "Ketchup",
            4, "Ranch",
            5, "Thousand Islands",
            6, "Vinaigrette"
    ));
    public final Map<Integer, String> drinksList = new TreeMap<>(Map.of(
            1, "Coke",
            2, "Root Beer",
            3, "Pepsi",
            4, "Dr. Pepper",
            5, "Fanta",
            6, "Lemonade"
    ));


    //Methods
    public void addSandwichToOrder(Sandwich sandwich) {
        sandwiches.add(sandwich );
    }

    public void addDrinkToOrder(Drinks drink) {
        drinks.add(drink);
    }

    public void addChipsToOrder() {
        chips++;
    }

    public void addSideToOrder(String side) {
        sidesList.add(side);
    }


    //Getters and Setters
    public List<Drinks> getDrinks() {
        return drinks;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public Set<String> getSidesList() {
        return sidesList;
    }

    public int getChips() {
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
            output.append(Utilities.createHeader("Drinks"));
            String drinkAbbrev = "";
            for (Drinks drink : drinks) {
                switch (drink.getSize()) {
                    case "Small" -> drinkAbbrev = "Sm";
                    case "Medium" -> drinkAbbrev = "Md";
                    case "Large" -> drinkAbbrev = "Lg";
                }
                subtotal += drink.getPrice();
                output.append(String.format(" %s drink%-35s$%.2f\n %s\n\n", drinkAbbrev, " ", drink.getPrice(), drink.getBrand()));
            }
        }

        if (this.chips != 0) {
            double chipsCost = chips * 1.50;
            subtotal += chipsCost;
            output.append((Utilities.createHeader("Chips")));
            output.append(String.format("""
                     %d Regular%-34s$%.2f
                    """, chips, " ", chipsCost));
        }

        if (!this.sidesList.isEmpty()) {
            output.append(Utilities.createHeader("Sides"));
            sidesList.forEach(side -> output.append(String.format(" %s\n", side)));
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
