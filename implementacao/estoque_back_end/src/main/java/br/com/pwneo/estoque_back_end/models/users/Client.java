package br.com.pwneo.estoque_back_end.models.users;

import br.com.pwneo.estoque_back_end.models.StockOrder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client extends Person {

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 7, nullable = false)
    private String rg;

    @Column(nullable = false)
    private String street;

    @Column(length = 10, nullable = false)
    private String number;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String uf;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<StockOrder> stockOrder = new HashSet<>();

    public Client(Long id, String name, String email, String password, String cpf, String rg, String street, String number, String neighborhood, String city, String uf) {
        super(id, name, email, password);
        this.cpf = cpf;
        this.rg = rg;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.uf = uf;
    }

    public Client() {
        super();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Set<StockOrder> getStockOrder() {
        return stockOrder;
    }

    public void setStockOrder(Set<StockOrder> stockOrder) {
        this.stockOrder = stockOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(cpf, client.cpf) &&
                Objects.equals(rg, client.rg) &&
                Objects.equals(street, client.street) &&
                Objects.equals(number, client.number) &&
                Objects.equals(neighborhood, client.neighborhood) &&
                Objects.equals(city, client.city) &&
                Objects.equals(uf, client.uf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf, rg, street, number, neighborhood, city, uf);
    }

    @Override
    public String toString() {
        return "Client{" +
                "cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
