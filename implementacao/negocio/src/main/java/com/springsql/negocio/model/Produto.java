package com.springsql.negocio.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produto {
    @Id
    @GeneratedValue
    private int id;
    private String descricao;
    private String codbarra;
    private float valor;

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCodbarra() {
        return codbarra;
    }

    public float getValor() {
        return valor;
    }
}
