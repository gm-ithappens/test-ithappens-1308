package br.com.pwneo.estoque_back_end.resources;

import br.com.pwneo.estoque_back_end.models.OrderItem;
import br.com.pwneo.estoque_back_end.models.dtos.OrderItemDTO;
import br.com.pwneo.estoque_back_end.models.dtos.OrderItemQuantityDTO;
import br.com.pwneo.estoque_back_end.models.dtos.OrderItemStatusDTO;
import br.com.pwneo.estoque_back_end.services.OrderItemService;
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
    public ResponseEntity<OrderItem> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItem orderItem = this.service.create(orderItemDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderItem.getId()).toUri();
        return ResponseEntity.created(uri).body(orderItem);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/quantity/{id}")
    public ResponseEntity<OrderItem> updateQuantity(@PathVariable Integer id, @RequestBody OrderItemQuantityDTO orderItemQuantityDTO) {
        return ResponseEntity.ok().body(this.service.updateQuantity(id, orderItemQuantityDTO));
    }

    @PutMapping(value = "/status/{id}")
    public ResponseEntity<OrderItem> updateStatus(@PathVariable Integer id, @RequestBody OrderItemStatusDTO orderItemStatusDTO) {
        return ResponseEntity.ok().body(this.service.updateStatus(id, orderItemStatusDTO));
    }
}
