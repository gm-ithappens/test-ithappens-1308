package br.com.pwneo.estoque_back_end.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Paulo Weskley de Almeida Ferreira
 * @date 2020-07-29
 *
 * Classe responsável por mapear a Entidade responsável pelos tipos de pagamentos.
 */

@Entity
@Table(name = "payment_method")
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public PaymentMethod(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public PaymentMethod() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}