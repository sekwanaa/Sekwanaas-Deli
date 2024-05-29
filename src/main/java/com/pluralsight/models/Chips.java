package com.pluralsight.models;

public class Chips extends Product {
    private String name;


    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return 1.50;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
