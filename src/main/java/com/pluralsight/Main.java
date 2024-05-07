package com.pluralsight;

import com.pluralsight.userInterfaces.OrderScreen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nWelcome to our DELI-cious application");
                System.out.println("What can we do for you today?");
                System.out.print("""
                
                [1] Create a new order
                [0] Exit
                
                Enter choice:\s""");
                System.out.print("Enter choice: ");

                if (scanner.hasNextInt()) {
                    int userChoice = scanner.nextInt();

                    switch (userChoice) {
                        case 1 -> {
                            OrderScreen userOrder = new OrderScreen();
                            userOrder.homeScreen(scanner);
                        }
                        case 0 -> System.exit(0);
                        default -> System.out.println("That's not a valid choice, please try again...");
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("\nThat is not a valid input type, please select 1 or 0.");
                    System.out.println("Press ENTER if you understand...");
                    scanner.nextLine();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input type, please try again...");
        }
    }


}
