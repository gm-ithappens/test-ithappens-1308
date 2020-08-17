package com.springsql.negocio.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="PedidoEstoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PedidoEstoque {
    @Id
    @GeneratedValue
    private int id;
    private String tipo;
    private int codCliente;
    private int codUsuario;
    private int codFilial;
    private int codPagamento;
    private String Observacao;
    private int itens;
    private float valor;

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public int getCodFilial() {
        return codFilial;
    }

    public int getCodPagamento() {
        return codPagamento;
    }

    public String getObservacao() {
        return Observacao;
    }

    public int getItens() {
        return itens;
    }

    public float getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public void setCodFilial(int codFilial) {
        this.codFilial = codFilial;
    }

    public void setCodPagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }

    public void setItens(int itens) {
        this.itens = itens;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}

