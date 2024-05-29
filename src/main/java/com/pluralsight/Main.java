package com.pluralsight;

import com.pluralsight.util.Inputs;
import com.pluralsight.ui.HomeScreen;

public class Main {


    public static void main(String[] args) {
        Inputs.openScanner();

        HomeScreen.displayHomeScreen();

        Inputs.closeScanner();
    }


}
