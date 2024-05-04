package com.pluralsight.userInterfaces;

import com.pluralsight.models.Sandwich;

import java.util.*;

public class Order {
    public static List<Sandwich> sandwich = new ArrayList<>();
    public static Map<String, Integer> drinks = new HashMap<>();
    public static int chips = 0;;


    public void homeScreen(Scanner scanner) {
        boolean isMakingOrder = true;
        while (isMakingOrder) {
//            if (sandwich != null) {
//                System.out.println("Your current order:\n\n" + this);
//            }
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
                        CreateSandwich sandwichInterface = new CreateSandwich();
                        sandwichInterface.homeScreen(scanner);
                        break;
                    case 2:
                        // let users choose drink size
                            System.out.println("""
                                What size drink would you like?
                                
                                [1] Small
                                [2] Medium
                                [3] Large
                                
                                [x] Cancel drink selection
                                
                                """);
                            if (scanner.hasNextInt()) {
                                int drinkChoice = scanner.nextInt();
                                switch (drinkChoice) {
                                    case 1 -> addDrink("Small");
                                    case 2 -> addDrink("Medium");
                                    case 3 -> addDrink("Large");
                                    default -> System.out.println("This is not a valid choice, try again.");
                                }
                            } else {
                                String cancelSelection = scanner.nextLine();
                                if (cancelSelection.equalsIgnoreCase("x")) {
                                    break;
                                } else {
                                    System.out.println("This is not a valid command, please try again.");
                                }
                            }
                        break;
                    case 3:
                        // let users add chips if they want
                        System.out.print("Would you like to add some chips? (Y/N): ");
                        scanner.nextLine();
                        String chipsOrNot = scanner.nextLine();
                        if (chipsOrNot.equalsIgnoreCase("y")) {
                            addChips();
                        } else {
                            System.out.println("Next time then...");
                        }
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
                        System.out.println("Your current order:\n\n" + this);

                        isMakingOrder = false;
                    }
                    case "X", "x" -> {
                        // clear all static lists so that when they create a new order there's nothing in it.
                        sandwich.clear();
                        drinks.clear();
                        chips = 0;
                        isMakingOrder = false;
                    }
                    default -> System.out.println("This is not a valid choice, please try again.");
                }
            }
        }
    }

    private void addDrink(String size) {
        drinks.putIfAbsent(size, 1);
        drinks.computeIfPresent(size, (drink, count) -> count + 1);
    }


    private void addChips() {
        chips++;
    }


    @Override
    public String toString() {
        System.out.println("here");
        StringBuilder output = new StringBuilder();
        output.append("""
                                     Your Order
                =================================================
                """);
        if (sandwich != null) {
            sandwich.forEach(sandwich -> {
                String isToasted = sandwich.isToasted() ? "yes" : "no";
                String hasExtraCheese = sandwich.hasExtraCheese() ? "yes" : "no";
                String isExtraMeat = sandwich.isExtraMeat() ? "yes" : "no";
                String cheese = sandwich.getCheese() != null ? sandwich.getCheese() : "N/A";

                output.append(String.format("""
                    
                    -------------------------------------------------
                     Sandwich [%s %s]                       %.2f
                    +===============================================+
                     Cheese: %s
                     Toasted: %s  Extra Cheese: %s  Extra Meat: %s
                    """, sandwich.getSize(), sandwich.getType(), sandwich.getPrice(), cheese, isToasted, hasExtraCheese, isExtraMeat));

                output.append("\n Premium Toppings:\n");
                if (sandwich.getPremiumToppings() != null) {
                    sandwich.getPremiumToppings().forEach(premiumTopping -> output.append(String.format("\t%s\n", premiumTopping)));
                } else {
                    output.append("\tN/A\n");
                }

                output.append("\n Regular Toppings:\n");
                if (sandwich.getRegularToppings() != null) {
                    sandwich.getRegularToppings().forEach(regularTopping -> output.append(String.format("\t%s\n", regularTopping)));
                } else {
                    output.append("\tN/A\n");
                }

                output.append("\n Sauces:\n");
                if (sandwich.getSauces() != null) {
                    sandwich.getSauces().forEach(sauce -> output.append(String.format("\t%s\n", sauce)));
                } else {
                    output.append("\tN/A\n");
                }
                output.append("\n-------------------------------------------------");


            });
        }

        if (drinks != null) {
            output.append("""
                    
                    -------------------------------------------------
                     Drinks
                    +===============================================+
                    """);
            drinks.forEach((drink, count) -> {
                double drinkCost = 0;
                String drinkAbbrev = "";
                switch (drink) {
                    case "Small" -> {
                        drinkCost = count * 2.00;
                        drinkAbbrev = "Sm";
                    }
                    case "Medium" -> {
                        drinkCost = count * 2.50;
                        drinkAbbrev = "Md";
                    }
                    case "Large" -> {
                        drinkCost = count * 3.00;
                        drinkAbbrev = "Lg";
                    }
                }
                output.append(String.format("%d %s                                       %.2f\n", count, drinkAbbrev, drinkCost));
            });
            output.append("\n-------------------------------------------------");

        }

        if (chips != 0) {
            double chipsCost = chips * 1.50;
            output.append(String.format("""
                    
                    -------------------------------------------------
                     %d Chips                               %.2f
                    +===============================================+
                    """, chips, chipsCost));
            output.append("\n-------------------------------------------------");
        }
        // continue printing info about each sandwich, and then each drink, etc.
        return output.toString();
    }
}
