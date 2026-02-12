package br.com.fiap.beans;

public enum TipoTelefone {
    CELULAR("Telefone Celular"),
    FIXO("Telefone Fixo"),
    PUBLICO("Telefone Publico");

    private final String descricao;

    TipoTelefone(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
