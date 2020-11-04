package br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RequestFinishStockOrder {
    @NotNull
    @Min(value = 1)
    private Long stockOrderId;

    public Long getStockOrderId() {
        return stockOrderId;
    }
}
