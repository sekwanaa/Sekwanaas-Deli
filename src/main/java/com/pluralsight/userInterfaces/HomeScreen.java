package com.pluralsight.userInterfaces;

import com.pluralsight.DataManagers.ReceiptManager;
import com.pluralsight.Utilities.Inputs;

import java.util.*;

public class HomeScreen {

    public static void displayHomeScreen() {
        boolean usingApplication = true;
        while (usingApplication) {
            displayWelcomeMessage();

            System.out.print("""
                
                [1] Create a new order
                [2] View receipts (Admin Only)
                
                [0] Exit
                
                Enter choice:\s""");

            usingApplication = processHomeScreenChoice();
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nWelcome to our DELI-cious application");
        System.out.println("What can we do for you today?");
    }

    private static boolean processHomeScreenChoice() {
        int userChoice = Inputs.getInt();

        switch (userChoice) {
            case 1 -> {
                OrderScreen userOrder = new OrderScreen();
                userOrder.homeScreen();
            }
            case 2 -> {
                List<String> receipts = ReceiptManager.getAllReceipts();
                if (receipts == null) {
                    System.out.println("You didn't enter the correct password.");
                } else {
                    receipts.forEach(System.out::println);
                }
                Inputs.awaitInput();
            }
            case 0 -> {
                return false;
            }
            default -> {
                System.out.println("That's not a valid choice, please try again...");
                Inputs.awaitInput();
            }
        }
        return true;
    }
}
