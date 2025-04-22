package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import src.models.Carro;
import src.models.Coletivo;
import src.models.Moto;

public class Main {
    private static final ArrayList<Carro> carros = new ArrayList<>();
    private static final ArrayList<Moto> motos = new ArrayList<>();
    private static final ArrayList<Coletivo> coletivos = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            do {
                exibirMenu();
                try {
                    System.out.print("Escolha uma opção: ");
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcao) {
                        case 1 -> adicionarVeiculo(scanner);
                        case 2 -> excluirVeiculo(scanner);
                        case 3 -> exibirStatus();
                        case 4 -> System.out.println("Saindo...");
                        default -> System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Entrada inválida. Por favor, insira um número.");
                    scanner.nextLine();
                }
            } while (opcao != 4);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=======================");
        System.out.println("  SISTEMA DE VEÍCULOS  ");
        System.out.println("=======================");
        System.out.println("\nMENU:");
        System.out.println("[1] ADICIONAR");
        System.out.println("[2] EXCLUIR");
        System.out.println("[3] STATUS");
        System.out.println("[4] SAIR");
    }

    // Método para obter a conexão com o banco de dados PostgreSQL
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/sistema_veiculos";
        String user = "seu_usuario";
        String password = "sua_senha";
        return DriverManager.getConnection(url, user, password);
    }

    private static void adicionarVeiculo(Scanner scanner) {
        System.out.println("\nEscolha o tipo de veículo para adicionar:");
        System.out.println("[1] Carro");
        System.out.println("[2] Moto");
        System.out.println("[3] Coletivo");
        System.out.print("Escolha uma opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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

        String modelo;
        switch (tipo) {
            case 1 -> {
                System.out.println("Tipos de carro: SUV, Sedan, Hatch");
                System.out.print("Escolha o tipo: ");
                modelo = scanner.nextLine();
                System.out.print("Número de portas: ");
                int portas = scanner.nextInt();
                scanner.nextLine();
                carros.add(new Carro(id, placa, capacidade, alugado, ano, portas));
            }
            case 2 -> {
                System.out.println("Tipos de moto: Street, Scooter");
                System.out.print("Escolha o tipo: ");
                modelo = scanner.nextLine();
                System.out.print("Tem baú? (true/false): ");
                boolean bau = scanner.nextBoolean();
                scanner.nextLine();
                motos.add(new Moto(id, placa, capacidade, alugado, ano, bau));
            }
            case 3 -> {
                System.out.println("Tipos de coletivo: Van, Mini Van, Ônibus");
                System.out.print("Escolha o tipo: ");
                modelo = scanner.nextLine();
                System.out.print("Número de portas: ");
                int portasC = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Tem banheiro? (true/false): ");
                boolean banheiro = scanner.nextBoolean();
                scanner.nextLine();
                coletivos.add(new Coletivo(id, placa, capacidade, alugado, ano, portasC, banheiro));
            }
            default -> {
                System.out.println("Tipo inválido.");
                return;
            }
        }

        // Salvar no banco de dados
        String sql = "INSERT INTO veiculos (tipo, placa, capacidade, alugado, ano) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, modelo);
            stmt.setString(2, placa);
            stmt.setInt(3, capacidade);
            stmt.setBoolean(4, alugado);
            stmt.setInt(5, ano);

            stmt.executeUpdate();
            System.out.println("Veículo salvo no banco de dados com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar veículo: " + e.getMessage());
        }
    }

    private static void excluirVeiculo(Scanner scanner) {
        System.out.println("\nEscolha o tipo de veículo para excluir:");
        System.out.println("[1] Carro");
        System.out.println("[2] Moto");
        System.out.println("[3] Coletivo");
        System.out.print("Escolha uma opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID do veículo a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = switch (tipo) {
            case 1 -> carros.removeIf(c -> c.getId() == id);
            case 2 -> motos.removeIf(m -> m.getId() == id);
            case 3 -> coletivos.removeIf(c -> c.getId() == id);
            default -> {
                System.out.println("Tipo inválido.");
                yield false;
            }
        };

        if (encontrado) {
            System.out.println("Veículo excluído com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private static void exibirStatus() {
        System.out.println("\nStatus dos veículos:");
        System.out.println("Carros:");
        carros.forEach(Carro::exibirInformacoes);

        System.out.println("\nMotos:");
        motos.forEach(Moto::exibirInformacoes);

        System.out.println("\nColetivos:");
        coletivos.forEach(Coletivo::exibirInformacoes);
    }
}
