package br.com.pwneo.estoque_back_end.models.users;

import br.com.pwneo.estoque_back_end.models.StockOrder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    private String registration;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<StockOrder> stockOrder = new HashSet<>();

    public Employee(Long id, String name, String email, String password, String registration) {
        super(id, name, email, password);
        this.registration = registration;
    }

    public Employee() {
        super();
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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
        Employee employee = (Employee) o;
        return Objects.equals(registration, employee.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registration);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "registration='" + registration + '\'' +
                '}';
    }
}
