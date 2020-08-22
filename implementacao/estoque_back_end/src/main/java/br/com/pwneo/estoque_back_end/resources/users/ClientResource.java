package br.com.pwneo.estoque_back_end.resources.users;

import br.com.pwneo.estoque_back_end.models.users.Client;
import br.com.pwneo.estoque_back_end.services.users.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    /*GET*/
    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @GetMapping(value = "/cpf/{number}")
    public ResponseEntity<Client> findByCpf(@PathVariable String number) {
        return ResponseEntity.ok().body(this.service.findByCpf(number));
    }

    @GetMapping(value = "/rg/{number}")
    public ResponseEntity<Client> findByRg(@PathVariable String number) {
        return ResponseEntity.ok().body(this.service.findByRg(number));
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        client = this.service.create(client);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody Client client) {
        return ResponseEntity.ok().body(this.service.update(id, client));
    }
}