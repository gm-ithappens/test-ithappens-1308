package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class StockProductNewDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer quantity;
    private Double price;
    private Integer product;
    private Integer stock;

    public StockProductNewDTO(Integer quantity, Double price, Integer product, Integer stock) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.stock = stock;
    }

    public StockProductNewDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
