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
        while (true) {
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
                [0] Previous screen
                
                Enter choice:\s""");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("Sandwiches");
                    CreateSandwich sandwichInterface = new CreateSandwich();
                    sandwichInterface.homeScreen(scanner);
                    break;
                case 2:
                    System.out.println("Drinks");
                    break;
                case 3:
                    System.out.println("Chips");
                    break;
                case 4:
                    System.out.println("Sides");
                    break;
                case 5:
                    System.out.println("Sauces");
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("That's not a valid choice, please try again...");
            }
        }
    }


    @Override
    public String toString() {
        System.out.println("here");
        StringBuilder output = new StringBuilder();
        output.append("""
                               Your Order
                ======================================
                """);
        sandwich.forEach(sandwich -> {

            output.append(String.format("\nSandwich [%s %s]           %.2f", sandwich.getSize(), sandwich.getType(), sandwich.getPrice()));
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
