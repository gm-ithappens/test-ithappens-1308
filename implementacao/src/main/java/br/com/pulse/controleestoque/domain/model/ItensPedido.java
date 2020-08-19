package br.com.pulse.controleestoque.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
public class ItensPedido {

    @EmbeddedId
    private ItensPedidoPk id;

    @Column(nullable = false)
    private int quantidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Getter
    @Setter
    @Embeddable
    public class ItensPedidoPk implements Serializable {

        @ManyToOne
        @JoinColumn(name = "id_pedido", nullable = false)
        private Pedido pedido;

        @ManyToOne
        @JoinColumn(name = "id_produto", nullable = false)
        private Produto produto;
    }
}
