package com.example.foodapp;

public class FoodFactory {

    public static FoodItem createFoodItem(String type, String name, double price) {
        switch (type) {
            case "soup":
                return new Soup(name, price);
            case "main":
                return new MainDish(name, price);
            case "drink":
                return new Drink(name, price);
            case "custom":
                return new CustomFoodItem(name, price);
            default:
                throw new IllegalArgumentException("Неизвестный тип блюда: " + type);
        }
    }
}

