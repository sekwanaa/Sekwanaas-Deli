package com.pluralsight.models;

import java.util.Map;
import java.util.TreeMap;

public abstract class Order {
    /*Creating an abstract class which all orders can
    * use to create uniformity
    * */
    private String size;
    private String type;
    private double price;


    public static final Map<Integer, String> sides = new TreeMap<>(Map.of(
            1, "au jus",
            2, "sauce"
    ));


    //Getters and Setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
