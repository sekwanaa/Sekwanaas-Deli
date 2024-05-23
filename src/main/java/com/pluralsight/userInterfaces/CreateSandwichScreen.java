package com.pluralsight.userInterfaces;

import com.pluralsight.Utilities.Inputs;
import com.pluralsight.Utilities.Utilities;
import com.pluralsight.models.Order;
import com.pluralsight.models.Sandwich;

import java.util.*;

public class CreateSandwichScreen{
    private final Order userOrder;

    public CreateSandwichScreen(Order userOrder) {
        this.userOrder = userOrder;
    }

    //Methods

    public void sandwichCreationScreen() {
        Sandwich userSandwich = new Sandwich();
        boolean isRunning = true;
        while (isRunning) {
            Utilities.clearConsole();
            System.out.printf("\n%s\n", userSandwich.displayCurrentSandwich());
            System.out.println(Utilities.centerMessage("Customize your sandwich", 45, '-'));
            System.out.print("""
                    
                    [1] Choose bread size
                    [2] Choose bread type
                    [3] Choose meats (extra charge)
                    [4] Choose regular toppings
                    [5] Choose cheese (only 1 cheese allowed)
                    [6] Choose sauces
                    
                    [f] Finalize sandwich
                    [x] Cancel sandwich
                    
                    Enter choice:\s""");

            isRunning = processSandwichCreationMenuChoice(userSandwich, isRunning);
        }
    }

    private boolean processSandwichCreationMenuChoice(Sandwich userSandwich, boolean isRunning) {
        String userChoice = Inputs.getString();

        try {
            int userIntChoice = Integer.parseInt(userChoice);
            switch (userIntChoice) {
                case 1:
                    chooseBreadSize(userSandwich);
                    break;
                case 2:
                    chooseBreadType(userSandwich);
                    break;
                case 3:
                    chooseToppings(userSandwich, "premium");
                    break;
                case 4:
                    chooseToppings(userSandwich, "regular");
                    break;
                case 5:
                    chooseCheese(userSandwich);
                    break;
                case 6:
                    chooseSauces(userSandwich);
                    break;
                default:
                    System.out.println("This is not a valid choice, please try again.");
                    break;
            }
        } catch (NumberFormatException e) {
            switch (userChoice) {
                case "F", "f" -> {
                    // first ask if they want toasted, extra cheese, or extra meat.
                    if (checkIfRequiredItemsAreChosen(userSandwich)) {
                        System.out.print("Would you like your sandwich toasted? (Y/N): ");
                        String toasted = Inputs.getString();
                        if (toasted.equalsIgnoreCase("y")) {
                            userSandwich.setToasted(true);
                        }

                        if (userSandwich.getCheese() != null) {
                            System.out.print("Would you like to make your sandwich extra cheesy? (Y/N): ");
                            String extraCheese = Inputs.getString();
                            if (extraCheese.equalsIgnoreCase("y")) {
                                userSandwich.setExtraCheese(true);
                            }
                        }

                        if (userSandwich.getPremiumToppings() != null) {
                            System.out.print("Would you like you sandwich to be extra meaty? (Y/N): ");
                            String extraMeat = Inputs.getString();
                            if (extraMeat.equalsIgnoreCase("y")) {
                                userSandwich.setExtraMeat(true);
                            }
                        }
                        userOrder.addSandwichToOrder(userSandwich);
                        isRunning = false;
                    } else {
                        System.out.println("Sorry, it seems that you haven't chosen the required items (sandwich size / sandwich type)\nPlease select one of each to finalize your sandwich.");
                    }
                }
                case "X", "x" -> isRunning = false;
                default -> System.out.println("This is not a valid choice, please try again.");

            }
        }
        return isRunning;
    }


    private void chooseBreadSize(Sandwich userSandwich) {
        Utilities.clearConsole();
        System.out.println(Utilities.centerMessage("Choosing Bread Size", 50, '='));
        System.out.print("\n");

        System.out.print("""
                [1] 4" (price)
                [2] 6" (price)
                [3] 8" (price)
                
                Enter choice:\s""");
        int breadSizeChoice = Inputs.getInt();
        switch (breadSizeChoice) {
            case 1 -> userSandwich.setSize("4\"");
            case 2 -> userSandwich.setSize("8\"");
            case 3 -> userSandwich.setSize("12\"");
            default -> {
                System.out.println("That is not a valid choice, please select a valid option...");
                chooseBreadSize(userSandwich);
            }
        }
    }


    private void chooseBreadType(Sandwich userSandwich) {
        Utilities.clearConsole();
        System.out.println(Utilities.centerMessage("Choosing Bread Type", 50, '='));
        System.out.print("\n");

        System.out.print("""
                [1] Wheat
                [2] White
                [3] Rye
                [4] Wrap
                
                Enter choice:\s""");
        int breadChoice = Inputs.getInt();
        switch (breadChoice) {
            case 1 -> userSandwich.setType("Wheat");
            case 2 -> userSandwich.setType("White");
            case 3 -> userSandwich.setType("Rye");
            case 4 -> userSandwich.setType("Wrap");
            default -> {
                System.out.println("That is not a valid choice, please select a valid option...");
                chooseBreadType(userSandwich);
            }
        }
    }

    private void chooseToppings(Sandwich userSandwich, String type) {
        Map<Integer, String> toppings = type.equals("premium") ? userOrder.premiumToppings : userOrder.regularToppings;
        Set<String> chosenToppings = new HashSet<>();

        boolean isChoosingToppings = true;
        while (isChoosingToppings) {
            Utilities.clearConsole();
            System.out.println(Utilities.centerMessage("Choosing toppings", 50, '='));
            System.out.print("\n");
            System.out.println("Please choose which toppings to add:");
            toppings.forEach((number, topping) -> System.out.printf("[%d] %s\n", number, topping));
            System.out.print("""
                    
                    [D] Done
                    [x] Cancel choosing toppings...
                    
                    Enter choice:\s""");
            String userChoice = Inputs.getString();

            try {
                int userIntChoice = Integer.parseInt(userChoice);
                if (userIntChoice < 0 || userIntChoice > toppings.size()) {
                    System.out.println("Please provide a valid choice...");
                } else {
                    chosenToppings.add(toppings.get(userIntChoice));
                }
            } catch (NumberFormatException e) {
                switch (userChoice) {
                    case "D", "d":
                        System.out.printf("""
                                Summary of %s toppings:
                                
                                """, type);
                        chosenToppings.forEach(System.out::println);
                        System.out.print("Is this correct? (Y/N): ");
                        String accepted = Inputs.getString();
                        switch (accepted) {
                            case "y", "Y" -> {
                                if (type.equals("premium")) {
                                    userSandwich.setPremiumToppings(chosenToppings);
                                } else if (type.equals("regular")) {
                                    userSandwich.setRegularToppings(chosenToppings);
                                }
                                isChoosingToppings = false;
                            }
                            case "n", "N" -> System.out.println("Okay, let's try again...");
                            default -> System.out.println("That is not a valid choice, try again...");
                        }
                        break;
                    case "X", "x":
                        isChoosingToppings = false;
                        break;
                    default:
                        System.out.println("This is not a valid choice... Please enter a valid choice.");
                        break;
                }
            }
        }
    }


    private void chooseCheese(Sandwich userSandwich) {
        Map<Integer, String> cheeses = userOrder.cheeses;
        boolean isChoosingCheeses = true;
        while (isChoosingCheeses) {
            Utilities.clearConsole();
            System.out.println(Utilities.centerMessage("Choosing cheese", 50, '='));
            System.out.print("\n");
            cheeses.forEach((number, cheese) -> System.out.printf("[%d] %s\n", number, cheese));
            System.out.println("\n[x] Cancel choosing cheese...\n\n");
            System.out.print("Enter choice: ");

            String userChoice = Inputs.getString();
            try {
                int userIntChoice = Integer.parseInt(userChoice);
                if (userIntChoice < 0 || userIntChoice > cheeses.size()) {
                    System.out.println("Please provide a valid choice...");
                } else {
                    userSandwich.setCheese(cheeses.get(userIntChoice));
                    isChoosingCheeses = false;
                }
            } catch (NumberFormatException e) {
                if (userChoice.equalsIgnoreCase("x")) {
                    System.out.println("Ok, no cheese...");
                    isChoosingCheeses = false;
                } else {
                    System.out.println("This is not a valid choice... Please enter a valid choice.");
                }
            }
        }
    }


    private void chooseSauces(Sandwich userSandwich) {
        Map<Integer, String> sauces = userOrder.sauces;
        Set<String> chosenSauces = new HashSet<>();
        boolean isChoosingSauces = true;
        while (isChoosingSauces) {
            Utilities.clearConsole();
            System.out.println(Utilities.centerMessage("Choosing sauces", 50, '='));
            System.out.print("\n");
            System.out.println("Please choose which toppings to add:");
            sauces.forEach((number, sauce) -> System.out.printf("[%d] %s\n", number, sauce));
            System.out.print("""
                    
                    [D] Done
                    
                    Enter choice:\s""");
            String userChoice = Inputs.getString();

            try {
                int userIntChoice = Integer.parseInt(userChoice);
                if (userIntChoice < 0 || userIntChoice > sauces.size()) {
                    System.out.println("Please provide a valid choice...");
                } else if (userIntChoice == 0) {
                    break;
                } else {
                    chosenSauces.add(sauces.get(userIntChoice));
                }
            } catch (NumberFormatException e) {
                if (userChoice.equalsIgnoreCase("d")) {
                    System.out.println("\nSummary of sauces:");

                    chosenSauces.forEach(System.out::println);
                    System.out.print("Is this correct? (Y/N): ");
                    String accepted = Inputs.getString();
                    switch (accepted) {
                        case "y", "Y" -> {
                            userSandwich.setSauces(chosenSauces);
                            isChoosingSauces = false;
                        }
                        case "n", "N" -> System.out.println("Okay, let's try again...");
                    }
                } else {
                    System.out.println("This is not a valid choice... Please enter a valid choice.");
                }
            }
        }
    }


    private boolean checkIfRequiredItemsAreChosen(Sandwich userSandwich) {
        return userSandwich.getSize() != null && userSandwich.getType() != null;
    }
}
