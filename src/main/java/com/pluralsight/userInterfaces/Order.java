package com.pluralsight.userInterfaces;

import com.pluralsight.models.Chips;
import com.pluralsight.models.Drinks;
import com.pluralsight.models.Sandwich;

import java.util.List;
import java.util.Scanner;

public class Order {
    private List<Sandwich> sandwich;
    private List<Drinks> drinks;
    private List<Chips> chips;

    public void homeScreen(Scanner scanner) {
        while (true) {
            System.out.println("""
                What would you like to add to your order?
                
                [1] Sandwich
                [2] Drinks
                [3] Chips
                [4] Sides
                [5] Sauces
                [0] Previous screen
                """);
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("Sandwiches");
                    break;
                case 2:
                    System.out.println("Drinks");
                    break;
                case 3:
                    System.out.println("Chips");
                    break;
                case 4:
                    System.out.println("Sides");
                    break;
                case 5:
                    System.out.println("Sauces");
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("That's not a valid choice, please try again...");
            }
        }
    }



}
