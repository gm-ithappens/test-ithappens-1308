package br.com.pulse.controleestoque.api.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoInput {

    private ClienteIdInput cliente;

    @JsonProperty("obs_entrega")
    private String obsEntrega;

    @JsonProperty("tipo_pedido")
    private TipoPedidoIdInput tipoPedido;

    private List<ItemInput> itens;

    private FilialIdInput filial;
}
