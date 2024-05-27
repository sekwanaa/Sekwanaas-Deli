package com.pluralsight.models;

import java.util.HashSet;
import java.util.Set;

public class BLT extends Sandwich {
    public BLT() {
        this.setSize("8\"");
        this.setType("White");
        this.setCheese("Cheddar");
        this.setPremiumToppings(new HashSet<>(Set.of("Bacon")));
        this.setRegularToppings(new HashSet<>(Set.of("Lettuce", "Tomato")));
        this.setSauces(new HashSet<>(Set.of("Ranch")));
        this.setToasted(true);
    }
}
