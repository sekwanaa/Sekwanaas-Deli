package com.pluralsight.models;

import java.util.HashSet;
import java.util.Set;

public class PhillyCheeseSteak extends Sandwich {
    public PhillyCheeseSteak() {
        this.setSize("8\"");
        this.setType("White");
        this.setCheese("American");
        this.setPremiumToppings(new HashSet<>(Set.of("Steak")));
        this.setRegularToppings(new HashSet<>(Set.of("Peppers")));
        this.setSauces(new HashSet<>(Set.of("Mayo")));
        this.setToasted(true);
    }
}
