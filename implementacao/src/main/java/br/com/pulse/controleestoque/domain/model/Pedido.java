package br.com.pulse.controleestoque.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorPedido;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento")
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pedido", nullable = false)
    private TipoPedido tipoPedido;

    private String obsEntrega;

    @OneToMany(mappedBy = "id.pedido", cascade = CascadeType.PERSIST)
    private List<ItensPedido> itensPedido = new ArrayList<>();
}
