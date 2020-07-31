package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class StockProductPriceDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Double price;
    private Integer product;
    private Integer stock;

    public StockProductPriceDTO(Double price, Integer product, Integer stock) {
        this.price = price;
        this.product = product;
        this.stock = stock;
    }

    public StockProductPriceDTO() {
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
