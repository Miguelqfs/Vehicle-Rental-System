import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Carro> carros = new ArrayList<>();
    private static ArrayList<Moto> motos = new ArrayList<>();
    private static ArrayList<Coletivo> coletivos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=======================");
            System.out.println("  SISTEMA DE VEÍCULOS  ");
            System.out.println("=======================");
            System.out.println("\nMENU:");
            System.out.println("[1] ADICIONAR");
            System.out.println("[2] EXCLUIR");
            System.out.println("[3] STATUS");
            System.out.println("[4] SAIR");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarVeiculo(scanner);
                    break;
                case 2:
                    excluirVeiculo(scanner);
                    break;
                case 3:
                    exibirStatus();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
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

        String tipoVeiculo = "";
        switch (tipo) {
            case 1:
                System.out.println("Tipos de carro: SUV, Sedan, Hatch");
                System.out.print("Escolha o tipo: ");
                tipoVeiculo = scanner.nextLine();
                carros.add(new Carro(id, placa, capacidade, alugado, ano));
                break;
            case 2:
                System.out.println("Tipos de moto: Street, Scooter");
                System.out.print("Escolha o tipo: ");
                tipoVeiculo = scanner.nextLine();
                motos.add(new Moto(id, placa, capacidade, alugado, ano));
                break;
            case 3:
                System.out.println("Tipos de coletivo: Van, Mini Van, Onibus");
                System.out.print("Escolha o tipo: ");
                tipoVeiculo = scanner.nextLine();
                coletivos.add(new Coletivo(id, placa, capacidade, alugado, ano));
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        // Salvar no banco de dados
        String sql = "INSERT INTO veiculos (tipo, placa, capacidade, alugado, ano) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipoVeiculo);
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

        boolean encontrado = false;

        switch (tipo) {
            case 1: // Carro
                encontrado = carros.removeIf(carro -> carro.getId() == id);
                break;
            case 2: // Moto
                encontrado = motos.removeIf(moto -> moto.getId() == id);
                break;
            case 3: // Coletivo
                encontrado = coletivos.removeIf(coletivo -> coletivo.getId() == id);
                break;
            default:
                System.out.println("Tipo inválido.");
        }

        if (encontrado) {
            System.out.println("Veículo excluído com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private static void exibirStatus() {
        System.out.println("\nStatus dos veículos:");
        System.out.println("Carros:");
        for (Carro carro : carros) {
            carro.exibirInformacoes();
        }

        System.out.println("\nMotos:");
        for (Moto moto : motos) {
            moto.exibirInformacoes();
        }

        System.out.println("\nColetivos:");
        for (Coletivo coletivo : coletivos) {
            coletivo.exibirInformacoes();
        }
    }
}
