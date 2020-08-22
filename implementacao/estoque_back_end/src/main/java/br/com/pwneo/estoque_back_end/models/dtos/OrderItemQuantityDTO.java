package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class OrderItemQuantityDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer quantity;

    public OrderItemQuantityDTO(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderItemQuantityDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
