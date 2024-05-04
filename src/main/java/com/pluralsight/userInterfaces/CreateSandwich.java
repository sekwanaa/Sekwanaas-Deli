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


    public void homeScreen(Scanner scanner) {
        Sandwich userSandwich = new Sandwich();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(userSandwich.getPremiumToppings());
            System.out.print("""
                    
                    -------Customize your sandwich-------
                    
                    [1] Choose bread size
                    [2] Choose bread type
                    [3] Choose premium toppings
                    [4] Choose regular toppings
                    [5] Choose sauces
                    
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
                        // choose sauces
                        break;
                    default:
                        System.out.println("This is not a valid choice, please try again.");
                        break;
                }
            } else {
                String userChoice = scanner.nextLine();
                switch (userChoice) {
                    case "F", "f" -> {
                        if (checkIfRequiredItemsAreChosen(userSandwich)) {
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
        boolean isChoosing = true;
        while (isChoosing) {
            System.out.println("Please choose which toppings to add:");
            toppings.forEach((number, topping) -> {
                System.out.printf("[%d] %s\n", number, topping);
            });
            System.out.print("""
                    
                    [D] Done
                    
                    Enter choice:\s""");
            int userChoice;

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice < 0 || userChoice > toppings.size()) {
                    System.out.println("Please provide a valid choice...");
                } else if (userChoice == 0) {
                    break;
                } else {
                    chosenToppings.add(toppings.get(userChoice));
                }
                System.out.println(chosenToppings);
            } else {
                scanner.nextLine();
                String userDone = scanner.nextLine();
                if (userDone.equalsIgnoreCase("d")) {
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
                            isChoosing = false;
                        }
                        case "n", "N" -> System.out.println("Okay, let's try again...");
                    }
                } else {
                    System.out.println("This is not an integer... Please enter the correct data type.");
                }
            }
        }
    }


    private boolean checkIfRequiredItemsAreChosen(Sandwich userSandwich) {
        System.out.println(userSandwich.getSize());
        System.out.println(userSandwich.getType());
        return userSandwich.getSize() != null && userSandwich.getType() != null;
    }
}
