import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import models.Carro;
import models.Coletivo;
import models.Database;
import models.Moto;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            
            do {
                Database.testarConexao();
                exibirMenu();
                
                try {
                    System.out.print("Escolha uma opção: ");
                    opcao = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    switch (opcao) {
                        case 1 -> adicionarVeiculo(scanner);
                        case 2 -> excluirVeiculo(scanner);
                        case 3 -> exibirStatusVeiculos(scanner);
                        case 4 -> System.out.println("Saindo...");
                        default -> System.out.println("Opção inválida!");
                    }
                    
                } catch (InputMismatchException e) {
                    System.err.println("Erro: Entrada inválida!");
                    scanner.nextLine(); 
                }
                
            } while (opcao != 4);
            
        } catch (Exception e) {
            System.err.println("Erro fatal: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=======================");
        System.out.println("  SISTEMA DE ALUGUEIS  ");
        System.out.println("=======================");
        System.out.println("[1] Adicionar veículo");
        System.out.println("[2] Excluir veículo");
        System.out.println("[3] Exibir status");
        System.out.println("[4] Sair");
    }

    private static void adicionarVeiculo(Scanner scanner) {
        int tipo = escolherTipoVeiculo(scanner, "adicionar");

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.print("Capacidade: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Está alugado? (true/false): ");
        boolean alugado = scanner.nextBoolean();
        scanner.nextLine();

        try {
            switch (tipo) {
                case 1 -> {
                    System.out.println("Tipos disponíveis: SUV, Sedan, Hatch");
                    System.out.print("Tipo: ");
                    String tipoCarro = scanner.nextLine();

                    System.out.print("Portas: ");
                    int portas = scanner.nextInt();
                    scanner.nextLine();

                    Carro carro = new Carro(placa, capacidade, alugado, ano, portas, tipoCarro);
                    carro.salvarNoBanco();
                }
                case 2 -> {
                    System.out.println("Tipos disponíveis: Street, Scooter");
                    System.out.print("Tipo: ");
                    String tipoMoto = scanner.nextLine();

                    System.out.print("Bau (true/false): ");
                    boolean bau = scanner.nextBoolean();
                    scanner.nextLine();

                    Moto moto = new Moto(placa, capacidade, alugado, ano, bau, tipoMoto);
                    moto.salvarNoBanco();
                }
                case 3 -> {
                    System.out.println("Tipos disponíveis: Van, Mini Van, Onibus");
                    System.out.print("Tipo: ");
                    String tipoColetivo = scanner.nextLine();

                    System.out.print("Portas: ");
                    int portas = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Banheiros (true/false): ");
                    boolean banheiros = scanner.nextBoolean();
                    scanner.nextLine();

                    Coletivo coletivo = new Coletivo(placa, capacidade, alugado, ano, portas, banheiros, tipoColetivo);
                    coletivo.salvarNoBanco();
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao adicionar: " + e.getMessage());
        }
    }

    private static void excluirVeiculo(Scanner scanner) {
        int tipo = escolherTipoVeiculo(scanner, "excluir");
        System.out.print("ID do veículo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (tipo) {
                case 1 -> excluirCarro(id);
                case 2 -> excluirMoto(id);
                case 3 -> excluirColetivo(id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao excluir: " + e.getMessage());
        }
    }

    private static void exibirStatusVeiculos(Scanner scanner) {
        int tipo = escolherTipoVeiculo(scanner, "exibir status");
        
        try {
            switch (tipo) {
                case 1 -> Carro.exibirCarros();
                case 2 -> Moto.exibirMotos();
                case 3 -> Coletivo.exibirColetivos();
            }
        } catch (Exception e) {
            System.err.println("Erro ao exibir: " + e.getMessage());
        }
    }

    private static int escolherTipoVeiculo(Scanner scanner, String acao) {
        System.out.println("\n=== Tipo para " + acao + " ===");
        System.out.println("[1] Carro");
        System.out.println("[2] Moto");
        System.out.println("[3] Coletivo");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        return tipo;
    }

    public static void excluirCarro(int id) {
        String sql = "DELETE FROM car_rents WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Carro excluído com sucesso!");
            } else {
                System.out.println("Carro não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir carro: " + e.getMessage());
        }
    }

    public static void excluirMoto(int id) {
        String sql = "DELETE FROM moto_rents WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Moto excluída com sucesso!");
            } else {
                System.out.println("Moto não encontrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir moto: " + e.getMessage());
        }
    }

    public static void excluirColetivo(int id) {
        String sql = "DELETE FROM coletivo_rents WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Coletivo excluído com sucesso!");
            } else {
                System.out.println("Coletivo não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir coletivo: " + e.getMessage());
        }
    }

    public static void exibirCarros() {
        String sql = "SELECT * FROM car_rents";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Carros Cadastrados ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Tipo: " + rs.getString("tipo") +
                                   ", Placa: " + rs.getString("placa") +
                                   ", Capacidade: " + rs.getInt("capacidade") +
                                   ", Alugado: " + rs.getBoolean("alugado") +
                                   ", Ano: " + rs.getInt("ano") +
                                   ", Portas: " + rs.getInt("portas"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao exibir carros: " + e.getMessage());
        }
    }

    public static void exibirMotos() {
        String sql = "SELECT * FROM moto_rents";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Motos Cadastradas ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Tipo: " + rs.getString("tipo") +
                                   ", Placa: " + rs.getString("placa") +
                                   ", Capacidade: " + rs.getInt("capacidade") +
                                   ", Alugado: " + rs.getBoolean("alugado") +
                                   ", Ano: " + rs.getInt("ano") +
                                   ", Bau: " + rs.getBoolean("bau"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao exibir motos: " + e.getMessage());
        }
    }

    public static void exibirColetivos() {
        String sql = "SELECT * FROM coletivo_rents";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Coletivos Cadastrados ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Tipo: " + rs.getString("tipo") +
                                   ", Placa: " + rs.getString("placa") +
                                   ", Capacidade: " + rs.getInt("capacidade") +
                                   ", Alugado: " + rs.getBoolean("alugado") +
                                   ", Ano: " + rs.getInt("ano") +
                                   ", Portas: " + rs.getInt("portas") +
                                   ", Banheiros: " + rs.getBoolean("banheiros"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao exibir coletivos: " + e.getMessage());
        }
    }
}
