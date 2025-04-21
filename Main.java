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

        switch (tipo) {
            case 1: // Carro
                System.out.println("Tipos de carro: SUV, Sedan, Hatch");
                System.out.print("Escolha o tipo: ");
                String tipoCarro = scanner.nextLine();
                carros.add(new Carro(id, placa, capacidade, alugado, ano));
                System.out.println("Carro adicionado com sucesso!");
                break;
            case 2: // Moto
                System.out.println("Tipos de moto: Street, Scooter");
                System.out.print("Escolha o tipo: ");
                String tipoMoto = scanner.nextLine();
                motos.add(new Moto(id, placa, capacidade, alugado, ano));
                System.out.println("Moto adicionada com sucesso!");
                break;
            case 3: // Coletivo
                System.out.println("Tipos de coletivo: Van, Mini Van, Onibus");
                System.out.print("Escolha o tipo: ");
                String tipoColetivo = scanner.nextLine();
                coletivos.add(new Coletivo(id, placa, capacidade, alugado, ano));
                System.out.println("Coletivo adicionado com sucesso!");
                break;
            default:
                System.out.println("Tipo inválido.");
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
