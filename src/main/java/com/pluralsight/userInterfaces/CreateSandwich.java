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
            System.out.print("""
                    
                    -------Customize your sandwich-------
                    
                    [1] Choose bread size
                    [2] Choose bread type
                    [3] Choose premium toppings
                    [4] Choose regular toppings
                    [5] Choose sauces
                    [0] Cancel sandwich
                    
                    Enter choice:\s""");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    chooseBreadSize(scanner, userSandwich);
                    break;
                case 2:
                    chooseBreadType(scanner, userSandwich);
                    break;
                case 3:
                    choosePremiumToppings(scanner, userSandwich);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    isRunning = false;
                    break;
            }
        }
        System.out.println(userSandwich.getSize());
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

    private void choosePremiumToppings(Scanner scanner, Sandwich userSandwich) {
        Set<String> premiumToppings = new HashSet<>();
        while (true) {
            System.out.println("Please choose which toppings to add:");
            this.premiumToppings.forEach((number, topping) -> {
                System.out.printf("[%d] %s\n", number, topping);
            });
            System.out.print("""
                    
                    [D] Done
                    
                    Enter choice:\s""");
            int userChoice;

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice < 0 || userChoice > 6) {
                    System.out.println("Please provide a valid choice...");
                } else if (userChoice == 0) {
                    break;
                } else {
                    premiumToppings.add(this.premiumToppings.get(userChoice));
                }
                System.out.println(premiumToppings);
            } else {
                scanner.nextLine();
                String userDone = scanner.nextLine();
                if (userDone.equalsIgnoreCase("d")) {
                    System.out.println("""
                            Summary of premium toppings:
                            """);
                    premiumToppings.forEach(System.out::println);
                    System.out.print("Is this correct? (Y/N): ");
                    String accepted = scanner.nextLine();
                    if (accepted.equalsIgnoreCase("y")) {
                        userSandwich.setPremiumToppings(premiumToppings);
                        break;
                    } if (accepted.equalsIgnoreCase("n")) {
                        System.out.println("Okay, let's try again...");
                        break;
                    }
                } else {
                    System.out.println("This is not an integer... Please enter the correct data type.");
                }
            }

        }
    }
}
