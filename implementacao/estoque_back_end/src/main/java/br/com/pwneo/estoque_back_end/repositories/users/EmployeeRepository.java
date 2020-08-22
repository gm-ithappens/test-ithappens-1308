package br.com.pwneo.estoque_back_end.repositories.users;

import br.com.pwneo.estoque_back_end.models.users.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByRegistration(String number);
}
