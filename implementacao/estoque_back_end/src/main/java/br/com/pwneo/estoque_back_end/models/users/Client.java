package br.com.pwneo.estoque_back_end.models.users;

import br.com.pwneo.estoque_back_end.models.StockOrder;
import br.com.pwneo.estoque_back_end.models.supports.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-28
 *
 * Classe responsável por mapear as informações do cliente.
 */

@Entity
@Table(name = "client")
public class Client extends Person {

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 7, nullable = false, unique = true)
    private String rg;

    @Embedded
    private Address address = new Address();


    public Client(Integer id, String name, String email, String password, String cpf, String rg, String street, String number, String neighborhood, String city, String uf) {
        super(id, name, email, password);
        this.cpf = cpf;
        this.rg = rg;
        this.address.setStreet(street);
        this.address.setNumber(number);
        this.address.setNeighborhood(neighborhood);
        this.address.setCity(city);
        this.address.setUf(uf);
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(cpf, client.cpf) &&
                Objects.equals(rg, client.rg) &&
                Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf, rg, address);
    }

    @Override
    public String toString() {
        return "Client{" +
                "cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", address=" + address +
                '}';
    }
}
