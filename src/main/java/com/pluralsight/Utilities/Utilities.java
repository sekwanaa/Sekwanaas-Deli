package com.pluralsight.Utilities;

public class Utilities {

    private Utilities() {}

    //Text Utilities
    public static String centerMessage(String message, int width, char padChar) {
        int totalPadding = width - message.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;

        return String.valueOf(padChar).repeat(Math.max(0, leftPadding)) +
                message +
                String.valueOf(padChar).repeat(Math.max(0, rightPadding));
    }

    public static String createHeader(String header) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append(Utilities.createInnerLine()).append("\n");
        builder.append(" ");
        builder.append(header);
        int spaces = 40 - header.length();
        builder.append(" ".repeat(Math.max(0, spaces)));
        builder.append("||\n");
        builder.append(Utilities.createOuterLine()).append("\n");
        return builder.toString();
    }

    public static String createHeader(String header, double price) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append(Utilities.createInnerLine()).append("\n");
        builder.append(" ");
        builder.append(header);
        int spaces = 40 - header.length();
        builder.append(" ".repeat(Math.max(0, spaces)));
        builder.append(String.format("|| $%.2f\n", price));
        builder.append(Utilities.createOuterLine()).append("\n");
        return builder.toString();
    }

    public static String createInnerLine() {
        return "-----------------------------------------++--------";
    }

    public static String createOuterLine() {
        return "=========================================++========";
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    //Validation checks

}
