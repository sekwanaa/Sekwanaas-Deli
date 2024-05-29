package com.pluralsight.dataManagers;

import com.pluralsight.constants.AdminPasswords;
import com.pluralsight.util.Inputs;
import com.pluralsight.util.Text;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReceiptManager {

    public static void createReceipt(String receipt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        String date = formatter.format(LocalDateTime.now());
        try (FileWriter fileWriter = new FileWriter("src/main/resources/receipts/" + date + ".txt")) {
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
                    if (file.isFile()) {
                        String receiptInfo = viewReceiptLog(file);
                        receipts.add(receiptInfo);
                    }
                }
            }
        }
        return receipts;
    }

    private static String viewReceiptLog(File file) {
        String[] fileNameSplit = file.getName().split("\\.");
        String[] dateTimeSplit = fileNameSplit[0].split("-");
        DateTimeFormatter patternMatcher = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter endFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String receiptDate = LocalDate.parse(dateTimeSplit[0], patternMatcher).format(endFormat);
        return String.format("%s | %s", receiptDate, file.getName());
    }

    private static boolean verifyPassword() {
        Text.clearConsole();
        System.out.print("Enter Password: ");
        String userInput = Inputs.getString();
        return userInput.equals(AdminPasswords.RECEIPT_LOGS.password);
    }
}
