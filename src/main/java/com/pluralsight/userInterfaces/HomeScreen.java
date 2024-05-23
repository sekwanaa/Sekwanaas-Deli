package com.pluralsight.userInterfaces;

import com.pluralsight.Utilities.Inputs;

public class HomeScreen {

    public static void displayHomeScreen() {
        displayWelcomeMessage();

        System.out.print("""
                
                [1] Create a new order
                [0] Exit
                
                Enter choice:\s""");
        System.out.print("Enter choice: ");

        processHomeScreenChoice();
    }

    private static void displayWelcomeMessage() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nWelcome to our DELI-cious application");
        System.out.println("What can we do for you today?");
    }

    private static void processHomeScreenChoice() {
        int userChoice = Inputs.getInt();

        switch (userChoice) {
            case 1 -> {
                OrderScreen userOrder = new OrderScreen();
                userOrder.homeScreen();
            }
            case 0 -> System.exit(0);
            default -> {
                System.out.println("That's not a valid choice, please try again...");
                processHomeScreenChoice();
            }
        }
    }
}
