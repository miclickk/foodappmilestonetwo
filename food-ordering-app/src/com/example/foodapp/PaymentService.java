package com.example.foodapp;

public class PaymentService {
    public void pay(Order order, String paymentMethod, String cardNumber, String cvv, String address) {
        if (paymentMethod.equalsIgnoreCase("card")) {
            if (validateCardDetails(cardNumber, cvv, address)) {
                System.out.println("Оплата заказа на сумму " + order.getTotalPrice() + " через карту успешно проведена.");
            } else {
                System.out.println("Ошибка: Некорректные данные карты или адреса.");
            }
        } else {
            System.out.println("Оплата заказа на сумму " + order.getTotalPrice() + " через " + paymentMethod);
        }
    }

    private boolean validateCardDetails(String cardNumber, String cvv, String address) {
        if (cardNumber == null || cardNumber.length() != 16 || !cardNumber.matches("\\d+")) {
            return false;
        }
        if (cvv == null || cvv.length() != 3 || !cvv.matches("\\d+")) {
            return false;
        }
        if (address == null || address.isEmpty()) {
            return false;
        }
        return true;
    }
}
