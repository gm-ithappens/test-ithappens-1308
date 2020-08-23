package br.com.pulse.controleestoque.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estoque {

    @EqualsAndHashCode.Include
    @EmbeddedId
    private EstoquePk id;

    @Column(nullable = false)
    private int quantidade;

    private void adicionar(int quantidade) {
        this.quantidade =+ quantidade;
    }

    private void retirar(int quantidade) {
        this.quantidade =- quantidade;
    }
}


