package br.com.pwneo.estoque_back_end.models;

import br.com.pwneo.estoque_back_end.models.supports.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-29
 *
 * Classe responsável por mapear as informações dos itens do pedido de estoque
 */

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    @Transient()
    private Double total;

    @ManyToOne
    private Status status;

    @ManyToOne
    private StockProduct stockProduct;

    @ManyToOne
    private StockOrder stockOrder;

    public OrderItem(Integer id, Integer quantity, Status status, StockProduct stockProduct, StockOrder stockOrder) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
        this.stockProduct = stockProduct;
        this.stockOrder = stockOrder;
    }

    public OrderItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public StockProduct getStockProduct() {
        return stockProduct;
    }

    public void setStockProduct(StockProduct stockProduct) {
        this.stockProduct = stockProduct;
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
                Objects.equals(stockProduct, orderItem.stockProduct) &&
                Objects.equals(stockOrder, orderItem.stockOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, total, status, stockProduct, stockOrder);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", total=" + total +
                ", status=" + status +
                ", stockItem=" + stockProduct +
                ", stockOrder=" + stockOrder +
                '}';
    }
}
