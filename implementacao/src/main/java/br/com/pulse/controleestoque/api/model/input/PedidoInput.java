package br.com.pulse.controleestoque.api.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PedidoInput {

    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @ApiModelProperty(example = "Minha casa tem um portão branco")
    @JsonProperty("obs_entrega")
    private String obsEntrega;

    @Valid
    @NotNull
    @JsonProperty("tipo_pedido")
    private TipoPedidoIdInput tipoPedido;

    @NotNull
    @Valid
    @JsonProperty("forma_pagamento")
    private FormaPagamentoIdInput formaPagamento;

    @Valid
    @NotNull
    private List<ItemInput> itens;

    @Valid
    @NotNull
    private FilialIdInput filial;
}
