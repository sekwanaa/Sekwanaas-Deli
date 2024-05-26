package com.pluralsight.userInterfaces;

import com.pluralsight.Utilities.*;
import com.pluralsight.models.*;
import com.pluralsight.DataManagers.ReceiptManager;

public class OrderScreen {
    private final Order userOrder;

    public OrderScreen() {
        this.userOrder = new Order();
    }

    //Menus

    public void homeScreen() {
        boolean currentlyOrdering = true;
        while (currentlyOrdering) {
            Utilities.clearConsole();
            System.out.println(userOrder);
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

    private void orderDrink() {
        Utilities.clearConsole();
        System.out.print("""
                What size fountain drink would you like?
                
                [1] Small
                [2] Medium
                [3] Large
                
                [x] Cancel drink selection
                
                Enter choice:\s""");

        processDrinkMenuChoice();
    }

    private void addChips() {
        Utilities.clearConsole();
        System.out.println(Utilities.centerMessage("Choosing chips", 50, '='));
        System.out.print("\n");
        userOrder.chipsList.forEach((number, chip) -> System.out.printf("[%d] %s\n", number, chip));
        System.out.println("\n[x] Cancel sides choice");
        System.out.print("Enter choice: ");

        processChipsMenuChoice();
    }

    private void orderSides() {
        Utilities.clearConsole();
        System.out.println(Utilities.centerMessage("Choosing sides", 50, '='));
        System.out.print("\n");
        userOrder.sides.forEach((number, side) -> System.out.printf("[%d] %s\n", number, side));
        System.out.println("\n[x] Cancel sides choice");
        System.out.print("Enter choice: ");

        processSidesMenuChoice();
    }

    //Processing Menu Choices

    private boolean processOrderMenuChoice(boolean currentlyOrdering) {
        String orderMenuChoice = Inputs.getString();

        try {
            int userChoice = Integer.parseInt(orderMenuChoice);
            switch (userChoice) {
                case 1:
                    CreateSandwichScreen createSandwichScreen = new CreateSandwichScreen(userOrder);
                    createSandwichScreen.sandwichCreationScreen();
                    break;
                case 2:
                    orderDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    orderSides();
                    break;
                default:
                    System.out.println("This is not a valid choice");
                    Inputs.awaitInput();
                    break;
            }
        } catch (NumberFormatException e) {
            switch (orderMenuChoice) {
                case "F", "f" -> {
                    // add items to receipt and finalize the order
                    Utilities.clearConsole();
                    System.out.println(userOrder);
                    System.out.print("\n\n\n\nIs the order correct? (Y/N): ");
                    String orderFinished = Inputs.getString();

                    switch (orderFinished) {
                        case "Y", "y":
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + userOrder);
                            ReceiptManager.createReceipt(userOrder.toString());
                            currentlyOrdering = false;
                            HomeScreen.displayHomeScreen();
                            break;
                        case "N", "n":
                            break;
                        default:
                            System.out.println("That's not a valid option.");
                            Inputs.awaitInput();
                            break;
                    }

                }
                case "X", "x" -> {
                    currentlyOrdering = false;
                    HomeScreen.displayHomeScreen();
                }
                default -> {
                    System.out.print("This is not a valid choice.");
                    Inputs.awaitInput();
                }
            }
        }
        return currentlyOrdering;
    }

    private void processDrinkMenuChoice() {
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
            Utilities.clearConsole();
            System.out.println("""
                    
                    What drink would you like?
                    """);
            userOrder.drinksList.forEach((num, d) -> System.out.printf("[%d] %s\n", num, d));
            System.out.print("\nEnter choice: ");
            int drinkTypeChoice = Inputs.getInt();
            drink.setBrand(userOrder.drinksList.get(drinkTypeChoice));
            userOrder.addDrink(drink);
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                System.out.println();
            } else {
                System.out.println("This is not a valid command, please try again.");
            }
        }
    }

    private void processChipsMenuChoice() {
        String userChoice = Inputs.getString();
        try {
            int userSidesChoice = Integer.parseInt(userChoice);
            Chips chip = new Chips();
            chip.setName(userOrder.chipsList.get(userSidesChoice));
            userOrder.addChips(chip);
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                System.out.println();
            } else {
                System.out.println("This was not a valid sides choice. Please try again...");
            }
        }
    }

    private void processSidesMenuChoice() {
        String userChoice = Inputs.getString();
        try {
            Sides side = new Sides();
            int userSidesChoice = Integer.parseInt(userChoice);

            side.setName(userOrder.sides.get(userSidesChoice));
            userOrder.addSide(side);
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                System.out.println();
            } else {
                System.out.println("This was not a valid sides choice. Please try again...");
            }
        }
    }
}
