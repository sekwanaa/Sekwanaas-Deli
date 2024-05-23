package com.pluralsight.models;

import com.pluralsight.Utilities.Utilities;

import java.util.Set;

public class Sandwich extends Order {
    private Set<String> premiumToppingsList;
    private boolean extraMeat = false;
    private Set<String> regularToppingsList;
    private String cheese;
    private boolean extraCheese = false;
    private Set<String> saucesList;
    private boolean toasted = false;


    /*
    *
    * Getters and Setters
    *
    * */



    public Set<String> getPremiumToppings() {
        return premiumToppingsList;
    }

    public void setPremiumToppings(Set<String> premiumToppings) {
        this.premiumToppingsList = premiumToppings;
    }

    public Set<String> getRegularToppings() {
        return regularToppingsList;
    }

    public void setRegularToppings(Set<String> regularToppings) {
        this.regularToppingsList = regularToppings;
    }

    public Set<String> getSauces() {
        return saucesList;
    }

    public void setSauces(Set<String> sauces) {
        this.saucesList = sauces;
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
        if (getSize() != null) {
            switch (getSize()) {
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
        if (premiumToppingsList != null) {
            for (String ignored : premiumToppingsList) {
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
        return Utilities.centerMessage("Your current sandwich", 45, '-') +
                "\n\n" +
                String.format("Bread Size: %s  |  Bread Type: %s\n", (getSize() == null ? "Required" : getSize()), (getType() == null ? "Required" : getType())) +
                "Meats: " +
                (premiumToppingsList != null ? premiumToppingsList.toString() : "N/A") +
                "\n" +
                "Regular toppings: " +
                (regularToppingsList != null ? regularToppingsList.toString() : "N/A") +
                "\n" + "Cheese: " + (cheese != null ? cheese : "N/A") +
                "\n" + "Sauces: " + (saucesList != null ? saucesList.toString() : "N/A") +
                "\n\n" +
                String.format("Price: $%.2f\n", getPrice());
    }
}
