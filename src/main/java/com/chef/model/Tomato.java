package com.chef.model;

public class Tomato extends Vegetable {

    private final String variety; // наприклад: "Чері", "Сливка"

    public Tomato(double caloriesPer100g, double weight, String variety) {
        super("Помідор (" + variety + ")", caloriesPer100g, weight);
        this.variety = variety;
    }

    public String getVariety() {
        return variety;
    }
}