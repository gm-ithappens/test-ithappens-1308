package br.com.pwneo.estoque_back_end.resources.supports;

import br.com.pwneo.estoque_back_end.models.supports.Status;
import br.com.pwneo.estoque_back_end.services.supports.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/status")
public class StatusResource {

    @Autowired
    private StatusService service;

    @GetMapping
    public ResponseEntity<List<Status>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Status> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }
}
