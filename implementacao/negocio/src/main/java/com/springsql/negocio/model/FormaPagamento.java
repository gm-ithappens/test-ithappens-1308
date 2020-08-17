package com.springsql.negocio.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="FormaPagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormaPagamento {
    @Id
    @GeneratedValue
    private int id;
    private String tipo;
    private int parcela;
}