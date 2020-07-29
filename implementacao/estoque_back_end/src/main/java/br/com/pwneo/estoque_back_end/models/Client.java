package br.com.pwneo.estoque_back_end.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client extends Person {

    private String cpf;
    private String rg;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String uf;

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
