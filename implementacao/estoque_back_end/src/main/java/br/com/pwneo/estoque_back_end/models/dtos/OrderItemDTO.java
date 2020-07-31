package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class OrderItemDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer stockOrder;
    private Integer product;
    private Integer quantity;

    public OrderItemDTO(Integer stockOrder, Integer product, Integer quantity) {
        this.stockOrder = stockOrder;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItemDTO() {
    }

    public Integer getStockOrder() {
        return stockOrder;
    }

    public void setStockOrder(Integer stockOrder) {
        this.stockOrder = stockOrder;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "stockOrder=" + stockOrder +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
