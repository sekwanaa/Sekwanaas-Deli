package com.pluralsight.models;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private final String receipt;

    public Receipt(String receipt) {
        this.receipt = receipt;
    }


    public void createReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        String date = formatter.format(LocalDateTime.now());
        try (FileWriter fileWriter = new FileWriter("src/main/resources/" + date + ".txt")) {
            fileWriter.write(receipt);
        } catch (IOException e) {
            throw new RuntimeException("File not found: " + e);
        }
    }


}
