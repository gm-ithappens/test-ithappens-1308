package br.com.pwneo.estoque_back_end.resources;

import br.com.pwneo.estoque_back_end.models.Subsidiary;
import br.com.pwneo.estoque_back_end.services.SubsidiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/subsidiaries")
public class SubsidiaryResource {

    @Autowired
    private SubsidiaryService service;

    @GetMapping
    public ResponseEntity<List<Subsidiary>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Subsidiary> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @GetMapping(value = "/cnpj/{code}")
    public ResponseEntity<Subsidiary> findById(@PathVariable String code) {
        return ResponseEntity.ok().body(this.service.findByCnpj(code));
    }
}
