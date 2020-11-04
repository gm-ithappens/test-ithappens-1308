package br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RequestSetPaymentMethodStockOrder {
    @NotNull
    @Min(value = 1)
    private Long stockOrderId;

    @NotNull
    @Min(value = 1)
    private Long paymentMethodId;

    public Long getStockOrderId() {
        return stockOrderId;
    }

    public void setStockOrderId(Long stockOrderId) {
        this.stockOrderId = stockOrderId;
    }


    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
