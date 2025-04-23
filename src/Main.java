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
        while (!scanner.hasNextInt()) { 
            System.out.println("Entrada inválida! Digite um número para a capacidade.");
            scanner.next(); 
        }
        int capacidade = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Ano: ");
        while (!scanner.hasNextInt()) { 
            System.out.println("Entrada inválida! Digite um número para o ano.");
            scanner.next(); 
        }
        int ano = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Está alugado? (true/false): ");
        while (!scanner.hasNextBoolean()) {
            System.out.println("Entrada inválida! Digite true ou false.");
            scanner.next(); 
        }
        boolean alugado = scanner.nextBoolean();
        scanner.nextLine(); 

        try {
            switch (tipo) {
                case 1 -> criarCarro(scanner, placa, capacidade, alugado, ano);
                case 2 -> criarMoto(scanner, placa, capacidade, alugado, ano);
                case 3 -> criarColetivo(scanner, placa, capacidade, alugado, ano);
            }
        } catch (Exception e) {
            System.err.println("Erro ao adicionar: " + e.getMessage());
        }
    }

    private static void criarCarro(Scanner scanner, String placa, int capacidade, 
                                  boolean alugado, int ano) throws Exception {
        System.out.println("Tipos disponíveis: SUV, Sedan, Hatch");
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("Portas: ");
        int portas = scanner.nextInt();
        scanner.nextLine();

        new Carro(placa, capacidade, alugado, ano, portas, tipo).adicionarNoBanco();
    }

    private static void criarMoto(Scanner scanner, String placa, int capacidade,
                                 boolean alugado, int ano) throws Exception {
        System.out.println("Tipos disponíveis: Street, Scooter");
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("Tem baú? (true/false): ");
        boolean bau = scanner.nextBoolean();
        scanner.nextLine();

        new Moto(placa, capacidade, alugado, ano, bau, tipo).adicionarNoBanco();
    }

    private static void criarColetivo(Scanner scanner, String placa, int capacidade,
                                     boolean alugado, int ano) throws Exception {
        System.out.println("Tipos disponíveis: Van, Mini Van, Ônibus");
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("Portas: ");
        int portas = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Banheiro? (true/false): ");
        boolean banheiro = scanner.nextBoolean();
        scanner.nextLine();

        new Coletivo(placa, capacidade, alugado, ano, portas, banheiro, tipo).adicionarNoBanco();
    }

    private static void excluirVeiculo(Scanner scanner) {
        int tipo = escolherTipoVeiculo(scanner, "excluir");
        System.out.print("ID do veículo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (tipo) {
                case 1 -> Carro.excluirCarro(id);
                case 2 -> Moto.excluirMoto(id);
                case 3 -> Coletivo.excluirColetivo(id);
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
}
