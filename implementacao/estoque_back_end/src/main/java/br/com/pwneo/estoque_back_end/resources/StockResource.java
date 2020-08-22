package br.com.pwneo.estoque_back_end.resources;

import br.com.pwneo.estoque_back_end.models.Stock;
import br.com.pwneo.estoque_back_end.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stocks")
public class StockResource {

    @Autowired
    private StockService service;

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Stock> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }
}
