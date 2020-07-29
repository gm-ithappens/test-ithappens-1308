package br.com.pwneo.estoque_back_end.resources;

import br.com.pwneo.estoque_back_end.models.OrderItem;
import br.com.pwneo.estoque_back_end.models.Product;
import br.com.pwneo.estoque_back_end.models.StockItem;
import br.com.pwneo.estoque_back_end.services.OrderItemService;
import br.com.pwneo.estoque_back_end.services.StockItemService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orderitems")
public class OrderItemResource {

    @Autowired
    private OrderItemService service;

    @GetMapping
    public ResponseEntity<List<OrderItem>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItem> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<OrderItem> findByProductId(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findByItemId(id));
    }

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem orderItem) {
        orderItem = this.service.create(orderItem);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderItem.getId()).toUri();
        return ResponseEntity.created(uri).body(orderItem);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        return ResponseEntity.ok().body(this.service.update(id, orderItem));
    }
}
