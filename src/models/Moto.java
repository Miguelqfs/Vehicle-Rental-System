package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Moto extends Veiculo {
    private boolean bau;

    public Moto(String placa, int capacidade, boolean alugado, int ano, boolean bau, String tipo) {
        super(placa, capacidade, alugado, ano, tipo);
        this.bau = bau;
    }

    public boolean hasBau() {
        return bau;
    }

    public void setBau(boolean bau) {
        this.bau = bau;
    }

    public void salvarNoBanco() {
        String sql = "INSERT INTO moto_rents (tipo, placa, capacidade, alugado, ano, bau) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, getTipo());
            stmt.setString(2, getPlaca());
            stmt.setInt(3, getCapacidade());
            stmt.setBoolean(4, isAlugado());
            stmt.setInt(5, getAno());
            stmt.setBoolean(6, bau);

            stmt.executeUpdate();
            System.out.println("Moto salva com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar moto: " + e.getMessage());
        }
    }

    public static void excluirMoto(int idMoto) {
        String query = "DELETE FROM moto_rents WHERE id = ?";
        
        try (Connection conn = Database.getConnection();  // Conexão centralizada
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idMoto);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Moto ID " + idMoto + " excluída com sucesso!");
            } else {
                System.out.println("Nenhuma moto encontrada com o ID " + idMoto);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir moto: " + e.getMessage());
        }
    }

    public static void exibirMotos() {
        String query = "SELECT * FROM moto_rents ORDER BY id";
        
        try (Connection conn = Database.getConnection();  // Conexão centralizada
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            System.out.println("\n+----+---------+----------------------+-------------+---------+------+------+");
            System.out.println("| ID | Placa   | Tipo                 | Capacidade  | Alugado | Ano  | Baú |");
            System.out.println("+----+---------+----------------------+-------------+---------+------+------+");
            
            while (rs.next()) {
                System.out.printf(
                    "| %-2d | %-7s | %-20s | %-11d | %-7s | %-4d | %-4s |%n",
                    rs.getInt("id"),
                    rs.getString("placa"),
                    rs.getString("tipo"),
                    rs.getInt("capacidade"),
                    rs.getBoolean("alugado") ? "Sim" : "Não",
                    rs.getInt("ano"),
                    rs.getBoolean("bau") ? "Sim" : "Não"
                );
            }
            System.out.println("+----+---------+----------------------+-------------+---------+------+------+\n");
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar motos: " + e.getMessage());
        }
    }

    @Override
    public void exibirInformacoes() {
        System.out.printf(
            "Moto [Placa: %s, Tipo: %s, Capacidade: %d, Ano: %d, Baú: %s, Alugado: %s]%n",
            getPlaca(), getTipo(), getCapacidade(), getAno(), 
            hasBau() ? "Sim" : "Não", 
            isAlugado() ? "Sim" : "Não"
        );
    }
}