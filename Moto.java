public class Moto extends Veiculo {

    public Moto(int id, String placa, int capacidade, boolean alugado, int ano) {
        super(id, placa, capacidade, alugado, ano);
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Moto - ID: " + getId() + ", Placa: " + getPlaca() + 
                           ", Capacidade: " + getCapacidade() + ", Ano: " + getAno() + 
                           ", Alugado: " + isAlugado());
    }
}
