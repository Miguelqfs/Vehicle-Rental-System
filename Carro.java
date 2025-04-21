public class Carro extends Veiculo {

    public Carro(int id, String placa, int capacidade, boolean alugado, int ano) {
        super(id, placa, capacidade, alugado, ano);
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Carro - ID: " + getId() + ", Placa: " + getPlaca() + 
                           ", Capacidade: " + getCapacidade() + ", Ano: " + getAno() + 
                           ", Alugado: " + isAlugado());
    }
}
