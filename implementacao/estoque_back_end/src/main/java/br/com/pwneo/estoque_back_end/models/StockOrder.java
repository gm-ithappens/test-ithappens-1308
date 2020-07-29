package br.com.pwneo.estoque_back_end.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock_order")
public class StockOrder implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;

    @OneToOne
    private Subsidiary subsidiary;

    @OneToOne
    private Client client;

    @OneToOne
    private Employee employee;

    @OneToOne
    private PaymentMethod paymentMethod;

    public StockOrder(Long id, String note, Subsidiary subsidiary, Client client, Employee employee, PaymentMethod paymentMethod) {
        this.id = id;
        this.note = note;
        this.subsidiary = subsidiary;
        this.client = client;
        this.employee = employee;
        this.paymentMethod = paymentMethod;
    }

    public StockOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
