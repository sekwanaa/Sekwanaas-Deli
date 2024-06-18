package com.pluralsight.ui;

import com.pluralsight.dataManagers.ReceiptManager;
import com.pluralsight.util.Inputs;

import java.util.*;

public class HomeScreen {

    public static void displayHomeScreen() {
        displayWelcomeMessage();

        System.out.print("""
                
                [1] Create a new order
                [2] View receipts (Admin Only)
                
                [0] Exit
                
                Enter choice:\s""");

        if (processHomeScreenChoice()) displayHomeScreen();
    }

    private static void displayWelcomeMessage() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nWelcome to our DELI-cious application");
        System.out.println("What can we do for you today?");
    }

    private static boolean processHomeScreenChoice() {
        String userChoice = Inputs.getString();

        switch (userChoice) {
            case "1", "one" -> {
                OrderScreen userOrder = new OrderScreen();
                userOrder.homeScreen();
            }
            case "2", "two" -> {
                List<String> receipts = ReceiptManager.getAllReceipts();
                if (receipts == null) {
                    System.out.println("You didn't enter the correct password.");
                } else {
                    receipts.forEach(System.out::println);
                }
                Inputs.awaitInput();
            }
            case "0", "zero" -> System.exit(0);
            default -> {
                System.out.println("That's not a valid choice, please try again...");
                Inputs.awaitInput();
            }
        }
        return true;
    }
}
