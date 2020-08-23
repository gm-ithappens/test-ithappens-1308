package br.com.pulse.controleestoque.api.model.input;

public enum StatusPedidoInput {

    ATIVO("Ativo"),
    CANCELADO("Cancelado"),
    PROCESSADO("Processado");

    private String descricao;

    StatusPedidoInput(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
