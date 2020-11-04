package br.com.felipeomachado.apitest1308pulse.entities;

import br.com.felipeomachado.apitest1308pulse.enums.OrderItemStatus;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public StockPK(){}

    public StockPK(Branch branch, Product product) {
        this.branch = branch;
        this.product = product;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPK stockPK = (StockPK) o;
        return branch.equals(stockPK.branch) &&
                product.equals(stockPK.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branch, product);
    }
}
