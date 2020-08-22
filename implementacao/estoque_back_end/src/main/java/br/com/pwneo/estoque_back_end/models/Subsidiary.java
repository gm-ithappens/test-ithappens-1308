package br.com.pwneo.estoque_back_end.models;

import br.com.pwneo.estoque_back_end.models.supports.Address;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-29
 *
 * Classe responsável por mapear as informações das filiais
 */

@Entity
@Table(name = "subsidiary")
public class Subsidiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Embedded
    private Address address = new Address();

    @OneToOne
    private Stock stock;

    public Subsidiary(Integer id, String name, String cnpj, String street, String number, String neighborhood, String city, String uf, Stock stock) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address.setStreet(street);
        this.address.setNumber(number);
        this.address.setNeighborhood(neighborhood);
        this.address.setCity(city);
        this.address.setUf(uf);
        this.stock = stock;
    }

    public Subsidiary() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subsidiary that = (Subsidiary) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(cnpj, that.cnpj) &&
                Objects.equals(address, that.address) &&
                Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnpj, address, stock);
    }

    @Override
    public String toString() {
        return "Subsidiary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", address=" + address +
                ", stock=" + stock +
                '}';
    }
}
