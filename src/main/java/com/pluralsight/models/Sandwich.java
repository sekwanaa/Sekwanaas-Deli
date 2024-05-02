package com.pluralsight.models;

import java.util.List;

public class Sandwich {
    private String size;
    private String type;
    private List<String> premiumToppings;
    private List<String> regularToppings;
    private List<String> sauces;

    /*
    *
    * Getters and Setters
    *
    * */

    public String getSize() {
        return size;
    }

    public void setSize(int choice) {
        this.size = switch (choice) {
            case 1 -> "4\"";
            case 2 -> "6\"";
            case 3 -> "8\"";
            default -> "";
        };
    }

    public String getType() {
        return type;
    }

    public void setType(int choice) {
        this.type = switch (choice) {
            case 1 -> "Wheat";
            case 2 -> "White";
            case 3 -> "Rye";
            case 4 -> "Wrap";
            default -> "";
        };
    }

    public List<String> getPremiumToppings() {
        return premiumToppings;
    }

    public void setPremiumToppings(List<String> premiumToppings) {
        this.premiumToppings = premiumToppings;
    }

    public List<String> getRegularToppings() {
        return regularToppings;
    }

    public void setRegularToppings(List<String> regularToppings) {
        this.regularToppings = regularToppings;
    }

    public List<String> getSauces() {
        return sauces;
    }

    public void setSauces(List<String> sauces) {
        this.sauces = sauces;
    }
}
