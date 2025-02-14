package app;

public class Deducao {
    public enum Tipo {
        CONTRIBUICAO_PREVIDENCIARIA,
        PENSAO_ALIMENTICIA,
        OUTRA
    }

    private String nome;
    private float valor;
    private Tipo tipo;

    public Deducao(String nome, float valor, Tipo tipo) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }
}