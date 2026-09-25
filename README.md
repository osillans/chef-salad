# Лабораторна робота з ООП №1. Шеф-кухар (Chef Salad)

Консольний Java-застосунок

## Опис проєкту

Програма моделює процес приготування салату шеф-кухарем:
- Створено ієрархію овочів на основі абстрактного базового класу Vegetable.
- Реалізовано конкретні класи овочів (Tomato, Cucumber, Carrot, Onion) з відповідними параметрами.
- Реалізовано клас Salad для зберігання списку інгредієнтів.
- Клас SaladService реалізує бізнес-логіку:
  - підрахунок загальної калорійності салату;
  - сортування овочів за калорійністю або вагою;
  - пошук овочів у заданому діапазоні калорійності.

## Використані технології

- Java 21
- Apache Maven
- JUnit 5
- Mockito
- Git / GitHub

## Структура проєкту

```text
src/
├── main/java/com/chef/
│   ├── model/           # Доменні моделі (Vegetable, Tomato, Cucumber, Carrot, Onion, Salad)
│   ├── service/         # Логіка (SaladService)
│   └── Main.java        # Точка входу в програму
└── test/java/com/chef/
    └── service/         # Тести (SaladServiceTest)

## Збірка та запуск

### Запуск модульних тестів

mvn test

### Компіляція класів

javac -encoding UTF-8 -d target/classes src/main/java/com/chef/model/*.java src/main/java/com/chef/service/*.java src/main/java/com/chef/Main.java

### Запуск програми

java -cp target/classes com.chef.Maingit