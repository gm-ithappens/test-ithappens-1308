package br.com.pwneo.estoque_back_end.repositories;

import br.com.pwneo.estoque_back_end.models.Client;
import br.com.pwneo.estoque_back_end.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByRegistration(String number);
}
