package br.com.pwneo.estoque_back_end.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @Transient()
    private Double total;

    @ManyToOne
    private Status status;

    @ManyToOne
    private StockItem stockItem;

    @ManyToOne
    private StockOrder stockOrder;

    public OrderItem(Long id, Integer quantity, Double total, Status status, StockItem stockItem, StockOrder stockOrder) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
        this.stockItem = stockItem;
        this.stockOrder = stockOrder;
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    public StockOrder getStockOrder() {
        return stockOrder;
    }

    public void setStockOrder(StockOrder stockOrder) {
        this.stockOrder = stockOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) &&
                Objects.equals(quantity, orderItem.quantity) &&
                Objects.equals(total, orderItem.total) &&
                Objects.equals(status, orderItem.status) &&
                Objects.equals(stockItem, orderItem.stockItem) &&
                Objects.equals(stockOrder, orderItem.stockOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, total, status, stockItem, stockOrder);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", total=" + total +
                ", status=" + status +
                ", stockItem=" + stockItem +
                ", stockOrder=" + stockOrder +
                '}';
    }
}
