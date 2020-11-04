package br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request;

import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrderItem;
import br.com.felipeomachado.apitest1308pulse.product.entities.Product;
import br.com.felipeomachado.apitest1308pulse.stockOrder.enums.OrderItemStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RequestItemStockOrderIn {
    @NotNull
    @Min(value = 1)
    private Long productId;

    @NotNull
    @Min(value = 1)
    private Double quantity;

    @NotNull
    @Min(value = 1)
    private Double unitPrice;

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

    public StockOrderItem toModel() {
        return new StockOrderItem(quantity, unitPrice, quantity * unitPrice, OrderItemStatus.ACTIVE, null, new Product(productId));
    }

    @Override
    public String toString() {
        return "RequestItemStockOrderIn{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
