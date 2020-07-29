package br.com.pwneo.estoque_back_end.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "stock_item")
public class StockItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private Double price;

    @OneToOne
    private Subsidiary subsidiary;

    public StockItem(Long id, Product product, Integer quantity, Double price, Subsidiary subsidiary) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.subsidiary = subsidiary;
    }

    public StockItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem item = (StockItem) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(product, item.product) &&
                Objects.equals(quantity, item.quantity) &&
                Objects.equals(price, item.price) &&
                Objects.equals(subsidiary, item.subsidiary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, price, subsidiary);
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subsidiary=" + subsidiary +
                '}';
    }
}
