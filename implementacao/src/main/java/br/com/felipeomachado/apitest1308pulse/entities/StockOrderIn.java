package br.com.felipeomachado.apitest1308pulse.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StockOrderIn extends StockOrder{
    @NotNull
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @Deprecated
    public StockOrderIn(){super();}

    public StockOrderIn(LocalDateTime date, Branch branch, User user, Provider provider, Integer totalItems, Double totalPrice) {
        super(date, totalItems, totalPrice, branch, user);
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return super.toString() + ", StockOrderIn{" +
                "provider=" + provider +
                '}';
    }
}
