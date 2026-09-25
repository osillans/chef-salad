package com.chef.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Salad {

    private final String name;
    private final List<Vegetable> ingredients = new ArrayList<>();

    public Salad(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addIngredient(Vegetable vegetable) {
        if (vegetable == null) {
            throw new IllegalArgumentException("Овоч не може бути null");
        }
        ingredients.add(vegetable);
    }

    public List<Vegetable> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }
}