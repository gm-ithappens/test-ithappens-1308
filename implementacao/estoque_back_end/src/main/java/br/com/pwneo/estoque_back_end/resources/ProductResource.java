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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @GetMapping(value = "/barcode/{number}")
    public ResponseEntity<Product> findByBarcode(@PathVariable String number) {
        return ResponseEntity.ok().body(this.service.findBybarCode(number));
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
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.delete(id) ? "Item removido de Produtos" : "Algo deu errado");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {
        return ResponseEntity.ok().body(this.service.update(id, product));
    }
}
