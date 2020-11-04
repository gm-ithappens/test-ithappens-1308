package br.com.felipeomachado.apitest1308pulse.entities.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RequestSetPaymentMethodStockOrder {
    @NotNull
    @Min(value = 1)
    private Long stockOrderId;

    @NotNull
    @Min(value = 1)
    private Integer paymentMethodCode;

    public Long getStockOrderId() {
        return stockOrderId;
    }

    public void setStockOrderId(Long stockOrderId) {
        this.stockOrderId = stockOrderId;
    }

    public Integer getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(Integer paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }
}
