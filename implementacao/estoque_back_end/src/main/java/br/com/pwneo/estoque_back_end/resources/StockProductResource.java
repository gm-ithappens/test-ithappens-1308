package br.com.pwneo.estoque_back_end.resources;

import br.com.pwneo.estoque_back_end.models.StockProduct;
import br.com.pwneo.estoque_back_end.models.dtos.StockProductNewDTO;
import br.com.pwneo.estoque_back_end.models.dtos.StockProductPriceDTO;
import br.com.pwneo.estoque_back_end.models.dtos.StockProductQuantityDTO;
import br.com.pwneo.estoque_back_end.services.StockProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/stockproducts")
public class StockProductResource {

    @Autowired
    private StockProductService service;

    @GetMapping
    public ResponseEntity<List<StockProduct>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StockProduct> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @GetMapping(value = "/stock/{id}")
    public ResponseEntity<Set<StockProduct>> findByStock(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findByStock(id));
    }

    @PostMapping
    public ResponseEntity<StockProduct> create(@RequestBody StockProductNewDTO stockProductNewDTO) {
        StockProduct stockProduct = this.service.create(stockProductNewDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(stockProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(stockProduct);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.delete(id) ? "Item removido de Produtos" : "Algo deu errado");
    }

    @PutMapping(value = "/quantity/{id}")
    public ResponseEntity<StockProduct> updateQuantity(@PathVariable Integer id, @RequestBody StockProductQuantityDTO stockProductQuantityDTO) {
        return ResponseEntity.ok().body(this.service.updateQuantity(id, stockProductQuantityDTO));
    }

    @PutMapping(value = "/price/{id}")
    public ResponseEntity<StockProduct> updatePrice(@PathVariable Integer id, @RequestBody StockProductPriceDTO stockProductPriceDTO) {
        return ResponseEntity.ok().body(this.service.updatePrice(id, stockProductPriceDTO));
    }
}
