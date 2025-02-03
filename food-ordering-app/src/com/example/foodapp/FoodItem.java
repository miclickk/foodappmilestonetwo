package com.example.foodapp;

public abstract class FoodItem {
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Soup extends FoodItem {
    public Soup(String name, double price) {
        super(name, price);
    }
}

class MainDish extends FoodItem {
    public MainDish(String name, double price) {
        super(name, price);
    }
}

class Drink extends FoodItem {
    public Drink(String name, double price) {
        super(name, price);
    }

}
class CustomFoodItem extends FoodItem {
    public CustomFoodItem(String name, double price) {
        super(name, price);
    }
}
