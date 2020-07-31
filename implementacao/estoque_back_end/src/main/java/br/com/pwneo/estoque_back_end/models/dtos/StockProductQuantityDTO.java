package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class StockProductQuantityDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer quantity;
    private Integer product;
    private Integer stock;

    public StockProductQuantityDTO(Integer quantity, Integer product, Integer stock) {
        this.quantity = quantity;
        this.product = product;
        this.stock = stock;
    }

    public StockProductQuantityDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
