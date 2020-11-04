package br.com.felipeomachado.apitest1308pulse.entities.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RequestRemoveOrderItem {
    @NotNull
    @Min(value = 1)
    private Long stockOrderItemId;

    public Long getStockOrderItemId() {
        return stockOrderItemId;
    }
}
