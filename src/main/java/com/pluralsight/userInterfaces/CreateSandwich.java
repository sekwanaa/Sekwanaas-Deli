package com.pluralsight.userInterfaces;

import com.pluralsight.models.Sandwich;

import java.util.Scanner;

public class CreateSandwich {


    public void homeScreen(Scanner scanner) {
        Sandwich userSandwich = new Sandwich();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    -------Customize your sandwich-------
                    
                    [1] Choose bread size
                    [2] Choose bread type
                    [3] Choose premium toppings
                    [4] Choose regular toppings
                    [5] Choose sauces
                    [0] Cancel sandwich
                    """);
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    chooseBreadSize(scanner, userSandwich);
                    break;
                case 2:
                    chooseBreadType(scanner, userSandwich);
                    break;
                case 3:
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
        System.out.println("""
                            [1] 4" (price)
                            [2] 6" (price)
                            [3] 8" (price)
                            """);
        int breadSize = scanner.nextInt();
        userSandwich.setSize(breadSize);
    }


    private void chooseBreadType(Scanner scanner, Sandwich userSandwich) {
        System.out.println("""
                            [1] Wheat
                            [2] White
                            [3] Rye
                            [4] Wrap
                            """);
        int breadType = scanner.nextInt();
        userSandwich.setType(breadType);
    }
}
