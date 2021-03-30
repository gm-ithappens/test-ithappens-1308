package com.estoque.Desafio.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name="estoques",uniqueConstraints={ @UniqueConstraint(columnNames = {"produto_id", "filial_id"})})
public class Estoque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Obrigatório selecionar um produto")
    @ManyToOne
    private Produto produto;

    @NotEmpty(message = "Obrigatório selecionar uma filial")
    @ManyToOne
    private Filial filial;

    @NotEmpty(message = "Obrigatório incluir um valor")
    private Double preco;

    @NotEmpty(message = "Obrigatório informar a quantidade")
    private Double qtde=0.;

    public Estoque() {
        super();
    }

    public Estoque(Produto produto, Filial filial, Double preco) {
        this.produto = produto;
        this.filial = filial;
        this.preco = preco;
        this.qtde = 0.;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQtde() {
        return qtde;
    }

    public void setQtde(Double qtde) {
        this.qtde = qtde;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}