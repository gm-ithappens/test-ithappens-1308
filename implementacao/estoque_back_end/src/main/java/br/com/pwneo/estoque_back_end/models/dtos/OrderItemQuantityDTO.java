package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class OrderItemQuantityDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer stockOrder;
    private Integer productId;
    private Integer quantity;

    public OrderItemQuantityDTO(Integer stockOrder, Integer productId, Integer quantity) {
        this.stockOrder = stockOrder;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderItemQuantityDTO() {
    }

    public Integer getStockOrder() {
        return stockOrder;
    }

    public void setStockOrder(Integer stockOrder) {
        this.stockOrder = stockOrder;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
