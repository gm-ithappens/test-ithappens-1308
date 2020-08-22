package br.com.pwneo.estoque_back_end.resources;

import br.com.pwneo.estoque_back_end.models.StockOrder;
import br.com.pwneo.estoque_back_end.models.dtos.StockOrderNewDTO;
import br.com.pwneo.estoque_back_end.services.StockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/stockorders")
public class StockOrderResource {

    @Autowired
    private StockOrderService service;

    /*GET*/
    @GetMapping
    public ResponseEntity<List<StockOrder>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StockOrder> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @PostMapping
    public ResponseEntity<StockOrder> create(@RequestBody StockOrderNewDTO stockOrderNewDTO) {
        StockOrder stockOrder = this.service.create(stockOrderNewDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(stockOrder.getId()).toUri();
        return ResponseEntity.created(uri).body(stockOrder);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}