package com.chef.service;

import com.chef.model.Salad;
import com.chef.model.Vegetable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SaladService {

    /**
     * Рахує загальну калорійність усього салату.
     */
    public double calculateTotalCalories(Salad salad) {
        if (salad == null) {
            throw new IllegalArgumentException("Салат не може бути null");
        }
        return salad.getIngredients().stream()
                .mapToDouble(Vegetable::getTotalCalories)
                .sum();
    }

    /**
     * Сортує овочі в салаті за обраним параметром (наприклад, за вагою за спаданням).
     */
    public List<Vegetable> sortByWeightDescending(Salad salad) {
        if (salad == null) {
            throw new IllegalArgumentException("Салат не може бути null");
        }
        List<Vegetable> sorted = new ArrayList<>(salad.getIngredients());
        sorted.sort(Comparator.comparingDouble(Vegetable::getWeight).reversed());
        return sorted;
    }

    /**
     * Сортує овочі за калорійністю на 100 г (за зростанням).
     */
    public List<Vegetable> sortByCaloriesAscending(Salad salad) {
        if (salad == null) {
            throw new IllegalArgumentException("Салат не може бути null");
        }
        List<Vegetable> sorted = new ArrayList<>(salad.getIngredients());
        sorted.sort(Comparator.comparingDouble(Vegetable::getCaloriesPer100g));
        return sorted;
    }

    /**
     * Знаходить овочі в салаті, калорійність яких (на 100 г) потрапляє в заданий діапазон.
     */
    public List<Vegetable> findByCaloriesRange(Salad salad, double minCalories, double maxCalories) {
        if (salad == null) {
            throw new IllegalArgumentException("Салат не може бути null");
        }
        if (minCalories > maxCalories) {
            throw new IllegalArgumentException("Мінімальна межа не може перевищувати максимальну");
        }

        return salad.getIngredients().stream()
                .filter(v -> v.getCaloriesPer100g() >= minCalories && v.getCaloriesPer100g() <= maxCalories)
                .collect(Collectors.toList());
    }
}