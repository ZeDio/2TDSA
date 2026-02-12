package br.com.fiap.beans;

public abstract class Telefone {
    private String tipo;

    public Telefone(String telefoneCelular) {
    }

    abstract public void disca(String numero);
    abstract public void toca(int numToques);

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
