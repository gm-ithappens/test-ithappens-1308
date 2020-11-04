package br.com.felipeomachado.apitest1308pulse.stockOrder.entities;

import br.com.felipeomachado.apitest1308pulse.stockOrder.enums.OrderItemStatus;
import br.com.felipeomachado.apitest1308pulse.product.entities.Product;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class StockOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;

    private Double unitPrice;

    private Double totalPrice;

    private Integer status;

    @PastOrPresent
    private LocalDateTime createdAt = LocalDateTime.now();

    @PastOrPresent
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "stockOrder_id")
    private StockOrder stockOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Deprecated
    public StockOrderItem(){}

    public StockOrderItem( Double quantity, Double unitPrice, Double totalPrice, OrderItemStatus status, StockOrder stockOrder, Product product) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        setStatus(status);
        this.stockOrder = stockOrder;
        this.product = product;
    }

    public OrderItemStatus getStatus() {
        return OrderItemStatus.valueOf(this.status);
    }

    public void setStatus(OrderItemStatus orderItemStatus) {
        if ( orderItemStatus != null)
            this.status = orderItemStatus.getCode();
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockOrder getStockOrder() {
        return stockOrder;
    }

    public void setStockOrder(StockOrder stockOrder) {
        this.stockOrder = stockOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockOrderItem that = (StockOrderItem) o;
        return id.equals(that.id) &&
                quantity.equals(that.quantity) &&
                unitPrice.equals(that.unitPrice) &&
                totalPrice.equals(that.totalPrice) &&
                status.equals(that.status) &&
                createdAt.equals(that.createdAt) &&
                updatedAt.equals(that.updatedAt) &&
                stockOrder.equals(that.stockOrder) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, unitPrice, totalPrice, status, createdAt, updatedAt, stockOrder, product);
    }

    @Override
    public String toString() {
        return "StockOrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", stockOrder=" + stockOrder +
                ", product=" + product +
                '}';
    }
}
