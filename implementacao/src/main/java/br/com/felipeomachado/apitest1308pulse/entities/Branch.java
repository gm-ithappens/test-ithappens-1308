package br.com.felipeomachado.apitest1308pulse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @CNPJ
    @Column(unique = true)
    private String cnpj;

    @NotBlank
    private String name;

    @PastOrPresent
    private LocalDateTime createdAt = LocalDateTime.now();

    @PastOrPresent
    private LocalDateTime updatedAt = LocalDateTime.now();

    @JsonIgnore
    @OneToMany(mappedBy = "branch")
    private List<StockOrder> orders = new ArrayList<>();

    @Deprecated
    public Branch(){}

    public Branch(Long id) {
        this.id = id;
    }

    public Branch(Long id, String cnpj) {
        this.id = id;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public List<StockOrder> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
