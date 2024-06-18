package com.pluralsight.models;

import com.pluralsight.models.abstractModel.Product;

public class Side extends Product {
    private String name;

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
