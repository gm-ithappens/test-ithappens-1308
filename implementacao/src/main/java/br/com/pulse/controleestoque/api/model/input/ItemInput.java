package br.com.pulse.controleestoque.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInput {

    private ProdutoInput produto;

    private int quantidade;

    private StatusPedidoInput status;
}
