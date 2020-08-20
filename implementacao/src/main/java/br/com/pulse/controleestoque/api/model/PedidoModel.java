package br.com.pulse.controleestoque.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoModel {

    private Long id;

    private ClienteModel cliente;

    private UsuarioModel usuario;

    @JsonProperty("tipo_pedido")
    private TipoPedidoModel tipoPedido;

    @JsonProperty("forma_pagamento")
    private FormaPagamentoModel formaPagamento;

    private FilialModel filial;

    @JsonProperty("obs_entrega")
    private String obsEntrega;

    private List<ItensPedidoModel> itens;
}
