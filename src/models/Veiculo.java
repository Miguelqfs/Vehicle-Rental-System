package models;

// Classe abstrata Veiculo
public abstract class Veiculo {
    private String placa;
    private int capacidade;
    private boolean alugado;
    private int ano;
    private String tipo;

    // Construtor
    public Veiculo(String placa, int capacidade, boolean alugado, int ano, String tipo) {
        this.placa = placa;
        this.capacidade = capacidade;
        this.alugado = alugado;
        this.ano = ano;
        this.tipo = tipo;
    }

    // Getters e Setters
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Método abstrato para exibir informações do veículo
    public abstract void exibirInformacoes();
}
