package br.com.pwneo.estoque_back_end.models.supports;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-30
 *
 * Classe auxiliar que agrupa as informações de endereço para Client e Subsidiary.
 */

@Embeddable
public class Address {

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
}
