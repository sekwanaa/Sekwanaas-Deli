package com.pluralsight.userInterfaces;

import com.pluralsight.Utilities.Inputs;
import com.pluralsight.Utilities.Utilities;
import com.pluralsight.models.Drinks;
import com.pluralsight.models.Order;
import com.pluralsight.DataManagers.ReceiptManager;
import com.pluralsight.models.Sandwich;

import java.util.*;

public class OrderScreen {
    public static List<Sandwich> sandwich = new ArrayList<>();
    public static List<Drinks> drinks = new ArrayList<>();
    public static int chips = 0;
    public static Set<String> sidesChoice = new HashSet<>();


    public void homeScreen() {
        boolean currentlyOrdering = true;
        while (currentlyOrdering) {
            Utilities.clearConsole();
            System.out.println(this);
            System.out.println("\n");
            System.out.print("""
                    What would you like to add to your order?
                    
                    [1] Sandwich
                    [2] Drinks
                    [3] Chips
                    [4] Sides
                    
                    [f] Finalize order
                    [x] Cancel order
                    
                    Enter choice:\s""");

            currentlyOrdering = processOrderMenuChoice(currentlyOrdering);
        }
    }

    private boolean processOrderMenuChoice(boolean currentlyOrdering) {
        String orderMenuChoice = Inputs.getString();

        try {
            int userChoice = Integer.parseInt(orderMenuChoice);
            switch (userChoice) {
                case 1:
                    // go to createSandwich interface since it's complicated
                    CreateSandwichScreen createSandwichScreen = new CreateSandwichScreen();
                    createSandwichScreen.homeScreen();
                    break;
                case 2:
                    // let users choose drink size
                    orderDrink();
                    break;
                case 3:
                    // let users add chips if they want
                    addChips();
                    break;
                case 4:
                    // let users add sides if they want (use a set so they can only get one unique side)
                    orderSides();
                    break;
                default:
                    System.out.print("This is not a valid choice, press ENTER try again.");
                    Inputs.awaitInput();
                    break;
            }
        } catch (NumberFormatException e) {
            switch (orderMenuChoice) {
                case "F", "f" -> {
                    // add items to receipt and finalize the order
                    Utilities.clearConsole();
                    System.out.println(this);
                    System.out.print("\n\n\n\nIs the order correct? (Y/N): ");
                    String orderFinished = Inputs.getString();

                    switch (orderFinished) {
                        case "Y", "y":
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + this);
                            ReceiptManager.createReceipt(this.toString());
                            currentlyOrdering = false;
                            break;
                        case "N", "n":
                            break;
                        default:
                            System.out.print("That's not a valid option, press ENTER to try again...");
                            Inputs.awaitInput();
                            break;
                    }

                }
                case "X", "x" -> {
                    // clear all static lists so that when they create a new order there's nothing in it.
                    sandwich.clear();
                    drinks.clear();
                    chips = 0;
                    currentlyOrdering = false;
                }
                default -> {
                    System.out.print("This is not a valid choice, press ENTER try again.");
                    Inputs.awaitInput();
                }
            }
        }
        return currentlyOrdering;
    }

    private void orderDrink() {
        System.out.print("""
                What size fountain drink would you like?
                
                [1] Small
                [2] Medium
                [3] Large
                
                [x] Cancel drink selection
                
                Enter choice:\s""");

        String userChoice = Inputs.getString();

        try {
            Drinks drink = new Drinks();
            int drinkChoice = Integer.parseInt(userChoice);
            String size = "";
            double price = switch (drinkChoice) {
                case 1 -> {
                    size = "Small";
                    yield 2.00;
                }
                case 2 -> {
                    size = "Medium";
                    yield 2.50;
                }
                case 3 -> {
                    size = "Large";
                    yield 3.00;
                }
                default -> 0;
            };
            drink.setSize(size);
            drink.setPrice(price);
            System.out.println("""
                    
                    What drink would you like?
                    """);
            Drinks.drinksList.forEach((num, d) -> System.out.printf("[%d] %s\n", num, d));
            System.out.print("\nEnter choice: ");
            int drinkTypeChoice = Inputs.getInt();
            drink.setType(Drinks.drinksList.get(drinkTypeChoice));
            drinks.add(drink);
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                System.out.println();
            } else {
                System.out.println("This is not a valid command, please try again.");
            }
        }
    }

    private void addChips() {
        chips++;
    }

    private void orderSides() {
        Order.sides.forEach((number, side) -> System.out.printf("[%d] %s\n", number, side));
        System.out.println("\n[x] Cancel sides choice");
        System.out.print("Enter choice: ");

        String userChoice = Inputs.getString();
        try {
            int userSidesChoice = Integer.parseInt(userChoice);
            sidesChoice.add(Order.sides.get(userSidesChoice));
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                System.out.println();
            } else {
                System.out.println("This was not a valid sides choice. Please try again...");
            }
        }
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
                String sandwichInfo = String.format("Sandwich [%s %s]", sandwich.getSize(), sandwich.getType());

                output.append(" ").append(Utilities.createHeader(sandwichInfo, sandwich.getPrice()));
                output.append(String.format("""
                         Cheese: %s
                         Toasted: %s | Extra Cheese: %s | Extra Meat: %s
                        """, cheese, isToasted, hasExtraCheese, isExtraMeat));

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

        if (!drinks.isEmpty()) {
            output.append(Utilities.createHeader("Drinks"));
            String drinkAbbrev = "";
            for (Drinks drink : drinks) {
                switch (drink.getSize()) {
                    case "Small" -> drinkAbbrev = "Sm";
                    case "Medium" -> drinkAbbrev = "Md";
                    case "Large" -> drinkAbbrev = "Lg";
                }
                subtotal += drink.getPrice();
                output.append(String.format(" %s drink%-35s$%.2f\n %s\n\n", drinkAbbrev, " ", drink.getPrice(), drink.getType()));
            }
        }

        if (chips != 0) {
            double chipsCost = chips * 1.50;
            subtotal += chipsCost;
            output.append((Utilities.createHeader("Chips")));
            output.append(String.format("""
                     %d Regular%-34s$%.2f
                    """, chips, " ", chipsCost));
        }

        if (!sidesChoice.isEmpty()) {
            output.append(Utilities.createHeader("Sides"));
            sidesChoice.forEach(side -> output.append(String.format(" %s\n", side)));
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


        // continue printing info about each sandwich, and then each drink, etc.
        return output.toString();
    }
}
