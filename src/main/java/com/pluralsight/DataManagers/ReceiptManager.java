package com.pluralsight.DataManagers;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
