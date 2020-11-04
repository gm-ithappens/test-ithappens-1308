package br.com.felipeomachado.apitest1308pulse.entities.request;

import br.com.felipeomachado.apitest1308pulse.entities.Branch;
import br.com.felipeomachado.apitest1308pulse.entities.Customer;
import br.com.felipeomachado.apitest1308pulse.entities.StockOrderOut;
import br.com.felipeomachado.apitest1308pulse.entities.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RequestStockOrderOut {

    @NotNull
    private LocalDateTime date;

    private String deliveryNote;

    @NotNull
    @Min(value = 1)
    private Long branchId;

    @NotNull
    @Min(value = 1)
    private Long userId;

    @NotNull
    @Min(value = 1)
    private Long customerId;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public StockOrderOut build() {
        return new StockOrderOut(getDate(), new Branch(branchId), new User(userId), deliveryNote, new Customer(customerId), 0, 0d);
    }

}
