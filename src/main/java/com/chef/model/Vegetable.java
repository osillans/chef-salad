package com.chef.model;

import java.util.Objects;

/**
 * Базовий абстрактний клас для всіх овочів у салаті.
 * Демонструє інкапсуляцію та абстракцію.
 */
public abstract class Vegetable {

    private final String name;
    private final double caloriesPer100g; // калорійність на 100 грамів
    private final double weight;          // вага в грамах

    public Vegetable(String name, double caloriesPer100g, double weight) {
        if (caloriesPer100g < 0 || weight <= 0) {
            throw new IllegalArgumentException("Калорійність та вага не можуть бути від'ємними");
        }
        this.name = name;
        this.caloriesPer100g = caloriesPer100g;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getCaloriesPer100g() {
        return caloriesPer100g;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Поліморфний розрахунок загальної калорійності конкретної порції овоча.
     */
    public double getTotalCalories() {
        return (caloriesPer100g * weight) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vegetable vegetable = (Vegetable) o;
        return Double.compare(vegetable.caloriesPer100g, caloriesPer100g) == 0 &&
               Double.compare(vegetable.weight, weight) == 0 &&
               Objects.equals(name, vegetable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, caloriesPer100g, weight);
    }

    @Override
    public String toString() {
        return String.format("%s [вага: %.1f г, калорій на 100г: %.1f ккал, всього: %.1f ккал]",
                name, weight, caloriesPer100g, getTotalCalories());
    }
}