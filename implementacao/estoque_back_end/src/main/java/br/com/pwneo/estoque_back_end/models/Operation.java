package br.com.pwneo.estoque_back_end.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-27
 * <p>
 * Classe Responsável por mapear a tabela auxiliar das operações de pedidos de estoque.
 */

@Entity
@Table(name = "operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    /*Constructors*/
    public Operation(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Operation() {
    }

    /*Getters and Setters*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*HashCode and Equals*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(id, operation.id) &&
                Objects.equals(description, operation.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
