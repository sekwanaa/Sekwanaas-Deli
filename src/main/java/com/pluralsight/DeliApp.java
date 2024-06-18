package com.pluralsight;

import com.pluralsight.util.Inputs;
import com.pluralsight.ui.HomeScreen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliApp {


    public static void main(String[] args) {

        Inputs.openScanner();

        SpringApplication.run(DeliApp.class, args);
        
        HomeScreen.displayHomeScreen();

        Inputs.closeScanner();
    }


}
