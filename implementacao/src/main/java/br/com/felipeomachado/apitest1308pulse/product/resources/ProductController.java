package br.com.felipeomachado.apitest1308pulse.product.resources;

import br.com.felipeomachado.apitest1308pulse.product.entities.Product;
import br.com.felipeomachado.apitest1308pulse.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Product> find(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findById(id);

        if(optionalProduct.isPresent()){
            return ResponseEntity.ok().body(optionalProduct.get());
        }

        throw new EntityNotFoundException("Product not found!");
    }

    @GetMapping(value = "/barcode/{barcode}")
    public ResponseEntity<Product> findByBarcode(@PathVariable String barcode) {
        Optional<Product> optionalProduct = productService.findByBarcode(barcode);

        if(optionalProduct.isPresent()){
            return ResponseEntity.ok().body(optionalProduct.get());
        }

        throw new EntityNotFoundException("Product not found!");
    }

    @GetMapping(value = "/description/{description}")
    public ResponseEntity<Product> findByDescription(@PathVariable String description) {
        Optional<Product> optionalProduct = productService.findByDescription(description);

        if(optionalProduct.isPresent()){
            return ResponseEntity.ok().body(optionalProduct.get());
        }

        throw new EntityNotFoundException("Product not found!");
    }
}
