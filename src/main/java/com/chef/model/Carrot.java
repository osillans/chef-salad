package com.chef.model;

public class Carrot extends Vegetable {

    private final double sugarPercentage;

    public Carrot(double caloriesPer100g, double weight, double sugarPercentage) {
        super("Морква", caloriesPer100g, weight);
        this.sugarPercentage = sugarPercentage;
    }

    public double getSugarPercentage() {
        return sugarPercentage;
    }
}