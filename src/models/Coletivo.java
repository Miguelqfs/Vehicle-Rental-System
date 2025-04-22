package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Database;

public class Coletivo extends Veiculo {
    private int portas;
    private boolean banheiro;

    public Coletivo(String placa, int capacidade, boolean alugado, int ano, int portas, boolean banheiro, String tipo) {
        super(placa, capacidade, alugado, ano, tipo);
        this.portas = portas;
        this.banheiro = banheiro;
    }

    // Removido método getConnection() redundante

    public void adicionarNoBanco() {
        String query = "INSERT INTO collective_rents (placa, tipo, capacidade, alugado, ano, portas, banheiro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();  // Conexão centralizada
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, getPlaca());
            stmt.setString(2, getTipo());
            stmt.setInt(3, getCapacidade());
            stmt.setBoolean(4, isAlugado());
            stmt.setInt(5, getAno());
            stmt.setInt(6, portas);
            stmt.setBoolean(7, banheiro);
            
            stmt.executeUpdate();
            System.out.println("Coletivo salvo com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar coletivo: " + e.getMessage());
        }
    }

    public static void excluirColetivo(int idColetivo) {
        String query = "DELETE FROM collective_rents WHERE id = ?";
        
        try (Connection conn = Database.getConnection();  // Conexão centralizada
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idColetivo);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Coletivo ID " + idColetivo + " excluído com sucesso!");
            } else {
                System.out.println("Nenhum coletivo encontrado com o ID " + idColetivo);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir coletivo: " + e.getMessage());
        }
    }

    public static void exibirColetivos() {
        String query = "SELECT * FROM collective_rents ORDER BY id";
        
        try (Connection conn = Database.getConnection();  // Conexão centralizada
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            System.out.println("\n+----+---------+----------------------+-------------+---------+------+--------+---------+");
            System.out.println("| ID | Placa   | Tipo                 | Capacidade  | Alugado | Ano  | Portas | Banheiro |");
            System.out.println("+----+---------+----------------------+-------------+---------+------+--------+---------+");
            
            while (rs.next()) {
                System.out.printf(
                    "| %-2d | %-7s | %-20s | %-11d | %-7s | %-4d | %-6d | %-7s |%n",
                    rs.getInt("id"),
                    rs.getString("placa"),
                    rs.getString("tipo"),
                    rs.getInt("capacidade"),
                    rs.getBoolean("alugado") ? "Sim" : "Não",
                    rs.getInt("ano"),
                    rs.getInt("portas"),
                    rs.getBoolean("banheiro") ? "Sim" : "Não"
                );
            }
            System.out.println("+----+---------+----------------------+-------------+---------+------+--------+---------+\n");
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar coletivos: " + e.getMessage());
        }
    }

    @Override
    public void exibirInformacoes() {
        System.out.printf(
            "Coletivo [Placa: %s, Tipo: %s, Capacidade: %d, Ano: %d, Portas: %d, Banheiro: %s, Alugado: %s]%n",
            getPlaca(), getTipo(), getCapacidade(), getAno(), 
            portas, 
            banheiro ? "Sim" : "Não", 
            isAlugado() ? "Sim" : "Não"
        );
    }
}