package com.pluralsight.models;

import com.pluralsight.models.abstractModel.Product;

public class Drink extends Product {
    private String brand;
    private String size;
    private double price;

    //Getters and Setters

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.size, this.brand);
    }
}
