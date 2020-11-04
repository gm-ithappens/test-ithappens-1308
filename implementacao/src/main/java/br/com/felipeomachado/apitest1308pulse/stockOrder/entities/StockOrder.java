package br.com.felipeomachado.apitest1308pulse.stockOrder.entities;

import br.com.felipeomachado.apitest1308pulse.entities.Branch;
import br.com.felipeomachado.apitest1308pulse.paymentMethod.entities.PaymentMethod;
import br.com.felipeomachado.apitest1308pulse.entities.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class StockOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Min(value = 0)
    private Integer totalItems;

    @NotNull
    @Min(value = 0)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id", nullable = true)
    private PaymentMethod paymentMethod;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PastOrPresent
    private LocalDateTime createdAt = LocalDateTime.now();

    @PastOrPresent
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "stockOrder")
    private List<StockOrderItem> items = new ArrayList<>();

    @Deprecated
    public StockOrder() {}

    public StockOrder(Long id, Branch branch) {
        this.id = id;
        this.branch = branch;
    }

    public StockOrder(@NotNull LocalDateTime date, @NotNull @Min(value = 0) Integer totalItems, @NotNull @Min(value = 0) Double totalPrice, @NotNull Branch branch, @NotNull User user) {
        this.date = date;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
        this.branch = branch;
        this.user = user;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<StockOrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockOrder that = (StockOrder) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StockOrder{" +
                "id=" + id +
                ", date=" + date +
                ", totalItems=" + totalItems +
                ", totalPrice=" + totalPrice +
                ", branch=" + branch +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void increaseTotals(StockOrderItem stockOrderItem) {
        this.totalItems += 1;
        this.totalPrice += stockOrderItem.getTotalPrice();
    }

    public void decreaseTotals(StockOrderItem stockOrderItem) {
        this.totalItems -= 1;
        this.totalPrice -= stockOrderItem.getTotalPrice();
    }
}
