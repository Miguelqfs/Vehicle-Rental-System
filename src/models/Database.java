package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/alugueis",
            "postgres",
            "postgres"
        );
    }

    public static void testarConexao() {
        try (Connection conn = getConnection()) {
            System.out.println("Conexão com o banco OK!");
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
        }
    }
}