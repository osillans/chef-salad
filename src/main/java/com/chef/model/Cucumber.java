package com.chef.model;

public class Cucumber extends Vegetable {

    private final double lengthCm;

    public Cucumber(double caloriesPer100g, double weight, double lengthCm) {
        super("Огірок", caloriesPer100g, weight);
        this.lengthCm = lengthCm;
    }

    public double getLengthCm() {
        return lengthCm;
    }
}