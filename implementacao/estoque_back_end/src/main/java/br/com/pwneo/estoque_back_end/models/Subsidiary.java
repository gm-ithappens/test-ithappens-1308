package br.com.pwneo.estoque_back_end.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "subsidiary")
public class Subsidiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnpj;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String uf;

    public Subsidiary(Long id, String name, String cnpj, String street, String number, String neighborhood, String city, String uf) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.uf = uf;
    }

    public Subsidiary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
        Subsidiary that = (Subsidiary) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(cnpj, that.cnpj) &&
                Objects.equals(street, that.street) &&
                Objects.equals(number, that.number) &&
                Objects.equals(neighborhood, that.neighborhood) &&
                Objects.equals(city, that.city) &&
                Objects.equals(uf, that.uf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnpj, street, number, neighborhood, city, uf);
    }

    @Override
    public String toString() {
        return "Subsidiary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + uf + '\'' +
                '}';
    }
}
