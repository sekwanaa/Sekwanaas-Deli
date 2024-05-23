package com.pluralsight.Utilities;

import java.util.Scanner;

public class Inputs {
    private static Scanner scanner;

    private Inputs() {
    }

    //Methods
    // Method to open the Scanner
    public static void openScanner() {
        scanner = new Scanner(System.in);
    }

    // Method to close the Scanner
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }

    // Helper method to make sure scanner is open when calling a method
    private static void ensureScannerIsOpen() {
        if (scanner == null) {
            openScanner();
        }
    }

    public static String getString() {
        ensureScannerIsOpen();
        return scanner.nextLine();
    }

    public static int getInt() {
        ensureScannerIsOpen();
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid number... Please enter an integer:");
            scanner.next(); // Discard invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); //eat CRLF
        return input;
    }

    public static double getDouble() {
        ensureScannerIsOpen();
        while (!scanner.hasNextDouble()) {
            System.out.println("That's not a valid double... Please enter a double:");
            scanner.next(); // Discard invalid input
        }
        double input = scanner.nextDouble();
        scanner.nextLine(); //eat CRLF
        return input;
    }

    public static void awaitInput() {
        ensureScannerIsOpen();
        scanner.nextLine();
    }
}
