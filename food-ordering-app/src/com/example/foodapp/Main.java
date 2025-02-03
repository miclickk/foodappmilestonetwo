package com.example.foodapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск приложения Food Ordering...");

        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();

        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        System.out.println("Выберите категорию меню. Введите номер категории для выбора:");

        boolean ordering = true;

        while (ordering) {
            System.out.println("Категории:");
            System.out.println("1. Первые блюда");
            System.out.println("2. Вторые блюда");
            System.out.println("3. Напитки");
            System.out.println("0. Завершить выбор");
            System.out.print("Ваш выбор: ");

            int categoryChoice = scanner.nextInt();

            switch (categoryChoice) {
                case 1:
                    System.out.println("Первые блюда:");
                    System.out.println("1. Борщ - 2300 KZT");
                    System.out.println("2. Суп с лапшой - 1700 KZT");
                    System.out.println("3. Окрошка - 2200 KZT");
                    System.out.println("4. Солянка - 2300 KZT");
                    System.out.println("0. Вернуться к категориям");
                    System.out.print("Ваш выбор: ");

                    int firstDishChoice = scanner.nextInt();
                    if (firstDishChoice == 1) {
                        order.addItem(FoodFactory.createFoodItem("soup", "Борщ", 2300));
                        System.out.println("Борщ добавлен в заказ.");
                    } else if (firstDishChoice == 2) {
                        order.addItem(FoodFactory.createFoodItem("soup", "Суп с лапшой", 1700));
                        System.out.println("Суп с лапшой добавлен в заказ.");
                    } else if (firstDishChoice == 3) {
                        order.addItem(FoodFactory.createFoodItem("soup", "Окрошка", 2200));
                        System.out.println("Окрошка добавлена в заказ.");
                    } else if (firstDishChoice == 4) {
                        order.addItem(FoodFactory.createFoodItem("soup", "Солянка", 2300));
                        System.out.println("Солянка добавлена в заказ.");
                    } else if (firstDishChoice == 0) {
                        break;
                    } else {
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                    }
                    break;

                case 2:
                    System.out.println("Вторые блюда:");
                    System.out.println("1. Курица гриль - 4300 KZT");
                    System.out.println("2. Стейк - 4000 KZT");
                    System.out.println("3. Рыба на пару - 3900 KZT");
                    System.out.println("4. Плов - 2900 KZT");
                    System.out.println("5. Котлеты с пюре - 2800 KZT");
                    System.out.println("0. Вернуться к категориям");
                    System.out.print("Ваш выбор: ");

                    int secondDishChoice = scanner.nextInt();
                    if (secondDishChoice == 1) {
                        order.addItem(FoodFactory.createFoodItem("main", "Курица гриль", 4300));
                        System.out.println("Курица гриль добавлена в заказ.");
                    } else if (secondDishChoice == 2) {
                        order.addItem(FoodFactory.createFoodItem("main", "Стейк", 4000));
                        System.out.println("Стейк добавлен в заказ.");
                    } else if (secondDishChoice == 3) {
                        order.addItem(FoodFactory.createFoodItem("main", "Рыба на пару", 3900));
                        System.out.println("Рыба на пару добавлена в заказ.");
                    } else if (secondDishChoice == 4) {
                        order.addItem(FoodFactory.createFoodItem("main", "Плов", 2900));
                        System.out.println("Плов добавлен в заказ.");
                    } else if (secondDishChoice == 5) {
                        order.addItem(FoodFactory.createFoodItem("main", "Котлеты с пюре", 2800));
                        System.out.println("Котлеты с пюре добавлены в заказ.");
                    } else if (secondDishChoice == 0) {
                        break;
                    } else {
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                    }
                    break;

                case 3:
                    System.out.println("Напитки:");
                    System.out.println("1. Кола - 700 KZT");
                    System.out.println("2. Чай - 370 KZT");
                    System.out.println("3. Кофе - 600 KZT");
                    System.out.println("4. Сок апельсиновый - 700 KZT");
                    System.out.println("5. Минеральная вода - 350 KZT");
                    System.out.println("0. Вернуться к категориям");
                    System.out.print("Ваш выбор: ");

                    int drinkChoice = scanner.nextInt();
                    if (drinkChoice == 1) {
                        order.addItem(FoodFactory.createFoodItem("drink", "Кола", 700));
                        System.out.println("Кола добавлена в заказ.");
                    } else if (drinkChoice == 2) {
                        order.addItem(FoodFactory.createFoodItem("drink", "Чай", 370));
                        System.out.println("Чай добавлен в заказ.");
                    } else if (drinkChoice == 3) {
                        order.addItem(FoodFactory.createFoodItem("drink", "Кофе", 600));
                        System.out.println("Кофе добавлен в заказ.");
                    } else if (drinkChoice == 4) {
                        order.addItem(FoodFactory.createFoodItem("drink", "Сок апельсиновый", 700));
                        System.out.println("Сок апельсиновый добавлен в заказ.");
                    } else if (drinkChoice == 5) {
                        order.addItem(FoodFactory.createFoodItem("drink", "Минеральная вода", 350));
                        System.out.println("Минеральная вода добавлена в заказ.");
                    } else if (drinkChoice == 0) {
                        break;
                    } else {
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                    }
                    break;

                case 0:
                    ordering = false;
                    System.out.println("Заказ завершён.");
                    break;

                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }

        System.out.println("Сформирован заказ. Список блюд:");
        order.getItems().forEach(item ->
                System.out.println(item.getDescription() + " - " + item.getPrice() + " KZT")
        );

        System.out.println("Общая стоимость заказа: " + order.getTotalPrice() + " KZT");

        System.out.print("Введите ваше имя: ");
        scanner.nextLine();
        String userName = scanner.nextLine();

        System.out.print("Введите ваш email: ");
        String userEmail = scanner.nextLine();

        orderService.saveOrderToDatabase(order, userName, userEmail);

        System.out.print("Введите метод оплаты (cash/card): ");
        String paymentMethod = scanner.nextLine();

        if (paymentMethod.equalsIgnoreCase("card")) {
            System.out.print("Введите номер карты (16 цифр): ");
            String cardNumber = scanner.nextLine();

            System.out.print("Введите CVV-код (3 цифры): ");
            String cvv = scanner.nextLine();

            System.out.print("Введите адрес проживания: ");
            String address = scanner.nextLine();

            paymentService.pay(order, paymentMethod, cardNumber, cvv, address);
        } else if (paymentMethod.equalsIgnoreCase("cash")) {
            System.out.print("Введите адрес проживания: ");
            String address = scanner.nextLine();

            paymentService.pay(order, paymentMethod, null, null, address);
        } else {
            System.out.println("Некорректный метод оплаты. Заказ не может быть обработан.");
        }

        System.out.println("Приложение завершило работу.");
    }
}
