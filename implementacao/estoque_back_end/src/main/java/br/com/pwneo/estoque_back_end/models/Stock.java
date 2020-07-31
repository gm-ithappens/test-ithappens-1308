package br.com.pwneo.estoque_back_end.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-29
 *
 * Classe responsável por mapear as informações dos estoques
 */

@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    public Stock(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Stock() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id) &&
                Objects.equals(description, stock.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
