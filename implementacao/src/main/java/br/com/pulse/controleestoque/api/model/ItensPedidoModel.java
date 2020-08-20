package br.com.pulse.controleestoque.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItensPedidoModel {

    private ProdutoModel produto;
    private int quantidade;
    private String status;
}
