package com.example.foodapp;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<FoodItem> items = new ArrayList<>();
    private double totalPrice;

    public void addItem(FoodItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}