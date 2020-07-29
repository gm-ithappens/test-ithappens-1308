package br.com.pwneo.estoque_back_end.models;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Employee extends Person {

    private String registration;

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
