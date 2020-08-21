package br.com.pulse.controleestoque.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    private ClienteModel cliente;

    private UsuarioModel usuario;

    @JsonProperty("tipo_pedido")
    private TipoPedidoModel tipoPedido;

    @JsonProperty("forma_pagamento")
    private FormaPagamentoModel formaPagamento;

    private FilialModel filial;

    @ApiModelProperty(example = "Minha casa tem um portão de cor branca")
    @JsonProperty("obs_entrega")
    private String obsEntrega;

    @JsonProperty("valor_total")
    @ApiModelProperty(example = "35.00")
    private BigDecimal valorPedido;

    private List<ItensPedidoModel> itens;
}
