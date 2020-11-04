package br.com.felipeomachado.apitest1308pulse.stockOrder.entities.request;

import br.com.felipeomachado.apitest1308pulse.entities.*;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrderIn;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RequestStockOrderIn {

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Min(value = 1)
    private Long branchId;

    @NotNull
    @Min(value = 1)
    private Long userId;

    @NotNull
    @Min(value = 1)
    private Long providerId;

    private Set<RequestItemStockOrderIn> items = new HashSet<>();

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Set<RequestItemStockOrderIn> getItems() {
        return items;
    }

    public StockOrderIn toModel() {
        StockOrderIn newStockOrderIn = new StockOrderIn(getDate(), new Branch(branchId), new User(userId), new Provider(providerId), items.size(), items.stream().mapToDouble(e -> (e.getQuantity() * e.getUnitPrice())).reduce(0, Double::sum));
        newStockOrderIn.getItems().addAll(items.stream().map(RequestItemStockOrderIn::toModel).collect(Collectors.toSet()));

        return newStockOrderIn;
    }

    @Override
    public String toString() {
        return "RequestStockOrderIn{" +
                "date=" + date +
                ", branchId=" + branchId +
                ", userId=" + userId +
                ", providerId=" + providerId +
                ", items=" + items +
                '}';
    }
}
