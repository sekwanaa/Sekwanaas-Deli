package com.pluralsight.userInterfaces;

import com.pluralsight.models.Sandwich;

import java.util.*;

public class CreateSandwich {
    Map<Integer, String> premiumToppings = new TreeMap<>(Map.of(
            1, "Steak",
            2, "Ham",
            3, "Salami",
            4, "Roast Beef",
            5, "Chicken",
            6, "Bacon"
    ));
    Map<Integer, String> regularToppings = new TreeMap<>(Map.of(
            1, "Lettuce",
            2, "Peppers",
            3, "Onions",
            4, "Tomatoes",
            5, "Jalapenos",
            6, "Cucumbers",
            7, "Pickles",
            8, "Guacamole",
            9, "Mushrooms"
    ));
    Map<Integer, String> cheeses = new TreeMap<>(Map.of(
            1, "American",
            2, "Provolone",
            3, "Cheddar",
            4, "Swiss"
    ));
    Map<Integer, String> sauces = new TreeMap<>(Map.of(
            1, "Mayo",
            2, "Mustard",
            3, "Ketchup",
            4, "Ranch",
            5, "Thousand Islands",
            6, "Vinaigrette"
    ));


    public void homeScreen(Scanner scanner) {
        Sandwich userSandwich = new Sandwich();
        boolean isRunning = true;
        while (isRunning) {
            System.out.print("""
                    
                    -------Customize your sandwich-------
                    
                    [1] Choose bread size
                    [2] Choose bread type
                    [3] Choose premium toppings
                    [4] Choose regular toppings
                    [5] Choose cheese
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
                        chooseToppings(scanner, userSandwich, premiumToppings, "premium");
                        break;
                    case 4:
                        chooseToppings(scanner, userSandwich, regularToppings, "regular");
                        break;
                    case 5:
                        chooseCheese(scanner, userSandwich, cheeses);
                        break;
                    case 6:
                        chooseSauces(scanner, userSandwich, sauces);
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
                            Order.sandwich.add(userSandwich);
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
        System.out.print("""
                            [1] 4" (price)
                            [2] 6" (price)
                            [3] 8" (price)
                            
                            Enter choice:\s""");
        int breadSize = scanner.nextInt();
        userSandwich.setSize(breadSize);
    }


    private void chooseBreadType(Scanner scanner, Sandwich userSandwich) {
        System.out.print("""
                            [1] Wheat
                            [2] White
                            [3] Rye
                            [4] Wrap
                            
                            Enter choice:\s""");
        int breadType = scanner.nextInt();
        userSandwich.setType(breadType);
    }

    private void chooseToppings(Scanner scanner, Sandwich userSandwich, Map<Integer, String> toppings, String type) {
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
                            case "n", "N" -> {
                                System.out.println("Okay, let's try again...");
                            }
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


    private void chooseCheese(Scanner scanner, Sandwich userSandwich, Map<Integer, String> cheeses) {
        cheeses.forEach((number, cheese) -> System.out.printf("[%d] %s\n", number, cheese));
        System.out.println("\n[x] Cancel choosing cheese...\n\n");
        System.out.print("Enter choice: ");
        if (scanner.hasNextInt()) {
            int userChoice = scanner.nextInt();
            if (userChoice < 0 || userChoice > cheeses.size()) {
                System.out.println("Please provide a valid choice...");
            } else {
                userSandwich.setCheese(cheeses.get(userChoice));
            }
        } else {
            scanner.nextLine();
            String userDone = scanner.nextLine();
            if (userDone.equalsIgnoreCase("x")) {
                System.out.println("Ok, no cheese...");
            } else {
                System.out.println("This is not a valid choice... Please enter a valid choice.");
            }
        }
    }


    private void chooseSauces(Scanner scanner, Sandwich userSandwich, Map<Integer, String> sauces) {
        Set<String> chosenSauces = new HashSet<>();
        boolean isChoosingSauces = true;
        while (isChoosingSauces) {
            System.out.println("Please choose which toppings to add:");
            sauces.forEach((number, sauce) -> System.out.printf("[%d] %s\n", number, sauce));
            System.out.print("""
                    
                    [D] Done
                    
                    Enter choice:\s""");
            int userChoice;

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice < 0 || userChoice > sauces.size()) {
                    System.out.println("Please provide a valid choice...");
                } else if (userChoice == 0) {
                    break;
                } else {
                    chosenSauces.add(sauces.get(userChoice));
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
