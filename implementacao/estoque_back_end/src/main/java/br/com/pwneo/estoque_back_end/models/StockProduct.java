package br.com.pwneo.estoque_back_end.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-29
 *
 * Classe responsável por mapear as informações dos produtos existentes nos estoques
 */

@Entity
@Table(name = "stock_product")
public class StockProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Stock stock;

    public StockProduct(Integer id, Product product, Integer quantity, Double price, Stock stock) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.stock = stock;
    }

    public StockProduct() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockProduct that = (StockProduct) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(price, that.price) &&
                Objects.equals(product, that.product) &&
                Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price, product, stock);
    }

    @Override
    public String toString() {
        return "StockProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", product=" + product +
                ", stock=" + stock +
                '}';
    }
}
