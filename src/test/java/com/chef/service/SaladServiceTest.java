package com.chef.service;

import com.chef.model.Carrot;
import com.chef.model.Cucumber;
import com.chef.model.Salad;
import com.chef.model.Tomato;
import com.chef.model.Vegetable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaladServiceTest {

    @InjectMocks
    private SaladService saladService;

    @Mock
    private Salad mockSalad;

    private Salad realSalad;

    @BeforeEach
    void setUp() {
        realSalad = new Salad("Тестовий салат");
        realSalad.addIngredient(new Tomato(20.0, 100.0, "Чері")); // 20.0 ккал
        realSalad.addIngredient(new Cucumber(15.0, 200.0, 10.0));   // 30.0 ккал
        realSalad.addIngredient(new Carrot(40.0, 50.0, 5.0));       // 20.0 ккал
    }

    @Test
    @DisplayName("Розрахунок загальної калорійності салату")
    void testCalculateTotalCalories() {
        double total = saladService.calculateTotalCalories(realSalad);
        assertEquals(70.0, total, 0.001);
    }

    @Test
    @DisplayName("Сортування овочів за калорійністю на 100г")
    void testSortByCaloriesAscending() {
        List<Vegetable> sorted = saladService.sortByCaloriesAscending(realSalad);

        assertEquals(3, sorted.size());
        assertEquals("Огірок", sorted.get(0).getName());
        assertEquals(15.0, sorted.get(0).getCaloriesPer100g());
        assertEquals(40.0, sorted.get(2).getCaloriesPer100g());
    }

    @Test
    @DisplayName("Сортування овочів за вагою за спаданням")
    void testSortByWeightDescending() {
        List<Vegetable> sorted = saladService.sortByWeightDescending(realSalad);

        assertEquals(200.0, sorted.get(0).getWeight());
        assertEquals(100.0, sorted.get(1).getWeight());
        assertEquals(50.0, sorted.get(2).getWeight());
    }

    @Test
    @DisplayName("Пошук овочів у діапазоні калорійності")
    void testFindByCaloriesRange() {
        List<Vegetable> found = saladService.findByCaloriesRange(realSalad, 18.0, 45.0);

        assertEquals(2, found.size());
        assertTrue(found.stream().anyMatch(v -> v.getCaloriesPer100g() == 20.0));
        assertTrue(found.stream().anyMatch(v -> v.getCaloriesPer100g() == 40.0));
    }

    @Test
    @DisplayName("Перевірка роботи через Mockito mock-об'єкт")
    void testMockitoInteraction() {
        when(mockSalad.getIngredients()).thenReturn(List.of(
                new Tomato(10.0, 100.0, "Чері")
        ));

        double calories = saladService.calculateTotalCalories(mockSalad);

        assertEquals(10.0, calories, 0.001);
        verify(mockSalad).getIngredients();
    }

    @Test
    @DisplayName("Перевірка викиду винятку при некоректному діапазоні")
    void testInvalidRangeThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                saladService.findByCaloriesRange(realSalad, 50.0, 10.0));
    }
}