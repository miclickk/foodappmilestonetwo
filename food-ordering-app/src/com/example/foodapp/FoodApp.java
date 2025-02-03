package com.example.foodapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FoodApp extends Application {

    private Order order = new Order();
    private TextArea orderDetails = new TextArea();
    private Label totalPriceLabel = new Label("Общая стоимость: 0.00 KZT");
    private OrderService orderService = new OrderService();
    private PaymentService paymentService = new PaymentService();

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Food Ordering App");

        BorderPane root = new BorderPane();

        VBox categories = new VBox(10);
        categories.setPadding(new Insets(10));
        categories.getChildren().addAll(
                createCategoryButton("Первые блюда", new String[][] {
                        {"Борщ", "2300", "file:/Users/askarkulahmetov/Downloads/FOOD/borsch.jpg"},
                        {"Суп с лапшой", "1700", "file:/Users/askarkulahmetov/Downloads/FOOD/noodle_soup.jpeg"},
                        {"Окрошка", "2200", "file:/Users/askarkulahmetov/Downloads/FOOD/okroshka.jpg"},
                        {"Солянка", "2300", "file:/Users/askarkulahmetov/Downloads/FOOD/solyanka.jpg"}
                }),
                createCategoryButton("Вторые блюда", new String[][] {
                        {"Курица гриль", "4300", "file:/Users/askarkulahmetov/Downloads/FOOD/grilled_chicken.jpg"},
                        {"Стейк", "4000", "file:/Users/askarkulahmetov/Downloads/FOOD/steak.jpg"},
                        {"Рыба на пару", "3900", "file:/Users/askarkulahmetov/Downloads/FOOD/steamed_fish.jpg"},
                        {"Плов", "2900", "file:/Users/askarkulahmetov/Downloads/FOOD/plov.jpeg"},
                        {"Котлеты с пюре", "2800", "file:/Users/askarkulahmetov/Downloads/FOOD/cutlets.jpg"}
                }),
                createCategoryButton("Напитки", new String[][] {
                        {"Кола", "700", "file:/Users/askarkulahmetov/Downloads/FOOD/cola.jpg"},
                        {"Чай", "370", "file:/Users/askarkulahmetov/Downloads/FOOD/tea.jpg"},
                        {"Кофе", "600", "file:/Users/askarkulahmetov/Downloads/FOOD/coffee.jpg"},
                        {"Сок апельсиновый", "700", "file:/Users/askarkulahmetov/Downloads/FOOD/orange_juice.jpg"},
                        {"Минеральная вода", "350", "file:/Users/askarkulahmetov/Downloads/FOOD/mineral_water.jpg"}
                })
        );

        VBox orderBox = new VBox(10);
        orderBox.setPadding(new Insets(10));
        orderDetails.setEditable(false);
        orderBox.getChildren().addAll(new Label("Ваш заказ:"), orderDetails, totalPriceLabel);

        Button finishOrderButton = new Button("Завершить заказ");
        finishOrderButton.setOnAction(e -> requestUserDetails());
        orderBox.getChildren().add(finishOrderButton);

        root.setLeft(categories);
        root.setCenter(orderBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createCategoryButton(String category, String[][] items) {
        Button button = new Button(category);
        button.setOnAction(e -> showItems(category, items));
        return button;
    }

    private void showItems(String category, String[][] items) {
        Stage stage = new Stage();
        stage.setTitle(category);

        VBox itemsBox = new VBox(10);
        itemsBox.setPadding(new Insets(10));

        for (String[] item : items) {
            String name = item[0];
            double price = Double.parseDouble(item[1]);
            String imageFile = item[2];

            HBox itemBox = new HBox(10);
            ImageView imageView = new ImageView(new Image(imageFile));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            VBox detailsBox = new VBox(5);
            detailsBox.getChildren().addAll(
                    new Label(name),
                    new Label(df.format(price) + " KZT")
            );

            Button itemButton = new Button("Добавить");
            itemButton.setOnAction(e -> addItemToOrder(name, price));

            itemBox.getChildren().addAll(imageView, detailsBox, itemButton);
            itemsBox.getChildren().add(itemBox);
        }

        Scene scene = new Scene(itemsBox, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void addItemToOrder(String itemName, double price) {
        order.addItem(FoodFactory.createFoodItem("custom", itemName, price));
        updateOrderDetails();
    }

    private void updateOrderDetails() {
        StringBuilder details = new StringBuilder();
        order.getItems().forEach(item ->
                details.append(item.getDescription()).append(" - ").append(df.format(item.getPrice())).append(" KZT\n")
        );
        orderDetails.setText(details.toString());
        totalPriceLabel.setText("Общая стоимость: " + df.format(order.getTotalPrice()) + " KZT");
    }

    private void requestUserDetails() {
        Stage stage = new Stage();
        stage.setTitle("Информация о заказе");

        VBox detailsBox = new VBox(10);
        detailsBox.setPadding(new Insets(10));

        TextField nameField = new TextField();
        nameField.setPromptText("Введите ваше имя");

        TextField emailField = new TextField();
        emailField.setPromptText("Введите ваш email");

        TextField addressField = new TextField();
        addressField.setPromptText("Введите ваш адрес");

        ChoiceBox<String> paymentMethodBox = new ChoiceBox<>();
        paymentMethodBox.getItems().addAll("Наличные", "Карта");
        paymentMethodBox.setValue("Наличные");

        TextField cardNumberField = new TextField();
        cardNumberField.setPromptText("Введите номер карты (16 цифр)");
        cardNumberField.setVisible(false);

        PasswordField cvvField = new PasswordField();
        cvvField.setPromptText("Введите CVV-код (3 цифры)");
        cvvField.setVisible(false);

        paymentMethodBox.setOnAction(e -> {
            boolean isCard = paymentMethodBox.getValue().equals("Карта");
            cardNumberField.setVisible(isCard);
            cvvField.setVisible(isCard);
        });

        Button submitButton = new Button("Подтвердить заказ");
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String paymentMethod = paymentMethodBox.getValue();

            if (name.isEmpty() || email.isEmpty() || address.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Все поля должны быть заполнены!");
                alert.showAndWait();
                return;
            }

            if (paymentMethod.equals("Карта")) {
                String cardNumber = cardNumberField.getText();
                String cvv = cvvField.getText();

                if (!cardNumber.matches("\\d{16}")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Введите корректный номер карты (16 цифр)!");
                    alert.showAndWait();
                    return;
                }

                if (!cvv.matches("\\d{3}")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Введите корректный CVV-код (3 цифры)!");
                    alert.showAndWait();
                    return;
                }

                saveOrder(name, email, address, paymentMethod, cardNumber, cvv);
            } else {
                saveOrder(name, email, address, paymentMethod, null, null);
            }

            stage.close();
        });

        detailsBox.getChildren().addAll(
                new Label("Введите ваши данные:"),
                nameField,
                emailField,
                addressField,
                new Label("Выберите метод оплаты:"),
                paymentMethodBox,
                cardNumberField,
                cvvField,
                submitButton
        );

        Scene scene = new Scene(detailsBox, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void saveOrder(String name, String email, String address, String paymentMethod, String cardNumber, String cvv) {
        orderService.saveOrderToDatabase(order, name, email);
        paymentService.pay(order, paymentMethod, cardNumber, cvv, address);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Заказ успешно завершён!");
        alert.showAndWait();

        order = new Order();
        updateOrderDetails();
    }
}
