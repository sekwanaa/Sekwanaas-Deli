package com.pluralsight.userInterfaces;

import com.pluralsight.Utilities.*;
import com.pluralsight.models.*;
import com.pluralsight.DataManagers.ReceiptManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderScreen {
    private final Order userOrder;

    public OrderScreen() {
        this.userOrder = new Order();
    }

    //Menus

    public void homeScreen() {
            Utilities.clearConsole();
            System.out.println(userOrder);
            System.out.println("\n");
            System.out.print("""
                    What would you like to add to your order?
                    
                    [1] Sandwich
                    [2] Drinks
                    [3] Chips
                    [4] Sides
                    
                    [e] Edit order
                    [f] Finalize order
                    
                    [x] Cancel order
                    
                    Enter choice:\s""");

            if (processOrderMenuChoice()) homeScreen();
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

            if (processDrinkMenuChoice()) orderDrink();
    }

    private void addChips() {
        Utilities.clearConsole();
        System.out.println(Utilities.centerMessage("Choosing chips", 50, '='));
        System.out.print("\n");
        userOrder.chipsList.forEach((number, chip) -> System.out.printf("[%d] %s\n", number, chip));
        System.out.println("\n[x] Cancel sides choice");
        System.out.print("Enter choice: ");

        if (processChipsMenuChoice()) addChips();
    }

    private void orderSides() {
        Utilities.clearConsole();
        System.out.println(Utilities.centerMessage("Choosing sides", 50, '='));
        System.out.print("\n");
        userOrder.sides.forEach((number, side) -> System.out.printf("[%d] %s\n", number, side));
        System.out.println("\n[x] Cancel sides choice");
        System.out.print("Enter choice: ");

        if (processSidesMenuChoice()) orderSides();
    }

    //Processing Menu Choices

    private boolean processOrderMenuChoice() {
        String orderMenuChoice = Inputs.getString();

        try {
            int userChoice = Integer.parseInt(orderMenuChoice);
            switch (userChoice) {
                case 1:
                    CreateSandwichScreen createSandwichScreen = new CreateSandwichScreen(userOrder);
                    createSandwichScreen.sandwichCreationHomeScreen();
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
                case "E", "e" -> {
                    //Ask user what items they would like to edit
                    Utilities.clearConsole();

                    System.out.print("""
                    What would you like to edit?
                    
                    [1] Sandwich
                    [2] Drinks
                    [3] Chips
                    [4] Sides
                    
                    [x] Cancel editing
                    
                    Enter choice:\s""");

                    processEditingMenuChoice();
                }
                case "F", "f" -> {
                    // add items to receipt and finalize the order
                    Utilities.clearConsole();
                    System.out.println(userOrder);
                    System.out.print("\n\n\n\nIs the order correct? (Y/N): ");
                    String orderFinished = Inputs.getString();

                    switch (orderFinished) {
                        case "Y", "y":
                            ReceiptManager.createReceipt(userOrder.toString());
                            return false;
                        case "N", "n":
                            break;
                        default:
                            System.out.println("That's not a valid option.");
                            Inputs.awaitInput();
                            break;
                    }

                }
                case "X", "x" -> {
                    return false;
                }
                default -> {
                    System.out.print("This is not a valid choice.");
                    Inputs.awaitInput();
                }
            }
        }
        return true;
    }

    private boolean processDrinkMenuChoice() {
        String userChoice = Inputs.getString();

        try {
            Drinks drink = new Drinks();
            int drinkChoice = Integer.parseInt(userChoice);
            String size = "";
            double price = 0;

            if (drinkChoice < 0 || drinkChoice > 3) {
                System.out.println("That's not a valid choice.");
                Inputs.awaitInput();
                return true;
            }

            switch (drinkChoice) {
                case 1 -> {
                    size = "Small";
                    price = 2.00;
                }
                case 2 -> {
                    size = "Medium";
                    price = 2.50;
                }
                case 3 -> {
                    size = "Large";
                    price = 3.00;
                }
            }
            drink.setSize(size);
            drink.setPrice(price);
            Utilities.clearConsole();
            System.out.println("""
                
                What drink would you like?
                """);
            userOrder.drinksList.forEach((num, d) -> System.out.printf("[%d] %s\n", num, d));
            System.out.print("\nEnter choice: ");
            int drinkTypeChoice = Inputs.getInt();

            if (validateUserChoice(drinkTypeChoice, userOrder.drinksList)) {
                drink.setBrand(userOrder.drinksList.get(drinkTypeChoice));
                userOrder.addDrink(drink);
                return false;
            }
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                return false;
            } else {
                System.out.println("This is not a valid command, please try again.");
                Inputs.awaitInput();
            }
        }
        return true;
    }

    private boolean processChipsMenuChoice() {
        String userChoice = Inputs.getString();
        try {
            int userSidesChoice = Integer.parseInt(userChoice);
            if (validateUserChoice(userSidesChoice, userOrder.chipsList)) {
                Chips chip = new Chips();
                chip.setName(userOrder.chipsList.get(userSidesChoice));
                userOrder.addChips(chip);
                return false;
            }
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                return false;
            } else {
                System.out.println("This was not a valid sides choice. Please try again...");
                Inputs.awaitInput();
            }
        }
        return true;
    }

    private boolean processSidesMenuChoice() {
        String userChoice = Inputs.getString();
        try {
            Sides side = new Sides();
            int userSidesChoice = Integer.parseInt(userChoice);

            if (validateUserChoice(userSidesChoice, userOrder.sides)) {
                side.setName(userOrder.sides.get(userSidesChoice));
                userOrder.addSide(side);
                return false;
            }
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                return false;
            } else {
                System.out.println("This was not a valid sides choice. Please try again...");
                Inputs.awaitInput();
            }
        }
        return true;
    }

    private void processEditingMenuChoice() {
        String userChoice = Inputs.getString();

        try {
            int userIntChoice = Integer.parseInt(userChoice);

            switch (userIntChoice) {
                case 1:
                    selectWhichItemToEdit(userOrder.getSandwiches());
                    break;
                case 2:
                    selectWhichItemToEdit(userOrder.getDrinks());
                    break;
                case 3:
                    selectWhichItemToEdit(userOrder.getChips());
                    break;
                case 4:
                    selectWhichItemToEdit(userOrder.getSidesList());
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            if (userChoice.equalsIgnoreCase("x")) {
                System.out.println();
            } else {
                System.out.println("This was not a valid menu choice. Please try again...");
                Inputs.awaitInput();
            }
        }
    }

    private void selectWhichItemToEdit(List<? extends Product> itemList) {
        int originalItemListSize = itemList.size();
        Utilities.clearConsole();
        System.out.println(Utilities.centerMessage(" Choose item to edit ", 50, '='));
        System.out.println();
        Map<Integer, Product> itemsMap = new HashMap<>();
        int itemNum = 1;
        if (!itemList.isEmpty()) {
            for (Product item : itemList) {
                itemsMap.put(itemNum, item);
                if (item instanceof Sandwich) System.out.printf("%s\n", ((Sandwich) item).displayCurrentSandwichCompact(itemNum));
                else System.out.printf("[%d] %s\n", itemNum, item);
                itemNum++;
            }

            System.out.print("\n\nWhich item would you like to edit?\n\nEnter choice: ");

            int itemEditChoice = Inputs.getInt();

            if (itemEditChoice < 0 || itemEditChoice > itemsMap.size()) {
                System.out.println("That's not a valid choice.");
                Inputs.awaitInput();
            } else {
                Product itemChoice = itemList.get(itemEditChoice - 1);

                if (itemChoice instanceof Drinks) {
                    orderDrink();
                    if (validateItemEdited(originalItemListSize, itemChoice)) userOrder.getDrinks().remove((Drinks) itemsMap.get(itemEditChoice));
                } else if (itemChoice instanceof Chips) {
                    addChips();
                    userOrder.getChips().remove((Chips) itemsMap.get(itemEditChoice));
                } else if (itemChoice instanceof Sides) {
                    orderSides();
                    userOrder.getSidesList().remove((Sides) itemsMap.get(itemEditChoice));
                } else if (itemChoice instanceof Sandwich) {
                    CreateSandwichScreen createSandwichScreen = new CreateSandwichScreen(userOrder);
                    createSandwichScreen.sandwichEditScreen((Sandwich) itemsMap.get(itemEditChoice));
                    userOrder.getSandwiches().remove((Sandwich) itemsMap.get(itemEditChoice));
                }
            }
        } else {
            System.out.println("You have nothing to edit...");
            Inputs.awaitInput();
        }

    }

    private boolean validateItemEdited(int originalListSize, Product itemChoice) {
        List<? extends Product> productList = null;
        try {
            if (itemChoice instanceof Drinks) {
                productList = userOrder.getDrinks();
            } else if (itemChoice instanceof Chips) {
                productList = userOrder.getChips();
            } else if (itemChoice instanceof Sides) {
                productList = userOrder.getSidesList();
            } else if (itemChoice instanceof Sandwich) {
                productList = userOrder.getSandwiches();
            }
            assert productList != null;
            if (productList.size() != originalListSize) return true;
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
        return false;
    }
    private boolean validateUserChoice(int userChoice, Map<Integer, String> itemsList) {
        if (userChoice < 0 || userChoice > itemsList.size()) {
            System.out.println("That's not a valid choice.");
            Inputs.awaitInput();
            return false;
        }
        return true;
    }
}
