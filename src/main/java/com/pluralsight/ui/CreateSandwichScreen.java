package com.pluralsight.ui;

import com.pluralsight.util.Inputs;
import com.pluralsight.util.Text;
import com.pluralsight.models.BLT;
import com.pluralsight.models.Order;
import com.pluralsight.models.PhillyCheeseSteak;
import com.pluralsight.models.Sandwich;

import java.util.*;

public class CreateSandwichScreen {
    private final Order userOrder;

    public CreateSandwichScreen(Order userOrder) {
        this.userOrder = userOrder;
    }

    //Methods

    //DISPLAYING MENU CHOICES

    public void sandwichCreationHomeScreen() {
        Text.clearConsole();
        System.out.println(Text.centerMessage("Choose an option", 45, '-'));
        System.out.print("""
                
                [1] Create custom sandwich
                [2] Choose a signature sandwich
                
                [x] Cancel sandwich
                
                Enter choice:\s""");

        processSandwichHomeScreenMenuChoice();
    }

    public void sandwichEditScreen() {
        Sandwich userSandwich = new Sandwich();
        Text.clearConsole();
        System.out.printf("\n%s\n", userSandwich.displayCurrentSandwich());
        System.out.println(Text.centerMessage("Customize your sandwich", 45, '-'));
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

        if (processCustomSandwichCreationMenuChoice(userSandwich)) sandwichEditScreen();
    }

    public void sandwichEditScreen(Sandwich userSandwich) {
        Text.clearConsole();
        System.out.printf("\n%s\n", userSandwich.displayCurrentSandwich());
        System.out.println(Text.centerMessage("Customize your sandwich", 45, '-'));
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

        if (processCustomSandwichCreationMenuChoice(userSandwich)) sandwichEditScreen(userSandwich);
    }

    private void chooseSignatureSandwichScreen() {
        Text.clearConsole();
        System.out.println("""
                Which signature sandwich would you like?
                
                [1] BLT
                [2] Philly Cheese Steak
                
                [x] Cancel signature sandwich
                
                Enter choice:\s""");

        processSignatureSandwichMenuChoice();
    }


    //PROCESSING MENU CHOICES

    private void processSandwichHomeScreenMenuChoice() {
        String userChoice = Inputs.getString();

        switch (userChoice) {
            case "1":
            sandwichEditScreen();
            break;
            case "2":
            chooseSignatureSandwichScreen();
            break;
            case "X", "x":
            break;
            default:
            System.out.println("This is not a valid choice, please try again.");
            Inputs.awaitInput();
            break;
        }
    }

    private boolean processCustomSandwichCreationMenuChoice(Sandwich userSandwich) {
        String userChoice = Inputs.getString();

        switch (userChoice) {
            case "1":
            chooseBreadSize(userSandwich);
            break;
            case "2":
            chooseBreadType(userSandwich);
            break;
            case "3":
            chooseToppings(userSandwich, "premium");
            break;
            case "4":
            chooseToppings(userSandwich, "regular");
            break;
            case "5":
            chooseCheese(userSandwich);
            break;
            case "6":
            chooseSauces(userSandwich);
            break;
            case "F", "f":
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
                userOrder.addSandwich(userSandwich);
                return false;
            } else {
                System.out.println("Sorry, it seems that you haven't chosen the required items (sandwich size / sandwich type)\nPlease select one of each to finalize your sandwich.");
                Inputs.awaitInput();
            }
            break;
            case "X", "x":
            return false;
            default:
            System.out.println("This is not a valid choice, please try again.");
            break;
        }
        return true;
    }

    private void processSignatureSandwichMenuChoice() {
        String userChoice = Inputs.getString();


        Sandwich sandwich = null;

        switch (userChoice) {
            case "1":
            sandwich = new BLT();
            System.out.println(sandwich.displayCurrentSandwich());
            break;
            case "2":
            sandwich = new PhillyCheeseSteak();
            System.out.println(sandwich.displayCurrentSandwich());
            break;
            case "X", "x":
            break;
            default:
            System.out.println("That's not a valid choice");
            Inputs.awaitInput();
            break;
        }

        if (sandwich != null) {
            System.out.print("Would you like to edit your sandwich? (Y/N): ");
            String editSandwich = Inputs.getString();
            if (editSandwich.equalsIgnoreCase("y")) sandwichEditScreen(sandwich);
            else userOrder.addSandwich(sandwich);
        }
    }

    //CHOOSING SANDWICH OPTIONS

    private void chooseBreadSize(Sandwich userSandwich) {
        Text.clearConsole();
        System.out.println(Text.centerMessage("Choosing Bread Size", 50, '='));
        System.out.print("\n");
        System.out.print("""
                [1] 4" ($5.50)
                [2] 8" ($7.00)
                [3] 12" ($8.50)
                
                Enter choice:\s""");
        int breadSizeChoice = Inputs.getInt();
        switch (breadSizeChoice) {
            case 1 -> userSandwich.setSize("4\"");
            case 2 -> userSandwich.setSize("8\"");
            case 3 -> userSandwich.setSize("12\"");
            default -> {
                System.out.println("That is not a valid choice, please select a valid option...");
                Inputs.awaitInput();
                chooseBreadSize(userSandwich);
            }
        }
    }

    private void chooseBreadType(Sandwich userSandwich) {
        Text.clearConsole();
        System.out.println(Text.centerMessage("Choosing Bread Type", 50, '='));
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
        Map<String, String> toppings = type.equals("premium") ? userOrder.premiumToppings : userOrder.regularToppings;
        Set<String> chosenToppings = checkIfHasToppings(userSandwich, type);

        Text.clearConsole();
        System.out.println(Text.centerMessage("Choosing toppings", 50, '='));
        System.out.printf("Current Toppings: %s\n\n", !chosenToppings.isEmpty() ? chosenToppings : "N/A");
        System.out.println("Please choose which toppings to add:");
        toppings.forEach((number, topping) -> System.out.printf("[%s] %s\n", number, topping));
        System.out.print("""
                
                [D] Done
                [x] Cancel choosing toppings...
                
                Enter choice:\s""");
        String userChoice = Inputs.getString();

        if (toppings.containsKey(userChoice)) {
            userSandwich.addToppings(toppings.get(userChoice));
            chooseToppings(userSandwich, type);
        }
        else {
            switch (userChoice) {
                case "D", "d":
                    System.out.printf("""
                        Summary of %s toppings:
                        
                        """, type);
                    displaySummary(chosenToppings);
                    System.out.print("\nIs this correct? (Y/N): ");
                    String accepted = Inputs.getString();
                    switch (accepted) {
                        case "y", "Y" -> {
                            if (type.equals("premium")) {
                                userSandwich.setPremiumToppings(chosenToppings);
                            } else if (type.equals("regular")) {
                                userSandwich.setRegularToppings(chosenToppings);
                            }
                        }
                        case "n", "N" -> {
                            System.out.println("Okay, let's try again...");
                            userSandwich.clearToppingsToAdd();
                            chooseToppings(userSandwich, type);
                        }
                        default -> {
                            System.out.println("That is not a valid choice, try again...");
                            chooseToppings(userSandwich, type);
                        }
                    }
                    break;
                case "X", "x":
                    userSandwich.clearToppingsToAdd();
                    break;
                default:
                    System.out.println("This is not a valid choice... Please enter a valid choice.");
                    chooseToppings(userSandwich, type);
                    break;
            }
        }
    }

    private void chooseCheese(Sandwich userSandwich) {
        Map<Integer, String> cheeses = userOrder.cheeses;
        boolean isChoosingCheeses = true;
        while (isChoosingCheeses) {
            Text.clearConsole();
            System.out.println(Text.centerMessage("Choosing cheese", 50, '='));
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
        Map<String, String> sauces = userOrder.sauces;
        Set<String> chosenSauces = checkIfHasSauces(userSandwich);
        Text.clearConsole();
        System.out.println(Text.centerMessage("Choosing sauces", 50, '='));
        System.out.printf("Current Sauces: %s\n\n", !chosenSauces.isEmpty() ? chosenSauces : "N/A");
        System.out.print("\n");
        System.out.println("Please choose which sauces to add:");
        sauces.forEach((number, sauce) -> System.out.printf("[%s] %s\n", number, sauce));
        System.out.print("""
                
                [d] Done
                [x] Cancel choosing sauces
                
                Enter choice:\s""");
        String userChoice = Inputs.getString();

        if (userOrder.sauces.containsKey(userChoice)) {
            userSandwich.addSauces(userOrder.sauces.get(userChoice));
            chooseSauces(userSandwich);
        } else {
            switch (userChoice) {
                case "D", "d":
                    System.out.println("\nSummary of sauces:\n");

                    displaySummary(chosenSauces);
                    System.out.print("\nIs this correct? (Y/N): ");
                    String accepted = Inputs.getString();
                    switch (accepted) {
                        case "y", "Y" -> userSandwich.setSauces(chosenSauces);
                        case "n", "N" -> {
                            System.out.println("Okay, let's try again...");
                            userSandwich.clearSaucesToAdd();
                            chooseSauces(userSandwich);
                        }
                    }
                    break;
                case "X", "x":
                    break;
                default:
                    System.out.println("This is not a valid choice... Please enter a valid choice.");
                    userSandwich.clearSaucesToAdd();
                    chooseSauces(userSandwich);
            }
        }
    }

    private void displaySummary(Set<String> chosenItems) {
        chosenItems.forEach(System.out::println);
    }

    //VALIDATION CHECKS

    private static Set<String> checkIfHasToppings(Sandwich userSandwich, String type) {
        Set<String> chosenToppings = new HashSet<>();
        if (type.equalsIgnoreCase("premium")) {
            if (userSandwich.getPremiumToppings() == null) return new HashSet<>();
            if (!userSandwich.getPremiumToppings().isEmpty()) chosenToppings = new HashSet<>(userSandwich.getPremiumToppings());
        } else if (type.equalsIgnoreCase("regular")) {
            if (userSandwich.getRegularToppings() == null) return new HashSet<>();
            if (!userSandwich.getRegularToppings().isEmpty()) chosenToppings = new HashSet<>(userSandwich.getRegularToppings());
        }

        if (!userSandwich.getToppingsToAdd().isEmpty()) chosenToppings.addAll(userSandwich.getToppingsToAdd());
        return chosenToppings;
    }

    private Set<String> checkIfHasSauces(Sandwich userSandwich) {
        Set<String> sauces = new HashSet<>();
        if (userSandwich.getSauces() == null) return new HashSet<>();
        if (!userSandwich.getSauces().isEmpty()) sauces = new HashSet<>(userSandwich.getSauces());

        if (!userSandwich.getSaucesToAdd().isEmpty()) sauces.addAll(userSandwich.getSaucesToAdd());
        return sauces;
    }

    private boolean checkIfRequiredItemsAreChosen(Sandwich userSandwich) {
        return userSandwich.getSize() != null && userSandwich.getType() != null;
    }
}
