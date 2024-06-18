package com.pluralsight.models;

import com.pluralsight.models.abstractModel.Product;

public class Chip extends Product {
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
