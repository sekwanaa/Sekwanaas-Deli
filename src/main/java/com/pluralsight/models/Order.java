package com.pluralsight.models;

import java.util.Map;
import java.util.TreeMap;

public abstract class Order {
    /*Creating an abstract class which all orders can
    * use to create uniformity
    * */
    private String size;
    private String type;
    private double price;


    public static final Map<Integer, String> sides = new TreeMap<>(Map.of(
            1, "au jus",
            2, "sauce"
    ));

    public static final Map<Integer, String> premiumToppings = new TreeMap<>(Map.of(
            1, "Steak",
            2, "Ham",
            3, "Salami",
            4, "Roast Beef",
            5, "Chicken",
            6, "Bacon"
    ));
    public static final Map<Integer, String> regularToppings = new TreeMap<>(Map.of(
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
    public static final Map<Integer, String> cheeses = new TreeMap<>(Map.of(
            1, "American",
            2, "Provolone",
            3, "Cheddar",
            4, "Swiss"
    ));
    public static final Map<Integer, String> sauces = new TreeMap<>(Map.of(
            1, "Mayo",
            2, "Mustard",
            3, "Ketchup",
            4, "Ranch",
            5, "Thousand Islands",
            6, "Vinaigrette"
    ));

    public static final Map<Integer, String> drinksList = new TreeMap<>(Map.of(
            1, "Coke",
            2, "Root Beer",
            3, "Pepsi",
            4, "Dr. Pepper",
            5, "Fanta",
            6, "Lemonade"
    ));

    //Getters and Setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
