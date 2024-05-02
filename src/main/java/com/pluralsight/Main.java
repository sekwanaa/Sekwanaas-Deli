package com.pluralsight;

import com.pluralsight.userInterfaces.Order;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nWelcome to our DELI-cious application");
                System.out.println("What can we do for you today?");
                System.out.println("""
                
                [1] Create a new order
                [0] Exit
                """);
                System.out.print("Enter choice: ");
                int userChoice = scanner.nextInt();

                switch (userChoice) {
                    case 1 -> {
                        Order userOrder = new Order();
                        userOrder.homeScreen(scanner);
                    }
                    case 0 -> System.exit(0);
                    default -> System.out.println("That's not a valid choice, please try again...");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input type, please try again...");
        }
    }


}
