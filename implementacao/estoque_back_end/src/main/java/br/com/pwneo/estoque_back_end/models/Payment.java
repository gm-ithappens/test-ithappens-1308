package br.com.pwneo.estoque_back_end.models;

import br.com.pwneo.estoque_back_end.models.supports.PaymentMethod;
import br.com.pwneo.estoque_back_end.models.supports.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double total = 0.0;

    @OneToOne
    private StockOrder stockOrder;

    @ManyToOne
    private PaymentMethod paymentMethod;

    @ManyToOne
    private Status status;

    public Payment(Integer id, PaymentMethod paymentMethod, StockOrder stockOrder, Status status) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.stockOrder = stockOrder;
        this.status = status;
    }

    public Payment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public StockOrder getStockOrder() {
        return stockOrder;
    }

    public void setStockOrder(StockOrder stockOrder) {
        this.stockOrder = stockOrder;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(paymentMethod, payment.paymentMethod) &&
                Objects.equals(stockOrder, payment.stockOrder) &&
                Objects.equals(status, payment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, stockOrder, status);
    }
}
