package com.pluralsight.userInterfaces;

import com.pluralsight.models.Chips;
import com.pluralsight.models.Drinks;
import com.pluralsight.models.Sandwich;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    public static List<Sandwich> sandwich = new ArrayList<>();
    public static List<Drinks> drinks = new ArrayList<>();
    public static List<Chips> chips = new ArrayList<>();;


    public void homeScreen(Scanner scanner) {
        boolean isMakingOrder = true;
        while (isMakingOrder) {
            if (sandwich != null) {
                System.out.println("Your current order:\n\n" + this);
            }
            System.out.print("""
                What would you like to add to your order?
                
                [1] Sandwich
                [2] Drinks
                [3] Chips
                [4] Sides
                [5] Sauces
                
                [f] Finalize order
                [x] Cancel order
                
                Enter choice:\s""");

            if (scanner.hasNextInt()) {
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1:
                        // go to createSandwich interface since it's complicated
                        System.out.println("Sandwiches");
                        CreateSandwich sandwichInterface = new CreateSandwich();
                        sandwichInterface.homeScreen(scanner);
                        break;
                    case 2:
                        // let users choose drink size
                        System.out.println("Drinks");
                        break;
                    case 3:
                        // let users add chips if they want
                        System.out.println("Chips");
                        break;
                    case 4:
                        // let users add sides if they want (use a set so they can only get one unique side)
                        System.out.println("Sides");
                        break;
                    case 5:
                        // let users add sauces if they want. Maybe a set? Maybe a map with a counter for how many sauces?
                        System.out.println("Sauces");
                        break;
                    default:
                        System.out.println("That's not a valid choice, please try again...");
                }
            } else {
                String userChoice = scanner.nextLine();
                switch (userChoice) {
                    case "F", "f" -> {
                        // add items to receipt and finalize the order
                        System.out.println("okay done");
                    }
                    case "X", "x" -> {
                        // clear all static lists so that when they create a new order there's nothing in it.
                        sandwich.clear();
                        drinks.clear();
                        chips.clear();
                        isMakingOrder = false;
                    }
                    default -> System.out.println("This is not a valid choice, please try again.");
                }
            }
        }
    }


    @Override
    public String toString() {
        System.out.println("here");
        StringBuilder output = new StringBuilder();
        output.append("""
                                     Your Order
                =================================================
                """);
        sandwich.forEach(sandwich -> {
            String isToasted = sandwich.isToasted() ? "yes" : "no";
            String hasExtraCheese = sandwich.hasExtraCheese() ? "yes" : "no";
            String isExtraMeat = sandwich.isExtraMeat() ? "yes" : "no";

            output.append(String.format("\nSandwich [%s %s]                          %.2f", sandwich.getSize(), sandwich.getType(), sandwich.getPrice()));
            output.append(String.format("\nToasted: %s  Extra Cheese: %s  Extra Meat: %s\n", isToasted, hasExtraCheese, isExtraMeat));

            output.append("\nPremium Toppings:\n");
            if (sandwich.getPremiumToppings() != null) {
                sandwich.getPremiumToppings().forEach(premiumTopping -> output.append(String.format("   %s\n", premiumTopping)));
            } else {
                output.append("N/A\n");
            }
            output.append("\nRegular Toppings:\n");
            if (sandwich.getRegularToppings() != null) {
                sandwich.getRegularToppings().forEach(regularTopping -> output.append(String.format("   %s\n", regularTopping)));
            } else {
                output.append("N/A\n");
            }
            // continue printing info about each sandwich, and then each drink, etc.
        });
        return output.toString();
    }
}
