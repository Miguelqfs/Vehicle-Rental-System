package src.models;

public class Coletivo extends Veiculo {
    private int portas;
    private boolean banheiro;

    public Coletivo(int id, String placa, int capacidade, boolean alugado, int ano, int portas, boolean banheiro) {
        super(id, placa, capacidade, alugado, ano);
        this.portas = portas;
        this.banheiro = banheiro;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public boolean temBanheiro() {
        return banheiro;
    }

    public void setBanheiro(boolean banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Coletivo - ID: " + getId() + ", Placa: " + getPlaca() + 
                           ", Capacidade: " + getCapacidade() + ", Ano: " + getAno() + 
                           ", Alugado: " + isAlugado() + ", Portas: " + portas +
                           ", Banheiro: " + (banheiro ? "Sim" : "Não"));
    }
}