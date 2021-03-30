package com.estoque.Desafio.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido implements Serializable {

    //compatibilidade de versões serializadas das classes
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Usuario usuario; //Registrar usuário

    @ManyToOne
    private Filial filial; //Registrar a filial

    @ManyToOne
    private Cliente cliente; //Registrar o cliente

    @ManyToOne
    private FormaPagamento formaPagamento; //Registrar a forma de pagamento do Pedido

    private Date dataSaida = new Date(); // valor padrão data atual
    private String Observacao; //observações de entrega
    private String tipo = "S";
    private Double totalPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
    private List<PedidoItem> pedidoItems;


    public Pedido() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }

    public List<PedidoItem> getPedidoItems() {
        return pedidoItems;
    }

    public void setPedidoItems(List<PedidoItem> pedidoItems) {
        this.pedidoItems = pedidoItems;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Boolean isSaida(){
        return this.tipo.equals("S");
    }
}