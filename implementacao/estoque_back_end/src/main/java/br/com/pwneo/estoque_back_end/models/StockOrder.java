package br.com.pwneo.estoque_back_end.models;

import br.com.pwneo.estoque_back_end.models.supports.Operation;
import br.com.pwneo.estoque_back_end.models.supports.PaymentMethod;
import br.com.pwneo.estoque_back_end.models.users.Client;
import br.com.pwneo.estoque_back_end.models.users.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-29
 *
 * Classe responsável por mapear as informações dos pedidos de estoque
 */

@Entity
@Table(name = "stock_order")
public class StockOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String note;

    @ManyToOne
    private Subsidiary subsidiary;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private PaymentMethod paymentMethod;

    @ManyToOne
    private Operation operation;

    public StockOrder(Integer id, String note, Subsidiary subsidiary, Client client, Employee employee, PaymentMethod paymentMethod, Operation operation) {
        this.id = id;
        this.note = note;
        this.subsidiary = subsidiary;
        this.client = client;
        this.employee = employee;
        this.paymentMethod = paymentMethod;
        this.operation = operation;
    }

    public StockOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "StockOrder{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", subsidiary=" + subsidiary +
                ", client=" + client +
                ", employee=" + employee +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}