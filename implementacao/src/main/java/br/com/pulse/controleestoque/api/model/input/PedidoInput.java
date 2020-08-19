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

    private List<ItemInput> itens;
}
