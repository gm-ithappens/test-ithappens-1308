package br.com.felipeomachado.apitest1308pulse.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StockOrderOut extends StockOrder{
    private String deliveryNote;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @Deprecated
    public StockOrderOut() {super();}

    public StockOrderOut(LocalDateTime date, Branch branch, User user, String deliveryNote, Customer customer, Integer totalItems, Double totalPrice) {
        super(date, totalItems, totalPrice, branch, user);
        this.deliveryNote = deliveryNote;
        this.customer = customer;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return super.toString() + ", StockOrderOut{" +
                "deliveryNote='" + deliveryNote + '\'' +
                ", customer=" + customer +
                '}';
    }
}
