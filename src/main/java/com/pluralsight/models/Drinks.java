package com.pluralsight.models;

import java.util.Map;
import java.util.TreeMap;

public class Drinks extends Order {
    public static final Map<Integer, String> drinksList = new TreeMap<>(Map.of(
            1, "Coke",
            2, "Root Beer",
            3, "Pepsi",
            4, "Dr. Pepper",
            5, "Fanta",
            6, "Lemonade"
    ));


}
