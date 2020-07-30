package br.com.pwneo.estoque_back_end.resources;

import br.com.pwneo.estoque_back_end.models.StockProduct;
import br.com.pwneo.estoque_back_end.services.StockProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping(value = "/subsidiary/{id}")
//    public ResponseEntity<Set<StockProduct>> findAllBySubsidiary(@PathVariable Long id) {
//        return ResponseEntity.ok().body(this.service.findBySubsidiary(id));
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StockProduct> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }
}
