package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Carro extends Veiculo {
    private int portas;

    public Carro(String placa, int capacidade, boolean alugado, int ano, int portas, String tipo) {
        super(placa, capacidade, alugado, ano, tipo);
        this.portas = portas;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public void salvarNoBanco() {
        String sql = "INSERT INTO car_rents (tipo, placa, capacidade, alugado, ano, portas) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, getTipo());
            stmt.setString(2, getPlaca());
            stmt.setInt(3, getCapacidade());
            stmt.setBoolean(4, isAlugado());
            stmt.setInt(5, getAno());
            stmt.setInt(6, portas);

            stmt.executeUpdate();
            System.out.println("Carro salvo com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar carro: " + e.getMessage());
        }
    }

    public static void exibirCarros() {
        String query = "SELECT * FROM car_rents ORDER BY id";
        
        try (Connection conn = Database.getConnection();  // Conexão centralizada
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            System.out.println("\n+----+---------+----------------------+-------------+---------+------+--------+");
            System.out.println("| ID | Placa   | Tipo                 | Capacidade  | Alugado | Ano  | Portas |");
            System.out.println("+----+---------+----------------------+-------------+---------+------+--------+");
            
            while (rs.next()) {
                System.out.printf(
                    "| %-2d | %-7s | %-20s | %-11d | %-7s | %-4d | %-6d |%n",
                    rs.getInt("id"),
                    rs.getString("placa"),
                    rs.getString("tipo"),
                    rs.getInt("capacidade"),
                    rs.getBoolean("alugado") ? "Sim" : "Não",
                    rs.getInt("ano"),
                    rs.getInt("portas")
                );
            }
            System.out.println("+----+---------+----------------------+-------------+---------+------+--------+\n");
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar carros: " + e.getMessage());
        }
    }

    public static void excluirCarro(int idCarro) {
        String query = "DELETE FROM car_rents WHERE id = ?";
        
        try (Connection conn = Database.getConnection();  // Conexão centralizada
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idCarro);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Carro ID " + idCarro + " excluído com sucesso!");
            } else {
                System.out.println("Nenhum carro encontrado com o ID " + idCarro);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir carro: " + e.getMessage());
        }
    }

    @Override
    public void exibirInformacoes() {
        System.out.printf(
            "Carro [Placa: %s, Tipo: %s, Capacidade: %d, Ano: %d, Portas: %d, Alugado: %s]%n",
            getPlaca(), getTipo(), getCapacidade(), getAno(), portas, 
            isAlugado() ? "Sim" : "Não"
        );
    }
}