package com.chef;

import com.chef.model.Carrot;
import com.chef.model.Cucumber;
import com.chef.model.Onion;
import com.chef.model.Salad;
import com.chef.model.Tomato;
import com.chef.model.Vegetable;
import com.chef.service.SaladService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SaladService saladService = new SaladService();

        Salad salad = new Salad("Весняний вітамінний");
        salad.addIngredient(new Tomato(18.0, 150.0, "Чері"));
        salad.addIngredient(new Cucumber(15.0, 200.0, 14.5));
        salad.addIngredient(new Carrot(41.0, 80.0, 4.7));
        salad.addIngredient(new Onion(40.0, 50.0, false));

        System.out.println("ІНГРЕДІЄНТИ САЛАТУ '" + salad.getName().toUpperCase());
        salad.getIngredients().forEach(System.out::println);

        // Калорійність
        double totalCalories = saladService.calculateTotalCalories(salad);
        System.out.printf("%nЗагальна калорійність салату: %.2f ккал%n", totalCalories);

        // Сортування за калорійністю на 100 г
        System.out.println("\nСОРТУВАННЯ ОВОЧІВ ЗА КАЛОРІЙНІСТЮ (НА 100 Г)");
        List<Vegetable> sorted = saladService.sortByCaloriesAscending(salad);
        sorted.forEach(System.out::println);

        // Пошук овочів від 16 до 40 ккал/100г
        double min = 16.0;
        double max = 40.0;
        System.out.printf("%n ОВОЧІ В ДІАПАЗОНІ КАЛОРІЙНОСТІ [%.1f - %.1f ккал/100г] %n", min, max);
        List<Vegetable> found = saladService.findByCaloriesRange(salad, min, max);
        found.forEach(System.out::println);
    }
}