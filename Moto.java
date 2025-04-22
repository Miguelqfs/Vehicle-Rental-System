public class Moto extends Veiculo {
    private boolean bau;

    public Moto(int id, String placa, int capacidade, boolean alugado, int ano, boolean bau) {
        super(id, placa, capacidade, alugado, ano);
        this.bau = bau;
    }

    public boolean temBau() {
        return bau;
    }

    public void setBau(boolean bau) {
        this.bau = bau;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Moto - ID: " + getId() + ", Placa: " + getPlaca() + 
                           ", Capacidade: " + getCapacidade() + ", Ano: " + getAno() + 
                           ", Alugado: " + isAlugado() + ", Baú: " + (bau ? "Sim" : "Não"));
    }
}