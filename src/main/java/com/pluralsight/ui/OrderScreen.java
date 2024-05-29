package com.pluralsight.ui;

import com.pluralsight.models.abstractModel.Product;
import com.pluralsight.util.*;
import com.pluralsight.models.*;
import com.pluralsight.dataManagers.ReceiptManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderScreen {
    private final Order userOrder;

    public OrderScreen() {
        this.userOrder = new Order();
    }

    //DISPLAYING MENUS

    public void homeScreen() {
        Text.clearConsole();
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
        Text.clearConsole();
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
        Text.clearConsole();
        System.out.println(Text.centerMessage("Choosing chips", 50, '='));
        System.out.print("\n");
        userOrder.chipsList.forEach((number, chip) -> System.out.printf("[%s] %s\n", number, chip));
        System.out.println("\n[x] Cancel sides choice");
        System.out.print("Enter choice: ");

        if (processChipsMenuChoice()) addChips();
    }

    private void orderSides() {
        Text.clearConsole();
        System.out.println(Text.centerMessage("Choosing sides", 50, '='));
        System.out.print("\n");
        userOrder.sides.forEach((number, side) -> System.out.printf("[%s] %s\n", number, side));
        System.out.println("\n[x] Cancel sides choice");
        System.out.print("Enter choice: ");

        if (processSidesMenuChoice()) orderSides();
    }

    //PROCESSING MENU CHOICES

    private boolean processOrderMenuChoice() {
        String orderMenuChoice = Inputs.getString();

        switch (orderMenuChoice) {
            case "1" -> {
                CreateSandwichScreen createSandwichScreen = new CreateSandwichScreen(userOrder);
                createSandwichScreen.sandwichCreationHomeScreen();
            }
            case "2" -> orderDrink();
            case "3" -> addChips();
            case "4" -> orderSides();
            case "E", "e" -> {
                //Ask user what items they would like to edit
                Text.clearConsole();

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
                Text.clearConsole();
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
                System.out.println("This is not a valid choice");
                Inputs.awaitInput();
            }
        }
        return true;
    }

    private boolean processDrinkMenuChoice() {
        String userChoice = Inputs.getString();

        Drinks drink = new Drinks();
        String size;
        double price;

        switch (userChoice) {
            case "1" -> {
                size = "Small";
                price = 2.00;
            }
            case "2" -> {
                size = "Medium";
                price = 2.50;
            }
            case "3" -> {
                size = "Large";
                price = 3.00;
            }
            case "X", "x" -> {
                return false;
            }
            default -> {
                System.out.println("That's not a valid choice.");
                Inputs.awaitInput();
                return true;
            }
        }

        drink.setSize(size);
        drink.setPrice(price);
        Text.clearConsole();
        System.out.println("""
                
                What drink would you like?
                """);
        userOrder.drinksList.forEach((num, d) -> System.out.printf("[%s] %s\n", num, d));
        System.out.print("\n[x] Cancel drink choice\n\nEnter choice: ");
        String drinkTypeChoice = Inputs.getString();

        if (userOrder.drinksList.containsKey(drinkTypeChoice)) {
            drink.setBrand(userOrder.drinksList.get(drinkTypeChoice));
            userOrder.addDrink(drink);
            return false;
        } else if (drinkTypeChoice.equalsIgnoreCase("x")) {
            System.out.println("Cancelling drink choice...");
            Inputs.awaitInput();
        } else {
            System.out.println("That's not a valid drink choice, please try again...");
            Inputs.awaitInput();
        }
        return true;
    }

    private boolean processChipsMenuChoice() {
        String userChoice = Inputs.getString();

        if (userOrder.chipsList.containsKey(userChoice)) {
            Chips chip = new Chips();
            chip.setName(userOrder.chipsList.get(userChoice));
            userOrder.addChips(chip);
            return false;
        } else {
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

        Sides side = new Sides();

        if (userOrder.sides.containsKey(userChoice)) {
            side.setName(userOrder.sides.get(userChoice));
            userOrder.addSide(side);
            return false;
        } else {
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

        switch (userChoice) {
            case "1":
                selectWhichItemToEdit(userOrder.getSandwiches());
                break;
            case "2":
                selectWhichItemToEdit(userOrder.getDrinks());
                break;
            case "3":
                selectWhichItemToEdit(userOrder.getChips());
                break;
            case "4":
                selectWhichItemToEdit(userOrder.getSidesList());
                break;
            case "X", "x":
                break;
            default:
                System.out.println("This was not a valid menu choice. Please try again...");
                Inputs.awaitInput();
                break;
        }
    }

    private void selectWhichItemToEdit(List<? extends Product> itemList) {
        int originalItemListSize = itemList.size();
        Text.clearConsole();
        System.out.println(Text.centerMessage(" Choose item to edit ", 50, '='));
        System.out.println();
        Map<Integer, Product> itemsMap = new HashMap<>();
        int itemNum = 1;
        if (!itemList.isEmpty()) {
            for (Product item : itemList) {
                itemsMap.put(itemNum, item);
                if (item instanceof Sandwich)
                    System.out.printf("%s\n", ((Sandwich) item).displayCurrentSandwichCompact(itemNum));
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
                    if (validateItemEdited(originalItemListSize, itemChoice))
                        userOrder.getDrinks().remove((Drinks) itemsMap.get(itemEditChoice));
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

    //VALIDATION CHECKS

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

}
