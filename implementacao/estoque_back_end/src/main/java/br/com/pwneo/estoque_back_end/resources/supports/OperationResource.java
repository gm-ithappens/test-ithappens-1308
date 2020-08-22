package br.com.pwneo.estoque_back_end.resources.supports;

import br.com.pwneo.estoque_back_end.models.supports.Operation;
import br.com.pwneo.estoque_back_end.services.supports.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/operations")
public class OperationResource {

    @Autowired
    private OperationService service;

    @GetMapping
    public ResponseEntity<List<Operation>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Operation> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }
}
