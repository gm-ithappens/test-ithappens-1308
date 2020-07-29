package br.com.pwneo.estoque_back_end.resources;

import br.com.pwneo.estoque_back_end.models.Product;
import br.com.pwneo.estoque_back_end.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @GetMapping(value = "/barcode/{barCode}")
    public ResponseEntity<Product> findById(@PathVariable String  barCode) {
        return ResponseEntity.ok().body(this.service.findBybarCode(barCode));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        product = this.service.create(product);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok().body(this.service.update(id, product));
    }
}
