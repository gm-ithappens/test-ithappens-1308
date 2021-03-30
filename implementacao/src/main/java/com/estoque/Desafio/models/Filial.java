package com.estoque.Desafio.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="filiais")
public class Filial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Campo Razão Social obrigatório")
    private String razao;
    private String fantasia;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.REMOVE)
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.REMOVE)
    private List<Estoque> estoques;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.REMOVE)
    private List<Pedido> pedidos;

    public Filial() {
        super();
    }

    public Filial(String razao) {
        this.razao = razao;
        this.fantasia = razao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}