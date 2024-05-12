package com.pluralsight.userInterfaces;

import com.pluralsight.Utilities.Utilities;
import com.pluralsight.models.Sandwich;

import java.util.*;

public class CreateSandwich {


    public void homeScreen(Scanner scanner) {
        Sandwich userSandwich = new Sandwich();
        boolean isRunning = true;
        while (isRunning) {
            Utilities.createBigBlankSpace();
            System.out.printf("\n%s\n", userSandwich);
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

            if (scanner.hasNextInt()) {
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1:
                        chooseBreadSize(scanner, userSandwich);
                        break;
                    case 2:
                        chooseBreadType(scanner, userSandwich);
                        break;
                    case 3:
                        chooseToppings(scanner, userSandwich, Sandwich.premiumToppings, "premium");
                        break;
                    case 4:
                        chooseToppings(scanner, userSandwich, Sandwich.regularToppings, "regular");
                        break;
                    case 5:
                        chooseCheese(scanner, userSandwich);
                        break;
                    case 6:
                        chooseSauces(scanner, userSandwich);
                        break;
                    default:
                        System.out.println("This is not a valid choice, please try again.");
                        break;
                }
            } else {
                String userChoice = scanner.nextLine();
                switch (userChoice) {
                    case "F", "f" -> {
                        // first ask if they want toasted, extra cheese, or extra meat.
                        if (checkIfRequiredItemsAreChosen(userSandwich)) {
                            System.out.print("Would you like your sandwich toasted? (Y/N): ");
                            String toasted = scanner.nextLine();
                            if (toasted.equalsIgnoreCase("y")) {
                                userSandwich.setToasted(true);
                            }

                            if (userSandwich.getCheese() != null) {
                                System.out.print("Would you like to make your sandwich extra cheesy? (Y/N): ");
                                String extraCheese = scanner.nextLine();
                                if (extraCheese.equalsIgnoreCase("y")) {
                                    userSandwich.setExtraCheese(true);
                                }
                            }

                            if (userSandwich.getPremiumToppings() != null) {
                                System.out.print("Would you like you sandwich to be extra meaty? (Y/N): ");
                                String extraMeat = scanner.nextLine();
                                if (extraMeat.equalsIgnoreCase("y")) {
                                    userSandwich.setExtraMeat(true);
                                }
                            }
                            OrderScreen.sandwich.add(userSandwich);
                            isRunning = false;
                        } else {
                            System.out.println("Sorry, it seems that you haven't chosen the required items (sandwich size / sandwich type)\nPlease select one of each to finalize your sandwich.");
                        }
                    }
                    case "X", "x" -> isRunning = false;
                    default -> System.out.println("This is not a valid choice, please try again.");

                }
            }

        }
    }


    private void chooseBreadSize(Scanner scanner, Sandwich userSandwich) {
        boolean isChoosingBreadSize = true;
        Utilities.createBigBlankSpace();
        System.out.println(Utilities.centerMessage("Choosing Bread Size", 50, '='));
        System.out.print("\n");
        String breadSize = "";
        while (isChoosingBreadSize) {
            System.out.print("""
                    [1] 4" (price)
                    [2] 6" (price)
                    [3] 8" (price)
                    
                    Enter choice:\s""");
            int breadSizeChoice = scanner.nextInt();
            switch (breadSizeChoice) {
                case 1 -> {
                    breadSize = "4\"";
                    isChoosingBreadSize = false;
                }
                case 2 -> {
                    breadSize = "8\"";
                    isChoosingBreadSize = false;
                }
                case 3 -> {
                    breadSize = "12\"";
                    isChoosingBreadSize = false;
                }
                default -> System.out.println("That is not a valid choice, please select a valid option...");

            }
        }
        userSandwich.setSize(breadSize);
    }


    private void chooseBreadType(Scanner scanner, Sandwich userSandwich) {
        boolean isChoosingBreadType = true;
        Utilities.createBigBlankSpace();
        System.out.println(Utilities.centerMessage("Choosing Bread Type", 50, '='));
        System.out.print("\n");
        String breadType = "";
        while (isChoosingBreadType) {
            System.out.print("""
                            [1] Wheat
                            [2] White
                            [3] Rye
                            [4] Wrap
                            
                            Enter choice:\s""");
            int breadChoice = scanner.nextInt();
            switch (breadChoice) {
                case 1 -> {
                    breadType = "Wheat";
                    isChoosingBreadType = false;
                }
                case 2 -> {
                    breadType = "White";
                    isChoosingBreadType = false;
                }
                case 3 -> {
                    breadType = "Rye";
                    isChoosingBreadType = false;
                }
                case 4 -> {
                    breadType = "Wrap";
                    isChoosingBreadType = false;
                }
                default -> System.out.println("That is not a valid choice, please select a valid option...");
            }

        }
        userSandwich.setType(breadType);
    }

    private void chooseToppings(Scanner scanner, Sandwich userSandwich, Map<Integer, String> toppings, String type) {
        Utilities.createBigBlankSpace();
        System.out.println(Utilities.centerMessage("Choosing toppings", 50, '='));
        System.out.print("\n");
        Set<String> chosenToppings = new HashSet<>();
        boolean isChoosingToppings = true;
        while (isChoosingToppings) {
            System.out.println("Please choose which toppings to add:");
            toppings.forEach((number, topping) -> System.out.printf("[%d] %s\n", number, topping));
            System.out.print("""
                    
                    [D] Done
                    [x] Cancel choosing toppings...
                    
                    Enter choice:\s""");
            int userChoice;

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                if (userChoice < 0 || userChoice > toppings.size()) {
                    System.out.println("Please provide a valid choice...");
                } else if (userChoice == 0) {
                    break;
                } else {
                    chosenToppings.add(toppings.get(userChoice));
                }
            } else {
                String userDone = scanner.nextLine();
                switch (userDone) {
                    case "D", "d":
                        System.out.printf("""
                            Summary of %s toppings:
                            
                            """, type);
                        chosenToppings.forEach(System.out::println);
                        System.out.print("Is this correct? (Y/N): ");
                        String accepted = scanner.nextLine();
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


    private void chooseCheese(Scanner scanner, Sandwich userSandwich) {
        boolean isChoosingCheeses = true;
        Utilities.createBigBlankSpace();
        System.out.println(Utilities.centerMessage("Choosing cheese", 50, '='));
        System.out.print("\n");
        while (isChoosingCheeses) {
            Sandwich.cheeses.forEach((number, cheese) -> System.out.printf("[%d] %s\n", number, cheese));
            System.out.println("\n[x] Cancel choosing cheese...\n\n");
            System.out.print("Enter choice: ");
            if (scanner.hasNextInt()) {
                int userChoice = scanner.nextInt();
                if (userChoice < 0 || userChoice > Sandwich.cheeses.size()) {
                    System.out.println("Please provide a valid choice...");
                } else {
                    userSandwich.setCheese(Sandwich.cheeses.get(userChoice));
                    isChoosingCheeses = false;
                }
            } else {
                scanner.nextLine();
                String userDone = scanner.nextLine();
                if (userDone.equalsIgnoreCase("x")) {
                    System.out.println("Ok, no cheese...");
                    isChoosingCheeses = false;
                } else {
                    System.out.println("This is not a valid choice... Please enter a valid choice.");
                }
            }
        }
    }


    private void chooseSauces(Scanner scanner, Sandwich userSandwich) {
        Set<String> chosenSauces = new HashSet<>();
        boolean isChoosingSauces = true;
        Utilities.createBigBlankSpace();
        System.out.println(Utilities.centerMessage("Choosing sauces", 50, '='));
        System.out.print("\n");
        while (isChoosingSauces) {
            System.out.println("Please choose which toppings to add:");
            Sandwich.sauces.forEach((number, sauce) -> System.out.printf("[%d] %s\n", number, sauce));
            System.out.print("""
                    
                    [D] Done
                    
                    Enter choice:\s""");
            int userChoice;

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice < 0 || userChoice > Sandwich.sauces.size()) {
                    System.out.println("Please provide a valid choice...");
                } else if (userChoice == 0) {
                    break;
                } else {
                    chosenSauces.add(Sandwich.sauces.get(userChoice));
                }
            } else {
                scanner.nextLine();
                String userDone = scanner.nextLine();
                if (userDone.equalsIgnoreCase("d")) {
                    System.out.println("\nSummary of sauces:");

                    chosenSauces.forEach(System.out::println);
                    System.out.print("Is this correct? (Y/N): ");
                    String accepted = scanner.nextLine();
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
