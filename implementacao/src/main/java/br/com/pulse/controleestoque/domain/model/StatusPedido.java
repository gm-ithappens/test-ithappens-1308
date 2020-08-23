package br.com.pulse.controleestoque.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

    ATIVO("Ativo"),
    CANCELADO("Cancelado"),
    PROCESSADO("Processado");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}