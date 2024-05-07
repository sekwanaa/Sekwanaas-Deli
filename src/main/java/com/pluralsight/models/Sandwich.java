package com.pluralsight.models;

import java.util.Set;

public class Sandwich {
    private String size;
    private String type;
    private Set<String> premiumToppings;
    private boolean extraMeat = false;
    private Set<String> regularToppings;
    private String cheese;
    private boolean extraCheese = false;
    private Set<String> sauces;
    private boolean toasted = false;

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
            case 2 -> "8\"";
            case 3 -> "12\"";
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

    public Set<String> getPremiumToppings() {
        return premiumToppings;
    }

    public void setPremiumToppings(Set<String> premiumToppings) {
        this.premiumToppings = premiumToppings;
    }

    public Set<String> getRegularToppings() {
        return regularToppings;
    }

    public void setRegularToppings(Set<String> regularToppings) {
        this.regularToppings = regularToppings;
    }

    public Set<String> getSauces() {
        return sauces;
    }

    public void setSauces(Set<String> sauces) {
        this.sauces = sauces;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public boolean hasExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public double getPrice() {
        double finalPrice = 0;
        double premiumToppingCharge = 0;
        double extraMeatCharge = 0;
        double cheeseCharge = 0;
        double extraCheeseCharge = 0;
        if (size != null) {
            switch (size) {
                case "4\"" -> {
                    finalPrice += 5.50;
                    premiumToppingCharge = 1.00;
                    extraMeatCharge = .50;
                    cheeseCharge = .75;
                    extraCheeseCharge = .30;
                }
                case "8\"" -> {
                    finalPrice += 7.00;
                    premiumToppingCharge = 2.00;
                    extraMeatCharge = 1.00;
                    cheeseCharge = 1.50;
                    extraCheeseCharge = .60;
                }
                case "12\"" -> {
                    finalPrice += 8.50;
                    premiumToppingCharge = 3.00;
                    extraMeatCharge = 1.50;
                    cheeseCharge = 2.25;
                    extraCheeseCharge = .90;
                }
            }
        }
        if (premiumToppings != null) {
            for (String ignored : premiumToppings) {
                finalPrice += premiumToppingCharge;
            }
        }

        if (cheese != null && !cheese.isEmpty()) {
            finalPrice += cheeseCharge;
        }

        if (extraMeat) {
            finalPrice += extraMeatCharge;
        }

        if (extraCheese) {
            finalPrice += extraCheeseCharge;
        }

        return finalPrice;
    }


    @Override
    public String toString() {
        return "\n\n\n\n\n\n--------Your current sandwich--------\n" + "\n" +
                String.format("Bread Size: %s  |  Bread Type: %s\n", (size == null ? "Required" : size), (type == null ? "Required" : type)) +
                "Meats: " + (premiumToppings != null ? premiumToppings.toString() : "N/A") + "\n" +
                "Regular toppings: " + (regularToppings != null ? regularToppings.toString() : "N/A") + "\n" +
                "Cheese: " + (cheese != null ? cheese : "N/A") + "\n" + "Sauces: " + (sauces != null ? sauces : "N/A") + "\n\n" + String.format("Price: %.2f\n", getPrice());
    }
}
