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

        String placa;
        do {
            System.out.print("Placa (formato LLLNNNN): ");
            placa = scanner.nextLine().toUpperCase().trim();
            if (!placa.matches("[A-Z]{3}\\d{4}")) {
                System.out.println("Formato inválido! Tente novamente.");
            }
        } while (!placa.matches("[A-Z]{3}\\d{4}"));

        int capacidade = 0;
        do {
            System.out.print("Capacidade: ");
            if (scanner.hasNextInt()) {
                capacidade = scanner.nextInt();
                scanner.nextLine();
                if (capacidade <= 0) {
                    System.out.println("Capacidade deve ser um número positivo!");
                }
            } else {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine(); // Limpa o buffer
            }
        } while (capacidade <= 0);

        int ano = 0;
        do {
            System.out.print("Ano: ");
            if (scanner.hasNextInt()) {
                ano = scanner.nextInt();
                scanner.nextLine();
                if (ano <= 0) {
                    System.out.println("Ano deve ser um número positivo!");
                }
            } else {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine(); // Limpa o buffer
            }
        } while (ano <= 0);

        boolean alugado = false;
        do {
            System.out.print("Está alugado? (true/false): ");
            if (scanner.hasNextBoolean()) {
                alugado = scanner.nextBoolean();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Entrada inválida! Digite true ou false.");
                scanner.nextLine(); // Limpa o buffer
            }
        } while (true);

        try {
            switch (tipo) {
                case 1 -> {
                    System.out.println("Tipos disponíveis: SUV, Sedan, Hatch");
                    String tipoCarro;
                    do {
                        System.out.print("Tipo: ");
                        tipoCarro = sanitizeString(scanner.nextLine());
                        if (!tipoCarro.matches("SUV|Sedan|Hatch")) {
                            System.out.println("Tipo inválido! Escolha entre SUV, Sedan ou Hatch.");
                        }
                    } while (!tipoCarro.matches("SUV|Sedan|Hatch"));

                    int portas = 0;
                    do {
                        System.out.print("Portas (número positivo): ");
                        if (scanner.hasNextInt()) {
                            portas = scanner.nextInt();
                            scanner.nextLine();
                            if (portas <= 0) {
                                System.out.println("Número de portas deve ser positivo!");
                            }
                        } else {
                            System.out.println("Entrada inválida! Digite um número.");
                            scanner.nextLine(); // Limpa o buffer
                        }
                    } while (portas <= 0);

                    Carro carro = new Carro(placa, capacidade, alugado, ano, portas, tipoCarro);
                    carro.salvarNoBanco();
                }
                case 2 -> {
                    System.out.println("Tipos disponíveis: Street, Scooter");
                    String tipoMoto;
                    do {
                        System.out.print("Tipo: ");
                        tipoMoto = sanitizeString(scanner.nextLine());
                        if (!tipoMoto.matches("Street|Scooter")) {
                            System.out.println("Tipo inválido! Escolha entre Street ou Scooter.");
                        }
                    } while (!tipoMoto.matches("Street|Scooter"));

                    boolean bau = false;
                    do {
                        System.out.print("Baú presente? (true/false): ");
                        if (scanner.hasNextBoolean()) {
                            bau = scanner.nextBoolean();
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Entrada inválida! Digite true ou false.");
                            scanner.nextLine(); // Limpa o buffer
                        }
                    } while (true);

                    Moto moto = new Moto(placa, capacidade, alugado, ano, bau, tipoMoto);
                    moto.salvarNoBanco();
                }
                case 3 -> {
                    System.out.println("Tipos disponíveis: Van, Mini Van, Onibus");
                    String tipoColetivo;
                    do {
                        System.out.print("Tipo: ");
                        tipoColetivo = sanitizeString(scanner.nextLine());
                        if (!tipoColetivo.matches("Van|Mini Van|Onibus")) {
                            System.out.println("Tipo inválido! Escolha entre Van, Mini Van ou Onibus.");
                        }
                    } while (!tipoColetivo.matches("Van|Mini Van|Onibus"));

                    int portas = 0;
                    do {
                        System.out.print("Portas (número positivo): ");
                        if (scanner.hasNextInt()) {
                            portas = scanner.nextInt();
                            scanner.nextLine();
                            if (portas <= 0) {
                                System.out.println("Número de portas deve ser positivo!");
                            }
                        } else {
                            System.out.println("Entrada inválida! Digite um número.");
                            scanner.nextLine(); // Limpa o buffer
                        }
                    } while (portas <= 0);

                    boolean banheiros = false;
                    do {
                        System.out.print("Banheiros presentes? (true/false): ");
                        if (scanner.hasNextBoolean()) {
                            banheiros = scanner.nextBoolean();
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Entrada inválida! Digite true ou false.");
                            scanner.nextLine(); // Limpa o buffer
                        }
                    } while (true);

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
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido! Tente novamente.");
            return escolherTipoVeiculo(scanner, acao);
        }
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
                try {
                    System.out.println("ID: " + rs.getInt("id") +
                                       ", Tipo: " + rs.getString("tipo") +
                                       ", Placa: " + rs.getString("placa") +
                                       ", Capacidade: " + rs.getInt("capacidade") +
                                       ", Alugado: " + rs.getBoolean("alugado") +
                                       ", Ano: " + rs.getInt("ano") +
                                       ", Portas: " + rs.getInt("portas") +
                                       ", Banheiro: " + rs.getBoolean("banheiro"));
                } catch (SQLException e) {
                    System.err.println("Erro ao processar ResultSet: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao exibir coletivos: " + e.getMessage());
        }
    }

    private static String sanitizeString(String input) {
        // Remove caracteres especiais e espaços extras
        return input.replaceAll("[^a-zA-Z0-9 ]", "").trim();
    }
}
