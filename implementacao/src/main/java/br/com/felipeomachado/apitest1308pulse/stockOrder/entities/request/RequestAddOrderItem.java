package br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request;

import br.com.felipeomachado.apitest1308pulse.entities.Branch;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrderItem;
import br.com.felipeomachado.apitest1308pulse.product.entities.Product;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrder;
import br.com.felipeomachado.apitest1308pulse.stockOrder.enums.OrderItemStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RequestAddOrderItem {
    @NotNull
    @Min(value = 1)
    private Long stockOrderId;

    @NotNull
    @Min(value = 1)
    private Long branchId;

    @NotNull
    @Min(value = 1)
    private Long productId;

    @NotNull
    @Min(value = 1)
    private Double quantity;

    @NotNull
    @Min(value = 1)
    private Double unitPrice;

    public StockOrderItem toModel() {
        return new StockOrderItem(quantity, unitPrice, quantity * unitPrice, OrderItemStatus.ACTIVE, new StockOrder(stockOrderId, new Branch(branchId)), new Product(productId));
    }

    public Long getStockOrderId() {
        return stockOrderId;
    }

    public void setStockOrderId(Long stockOrderId) {
        this.stockOrderId = stockOrderId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
}
