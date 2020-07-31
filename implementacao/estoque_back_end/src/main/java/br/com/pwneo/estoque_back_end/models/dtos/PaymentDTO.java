package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class PaymentDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer stockOrder;
    private Integer paymentMethod;
    private Integer status;

    public PaymentDTO(Integer stockOrder, Integer paymentMethod, Integer status) {
        this.stockOrder = stockOrder;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public PaymentDTO() {
    }

    public Integer getStockOrder() {
        return stockOrder;
    }

    public void setStockOrder(Integer stockOrder) {
        this.stockOrder = stockOrder;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
