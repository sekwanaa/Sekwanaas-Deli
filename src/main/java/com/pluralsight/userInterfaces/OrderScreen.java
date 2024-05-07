package com.pluralsight.userInterfaces;

import com.pluralsight.models.Receipt;
import com.pluralsight.models.Sandwich;

import java.util.*;

public class OrderScreen {
    public static List<Sandwich> sandwich = new ArrayList<>();
    public static Map<String, Integer> drinks = new HashMap<>();
    public static int chips = 0;
    public static Set<String> sidesChoice = new HashSet<>();

    private static final Map<Integer, String> sides = new TreeMap<>(Map.of(
            1, "au jus",
            2, "sauce"
    ));


    public void homeScreen(Scanner scanner) {
        boolean isMakingOrder = true;
        while (isMakingOrder) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.print("""
                What would you like to add to your order?
                
                [1] Sandwich
                [2] Drinks
                [3] Chips
                [4] Sides
                
                [f] Finalize order
                [x] Cancel order
                
                Enter choice:\s""");

            if (scanner.hasNextInt()) {
                int userChoice = scanner.nextInt();
                scanner.nextLine();
                switch (userChoice) {
                    case 1:
                        // go to createSandwich interface since it's complicated
                        CreateSandwich sandwichInterface = new CreateSandwich();
                        sandwichInterface.homeScreen(scanner);
                        break;
                    case 2:
                        // let users choose drink size
                            System.out.print("""
                                What size fountain drink would you like?
                                
                                [1] Small
                                [2] Medium
                                [3] Large
                                
                                [x] Cancel drink selection
                                
                                Enter choice:\s""");
                            if (scanner.hasNextInt()) {
                                int drinkChoice = scanner.nextInt();
                                scanner.nextLine();
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
                        String chipsOrNot = scanner.nextLine();
                        if (chipsOrNot.equalsIgnoreCase("y")) {
                            addChips();
                        } else {
                            System.out.println("Next time then...");
                        }
                        break;
                    case 4:
                        // let users add sides if they want (use a set so they can only get one unique side)
                        sides.forEach((number, side) -> System.out.printf("[%d] %s\n", number, side));
                        System.out.println("\n[x] Cancel sides choice");
                        System.out.print("Enter choice: ");

                        if (scanner.hasNextInt()) {
                            int userSidesChoice = scanner.nextInt();
                            scanner.nextLine();
                            sidesChoice.add(sides.get(userSidesChoice));
                        } else {
                            String cancelSidesChoice = scanner.nextLine();
                            if (cancelSidesChoice.equalsIgnoreCase("x")) {
                                break;
                            } else {
                                System.out.println("This was not a valid sides choice. Please try again...");
                            }
                        }
                        break;
                    default:
                        System.out.println("That's not a valid choice, please try again...");
                }
            } else {
                String userChoice = scanner.nextLine();
                switch (userChoice) {
                    case "F", "f" -> {
                        // add items to receipt and finalize the order
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + this);
                        System.out.print("\n\n\n\nIs the order correct? (Y/N): ");
                        String orderFinished = scanner.nextLine();

                        switch (orderFinished) {
                            case "Y", "y":
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + this);
                                Receipt receipt = new Receipt(this.toString());
                                receipt.createReceipt();
                                System.out.println("Receipt Created...");
                                isMakingOrder = false;
                                break;
                            case "N", "n":
                                break;
                        }

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
        drinks.computeIfPresent(size, (drink, count) -> count + 1);
        drinks.putIfAbsent(size, 1);
    }


    private void addChips() {
        chips++;
    }


    @Override
    public String toString() {
        double subtotal = 0;
        StringBuilder output = new StringBuilder();
        output.append("""
                                      Your Order
                ===================================================
                """);
        if (sandwich != null) {
            for (Sandwich sandwich : sandwich) {
                subtotal += sandwich.getPrice();
                String isToasted = sandwich.isToasted() ? "yes" : "no";
                String hasExtraCheese = sandwich.hasExtraCheese() ? "yes" : "no";
                String isExtraMeat = sandwich.isExtraMeat() ? "yes" : "no";
                String cheese = sandwich.getCheese() != null ? sandwich.getCheese() : "N/A";

                output.append(String.format("""
                    
                    -----------------------------------------++--------
                     Sandwich [%s %s]                     || %.2f
                    +========================================++=======+
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
            }
        }

        if (drinks != null) {
            output.append("""
                    
                    -----------------------------------------++--------
                     Drinks                                  ||
                    +========================================++=======+
                    """);
            for (Map.Entry<String, Integer> drink : drinks.entrySet()) {
                double drinkCost = 0;
                String drinkAbbrev = "";
                switch (drink.getKey()) {
                    case "Small" -> {
                        drinkCost = drink.getValue() * 2.00;
                        drinkAbbrev = "Sm";
                    }
                    case "Medium" -> {
                        drinkCost = drink.getValue() * 2.50;
                        drinkAbbrev = "Md";
                    }
                    case "Large" -> {
                        drinkCost = drink.getValue() * 3.00;
                        drinkAbbrev = "Lg";
                    }
                }
                subtotal += drinkCost;
                output.append(String.format(" %d %s                                       %.2f\n", drink.getValue(), drinkAbbrev, drinkCost));
            }
        }

        if (chips != 0) {
            double chipsCost = chips * 1.50;
            subtotal += chipsCost;
            output.append(String.format("""
                    
                    -----------------------------------------++--------
                     Chips                                   ||
                    +========================================++=======+
                     %d Regular                                 %.2f
                    """, chips, chipsCost));
        }

        if (sidesChoice != null) {
            output.append("""
                
                -----------------------------------------++--------
                 Sides                                   ||
                +========================================++=======+
                """);
            sidesChoice.forEach(side -> output.append(String.format(" %s\n", side)));
        }

        output.append("\n-----------------------------------------++--------\n");

        double tax = subtotal * 0.07;
        double total = tax + subtotal;

        output.append(String.format("""
                 \sSubtotal                                || %.2f
                 \sTax (7%%)                                || %.2f
                 \sTotal                                   || %.2f
                 """, subtotal, tax, total));
        output.append("-----------------------------------------++--------\n");


        // continue printing info about each sandwich, and then each drink, etc.
        return output.toString();
    }
}
