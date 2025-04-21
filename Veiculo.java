// Classe abstrata Veiculo

public abstract class Veiculo {
    private int id;
    private String placa;
    private int capacidade;
    private boolean alugado;
    private int ano;

    // Construtor
    public Veiculo(int id, String placa, int capacidade, boolean alugado, int ano) {
        this.id = id;
        this.placa = placa;
        this.capacidade = capacidade;
        this.alugado = alugado;
        this.ano = ano;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    // Método abstrato para exibir informações do veículo
    public abstract void exibirInformacoes();
}
