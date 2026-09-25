package com.chef.model;

public class Onion extends Vegetable {

    private final boolean isSpicy;

    public Onion(double caloriesPer100g, double weight, boolean isSpicy) {
        super("Цибуля " + (isSpicy ? "гостра" : "солодка"), caloriesPer100g, weight);
        this.isSpicy = isSpicy;
    }

    public boolean isSpicy() {
        return isSpicy;
    }
}