/*package com.example.foodapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInitializer {
    public static void initializeDatabase(DatabaseConnection dbConnection) {
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String createFoodTable = """
                CREATE TABLE IF NOT EXISTS food_items (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    price DECIMAL(10,2) NOT NULL
                );
                """;
            stmt.executeUpdate(createFoodTable);

            String createOrdersTable = """
                CREATE TABLE IF NOT EXISTS orders (
                    order_id SERIAL PRIMARY KEY,
                    items VARCHAR(255),
                    total_price DECIMAL(10,2)
                );
                """;
            stmt.executeUpdate(createOrdersTable);

            System.out.println("Таблицы инициализированы успешно.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/