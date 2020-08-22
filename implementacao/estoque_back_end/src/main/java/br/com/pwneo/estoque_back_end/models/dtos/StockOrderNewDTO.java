package br.com.pwneo.estoque_back_end.models.dtos;

import java.io.Serializable;

public class StockOrderNewDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String note;
    private Integer subsidiary;
    private Integer client;
    private Integer employee;
    private Integer paymentMethod;
    private Integer operation;

    public StockOrderNewDTO(String note, Integer subsidiary, Integer client, Integer employee, Integer paymentMethod, Integer operation) {
        this.note = note;
        this.subsidiary = subsidiary;
        this.client = client;
        this.employee = employee;
        this.paymentMethod = paymentMethod;
        this.operation = operation;
    }

    public StockOrderNewDTO() {
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Integer subsidiary) {
        this.subsidiary = subsidiary;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }
}
