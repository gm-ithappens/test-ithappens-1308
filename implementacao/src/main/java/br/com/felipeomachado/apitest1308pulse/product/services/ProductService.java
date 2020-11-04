package br.com.felipeomachado.apitest1308pulse.product.services;

import br.com.felipeomachado.apitest1308pulse.product.entities.Product;
import br.com.felipeomachado.apitest1308pulse.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode);
    }

    public List<Product> findByDescription(String description) {
        return productRepository.findByDescription(description);
    }
}
