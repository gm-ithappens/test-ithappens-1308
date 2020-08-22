package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class OrderItemStatusDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String statusMessage;

    public OrderItemStatusDTO(String message) {
        this.statusMessage = message;
    }

    public OrderItemStatusDTO() {
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void setStatusMessage(String message) {
        this.statusMessage = message;
    }
}