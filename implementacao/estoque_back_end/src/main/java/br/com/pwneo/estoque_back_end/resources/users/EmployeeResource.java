package br.com.pwneo.estoque_back_end.resources.users;

import br.com.pwneo.estoque_back_end.models.users.Employee;
import br.com.pwneo.estoque_back_end.services.users.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeResource {

    @Autowired
    private EmployeeService service;

    /*GET*/
    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @GetMapping(value = "/registration/{number}")
    public ResponseEntity<Employee> findByRegistration(@PathVariable String number) {
        return ResponseEntity.ok().body(this.service.findByRegistration(number));
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee = this.service.create(employee);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).body(employee);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee employee) {
        return ResponseEntity.ok().body(this.service.update(id, employee));
    }
}