package br.com.pwneo.estoque_back_end.services.users;

import br.com.pwneo.estoque_back_end.models.users.Employee;
import br.com.pwneo.estoque_back_end.repositories.users.EmployeeRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> findAll() {
        return this.repository.findAll();
    }

    public Employee findById(Integer id) {
        return this.repository.findById(id).get();
    }

    public Employee findByRegistration(String number) {
        return this.repository.findByRegistration(number);
    }

    public Employee create(Employee employee) {
        return this.repository.save(employee);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Employee update(Integer id, Employee employee) {
        try {
            Employee employeeTemp = repository.getOne(id);
            employeeTemp.setName(employee.getName());
            employeeTemp.setEmail(employee.getEmail());
            employeeTemp.setRegistration(employee.getRegistration());
            return repository.save(employeeTemp);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
