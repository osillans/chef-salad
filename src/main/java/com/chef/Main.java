package com.chef;

import com.chef.model.Carrot;
import com.chef.model.Cucumber;
import com.chef.model.Onion;
import com.chef.model.Salad;
import com.chef.model.Tomato;
import com.chef.model.Vegetable;
import com.chef.service.SaladService;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private static final SaladService saladService = new SaladService();

    // Базові калорійності на 100 г
    private static final double TOMATO_CALORIES = 18.0;
    private static final String TOMATO_VARIETY = "Чері";

    private static final double CUCUMBER_CALORIES = 15.0;
    private static final double CUCUMBER_LENGTH = 15.0;

    private static final double CARROT_CALORIES = 41.0;
    private static final double CARROT_SUGAR = 4.7;

    private static final double ONION_CALORIES = 40.0;
    private static final boolean ONION_IS_SPICY = false;

    public static void main(String[] args) {
        System.out.println("КУХНЯ ШЕФ-КУХАРЯ");
        System.out.print("Введiть назву салату: ");
        String saladName = scanner.nextLine().trim();
        if (saladName.isEmpty()) {
            saladName = "Фiрмовий салат";
        }
        Salad salad = new Salad(saladName);

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readIntInput("Оберiть дiю (0-6): ");

            switch (choice) {
                case 1 -> addVegetableByWeight(salad);
                case 2 -> showIngredients(salad);
                case 3 -> showTotalCalories(salad);
                case 4 -> sortByCalories(salad);
                case 5 -> sortByWeight(salad);
                case 6 -> searchByCaloriesRange(salad);
                case 0 -> {
                    System.out.println("\nПриготування завершено. Смачного!");
                    running = false;
                }
                default -> System.out.println("Невiрний вибiр. Оберiть пункт вiд 0 до 6.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--------------- МЕНЮ ------------------");
        System.out.println("1. Додати овоч до салату (вказати вагу)");
        System.out.println("2. Показати iнгредiєнти салату");
        System.out.println("3. Розрахувати загальну калорiйнiсть");
        System.out.println("4. Сортувати iнгредiєнти за калорiйнiстю");
        System.out.println("5. Сортувати iнгредiєнти за вагою");
        System.out.println("6. Пошук овочiв у дiапазонi калорiйностi");
        System.out.println("0. Вихiд");
        System.out.println("-----------------------------------------");
    }

    private static void addVegetableByWeight(Salad salad) {
        System.out.println("\nОберiть овоч:");
        System.out.println("1. Помiдор (18 ккал/100г)");
        System.out.println("2. Огiрок (15 ккал/100г)");
        System.out.println("3. Морква (41 ккал/100г)");
        System.out.println("4. Цибуля (40 ккал/100г)");

        int vegChoice = readIntInput("Ваш вибiр (1-4): ");
        if (vegChoice < 1 || vegChoice > 4) {
            System.out.println("Невiдомий вибiр.");
            return;
        }

        double weight = readDoubleInput("Введiть вагу (грами): ");

        switch (vegChoice) {
            case 1 -> salad.addIngredient(new Tomato(TOMATO_CALORIES, weight, TOMATO_VARIETY));
            case 2 -> salad.addIngredient(new Cucumber(CUCUMBER_CALORIES, weight, CUCUMBER_LENGTH));
            case 3 -> salad.addIngredient(new Carrot(CARROT_CALORIES, weight, CARROT_SUGAR));
            case 4 -> salad.addIngredient(new Onion(ONION_CALORIES, weight, ONION_IS_SPICY));
        }

        System.out.printf("Додано %.1f г до салату.%n", weight);
    }

    private static void showIngredients(Salad salad) {
        System.out.println("\niНГРЕДiЄНТИ САЛАТУ:");
        if (salad.getIngredients().isEmpty()) {
            System.out.println("(Салат порожнiй, додайте iнгредiєнти)");
            return;
        }
        salad.getIngredients().forEach(System.out::println);
    }

    private static void showTotalCalories(Salad salad) {
        double totalCalories = saladService.calculateTotalCalories(salad);
        System.out.printf("%nЗагальна калорiйнiсть салату '%s': %.2f ккал%n",
                salad.getName(), totalCalories);
    }

    private static void sortByCalories(Salad salad) {
        System.out.println("\nСОРТУВАННЯ ЗА ЗРОСТАННЯМ КАЛОРIЙНОСТI (на 100 г)");
        List<Vegetable> sorted = saladService.sortByCaloriesAscending(salad);
        if (sorted.isEmpty()) {
            System.out.println("(Салат порожнiй)");
            return;
        }
        sorted.forEach(System.out::println);
    }

    private static void sortByWeight(Salad salad) {
        System.out.println("\nСОРТУВАННЯ ЗА СПАДАННЯМ ВАГИ");
        List<Vegetable> sorted = saladService.sortByWeightDescending(salad);
        if (sorted.isEmpty()) {
            System.out.println("(Салат порожнiй)");
            return;
        }
        sorted.forEach(System.out::println);
    }

    private static void searchByCaloriesRange(Salad salad) {
        System.out.println("\nПОШУК ОВОЧIВ ЗА ДIАПАЗОНОМ КАЛОРIЙНОСТI");
        double min = readDoubleInput("Введiть мiнiмальну калорiйнiсть (ккал/100г): ");
        double max = readDoubleInput("Введiть максимальну калорiйнiсть (ккал/100г): ");

        try {
            List<Vegetable> found = saladService.findByCaloriesRange(salad, min, max);
            System.out.printf("Знайдено овочiв у дiапазонi [%.1f - %.1f ккал]:%n", min, max);
            if (found.isEmpty()) {
                System.out.println("(Овочiв у цьому дiапазонi немає)");
            } else {
                found.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка дiапазону: " + e.getMessage());
        }
    }

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введiть цiле число.");
            }
        }
    }

    private static double readDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().replace(',', '.');
            try {
                double val = Double.parseDouble(input);
                if (val <= 0) {
                    System.out.println("Вага повинна бути бiльшою за 0.");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введiть числове значення.");
            }
        }
    }
}