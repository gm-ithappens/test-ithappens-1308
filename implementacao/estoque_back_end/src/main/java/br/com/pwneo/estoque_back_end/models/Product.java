package br.com.pwneo.estoque_back_end.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-29
 *
 * Classe responsável por mapear as informações dos produtos
 */

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bar_code", nullable = false)
    private String barCode;

    @Column(nullable = false, updatable = true)
    private String description;

    public Product(Integer id, String barCode, String description) {
        this.id = id;
        this.barCode = barCode;
        this.description = description;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(barCode, product.barCode) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barCode, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", barCode='" + barCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
