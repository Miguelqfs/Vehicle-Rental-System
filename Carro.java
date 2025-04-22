public class Carro extends Veiculo {
    private int portas;

    public Carro(int id, String placa, int capacidade, boolean alugado, int ano, int portas) {
        super(id, placa, capacidade, alugado, ano);
        this.portas = portas;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Carro - ID: " + getId() + ", Placa: " + getPlaca() + 
                           ", Capacidade: " + getCapacidade() + ", Ano: " + getAno() + 
                           ", Alugado: " + isAlugado() + ", Portas: " + portas);
    }
}