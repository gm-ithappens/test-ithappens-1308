package br.com.felipeomachado.apitest1308pulse.stockOrder.entities;

import br.com.felipeomachado.apitest1308pulse.entities.Branch;
import br.com.felipeomachado.apitest1308pulse.product.entities.Product;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.ValidationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Stock {
    @EmbeddedId
    private StockPK id = new StockPK();

    @Min(value = 0)
    private Double quantity;

    @PastOrPresent
    private LocalDateTime createdAt = LocalDateTime.now();

    @PastOrPresent
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Deprecated
    public Stock() {
    }

    public Stock(Branch branch, Product product, @Min(value = 0) Double quantity) {
        id.setBranch(branch);
        id.setProduct(product);
        this.quantity = quantity;
    }

    public StockPK getId() {
        return id;
    }

    public void setId(StockPK id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public void increaseQuantity(Double quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity(Double quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0) {
            throw new ValidationException("BranchId: " + this.id.getBranch().getId() + ", ProductId: " + this.id.getProduct().getId() + ", Stock cannot be less than 0!");
        }
    }

}
