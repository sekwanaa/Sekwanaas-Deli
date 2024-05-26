package com.pluralsight.DataManagers;

import com.pluralsight.Constants.AdminPasswords;
import com.pluralsight.Utilities.Inputs;
import com.pluralsight.Utilities.Utilities;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReceiptManager {

    public static void createReceipt(String receipt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        String date = formatter.format(LocalDateTime.now());
        try (FileWriter fileWriter = new FileWriter("src/main/resources/receipts" + date + ".txt")) {
            fileWriter.write(receipt);
            System.out.println("Receipt Created...");
        } catch (IOException e) {
            throw new RuntimeException("File not found: " + e);
        }
    }

    public static List<String> getAllReceipts() {
        if (!verifyPassword()) return null;
        String filePath = "src/main/resources/receipts";

        File folder = new File(filePath);
        System.out.println(folder.getName());
        List<String> receipts = new ArrayList<>();

        if (folder.isDirectory() && folder.exists()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    StringBuilder receipt = new StringBuilder();
                    if (file.isFile()) {
                        System.out.println("\n\n");
                        System.out.println(Utilities.centerMessage(String.format("Current File: %s", file.getName()), 51, '='));
                        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                            String input;
                            while ((input = bufferedReader.readLine()) != null) {
                                receipt.append(input).append("\n");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("The specified folder path does not exist or is not a directory." + e);
                        }
                    }
                    receipts.add(receipt.toString());
                }
            }
        }
        return receipts;
    }

    private static boolean verifyPassword() {
        Utilities.clearConsole();
        System.out.print("Enter Password: ");
        String userInput = Inputs.getString();
        return userInput.equals(AdminPasswords.RECEIPT_LOGS.password);
    }
}
