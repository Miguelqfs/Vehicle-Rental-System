public class Coletivo extends Veiculo {

    public Coletivo(int id, String placa, int capacidade, boolean alugado, int ano) {
        super(id, placa, capacidade, alugado, ano);
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Coletivo - ID: " + getId() + ", Placa: " + getPlaca() + 
                           ", Capacidade: " + getCapacidade() + ", Ano: " + getAno() + 
                           ", Alugado: " + isAlugado());
    }
}
