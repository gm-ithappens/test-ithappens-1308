package br.com.pwneo.estoque_back_end.models;

import br.com.pwneo.estoque_back_end.models.supports.Operation;
import br.com.pwneo.estoque_back_end.models.users.Client;
import br.com.pwneo.estoque_back_end.models.users.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-29
 * <p>
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
    private Integer quantity = 0;
    private Double totalPrice = 0.0;

    @ManyToOne
    private Subsidiary subsidiary;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Operation operation;

    public StockOrder(Integer id, String note, Subsidiary subsidiary, Client client, Employee employee, Operation operation) {
        this.id = id;
        this.note = note;
        this.subsidiary = subsidiary;
        this.client = client;
        this.employee = employee;
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

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockOrder that = (StockOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(note, that.note) &&
                Objects.equals(subsidiary, that.subsidiary) &&
                Objects.equals(client, that.client) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note, subsidiary, client, employee, operation);
    }

    @Override
    public String toString() {
        return "StockOrder{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", subsidiary=" + subsidiary +
                ", client=" + client +
                ", employee=" + employee +
                '}';
    }
}