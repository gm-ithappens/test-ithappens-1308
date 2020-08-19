package br.com.pulse.controleestoque.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estoque {

    @EmbeddedId
    private EstoquePk id;

    @Column(nullable = false)
    private int quantidade;

    @Getter
    @Setter
    @Embeddable
    public class EstoquePk implements Serializable {

        @ManyToOne
        @JoinColumn(name = "id_produto", nullable = false)
        private Produto produto;

        @ManyToOne
        @JoinColumn(name = "id_filial", nullable = false)
        private Filial filial;
    }
}


