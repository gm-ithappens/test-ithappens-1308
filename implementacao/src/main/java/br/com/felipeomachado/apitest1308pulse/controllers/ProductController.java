package br.com.felipeomachado.apitest1308pulse.controllers;

import br.com.felipeomachado.apitest1308pulse.entities.Product;
import br.com.felipeomachado.apitest1308pulse.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Product> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(productRepository.findById(id).get());
    }

    @GetMapping(value = "/barcode/{barcode}")
    public ResponseEntity<List<Product>> findByBarcode(@PathVariable String barcode) {
        System.out.print(barcode);
        return ResponseEntity.ok().body(productRepository.findByBarcode(barcode));
    }

    @GetMapping(value = "/description/{description}")
    public ResponseEntity<List<Product>> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok().body(productRepository.findByDescription(description));
    }
}
