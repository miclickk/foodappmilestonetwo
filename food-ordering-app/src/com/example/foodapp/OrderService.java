package com.example.foodapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderService {

    public void saveOrderToDatabase(Order order, String userName, String userEmail) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            int userId = saveUser(connection, userName, userEmail);
            int orderId = saveOrder(connection, userId, order.getTotalPrice());
            saveOrderItems(connection, orderId, order);
            System.out.println("Заказ сохранён в базе данных.");
        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении заказа в базу данных: " + e.getMessage());
        }
    }

    private int saveUser(Connection connection, String name, String email) throws SQLException {
        String userQuery = "INSERT INTO users (name, email) VALUES (?, ?) ON CONFLICT (email) DO NOTHING RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(userQuery)) {
            statement.setString(1, name);
            statement.setString(2, email);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                String findUserQuery = "SELECT id FROM users WHERE email = ?";
                try (PreparedStatement findStatement = connection.prepareStatement(findUserQuery)) {
                    findStatement.setString(1, email);
                    var findResult = findStatement.executeQuery();
                    if (findResult.next()) {
                        return findResult.getInt(1);
                    }
                }
            }
        }
        throw new SQLException("Не удалось сохранить или найти пользователя.");
    }

    private int saveOrder(Connection connection, int userId, double totalPrice) throws SQLException {
        String orderQuery = "INSERT INTO orders (user_id, total_price) VALUES (?, ?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(orderQuery)) {
            statement.setInt(1, userId);
            statement.setDouble(2, totalPrice);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        throw new SQLException("Не удалось сохранить заказ.");
    }

    private void saveOrderItems(Connection connection, int orderId, Order order) throws SQLException {
        String itemQuery = "INSERT INTO order_items (order_id, name, price) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(itemQuery)) {
            for (FoodItem item : order.getItems()) {
                statement.setInt(1, orderId);
                statement.setString(2, item.getDescription());
                statement.setDouble(3, item.getPrice());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }
}