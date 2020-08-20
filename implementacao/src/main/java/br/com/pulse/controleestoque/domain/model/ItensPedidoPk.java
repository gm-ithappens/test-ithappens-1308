package br.com.pulse.controleestoque.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@Embeddable
public class ItensPedidoPk implements Serializable {

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;
}