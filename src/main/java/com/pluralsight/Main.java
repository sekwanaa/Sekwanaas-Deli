package com.pluralsight;

import com.pluralsight.Utilities.Inputs;
import com.pluralsight.userInterfaces.HomeScreen;

public class Main {


    public static void main(String[] args) {
        Inputs.openScanner();

        HomeScreen.displayHomeScreen();

        Inputs.closeScanner();
    }


}
