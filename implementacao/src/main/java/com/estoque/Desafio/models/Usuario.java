package com.estoque.Desafio.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="usuarios",uniqueConstraints={ @UniqueConstraint(columnNames = {"username"})})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    @Column(nullable = false)
    private String username;
    private String senha = "1234";
    @ManyToOne
    private Filial filial;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Pedido> pedidos;

    public Usuario(){
        super();
    }

    public Usuario(String nome, String username,Filial filial){
        this.nome = nome;
        this.username = username;
        this.filial = filial;
        this.senha = "1234";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

}
