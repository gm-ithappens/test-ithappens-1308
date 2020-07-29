package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.Client;
import br.com.pwneo.estoque_back_end.repositories.ClientRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll() {
        return this.repository.findAll();
    }

    public Client findById(Long id) {
        return this.repository.findById(id).get();
    }

    public Client findByCpf(String cpf) {
        return this.repository.findByCpf(cpf);
    }

    public Client findByRg(String rg) {
        return this.repository.findByRg(rg);
    }

    public Client create(Client client) {
        return this.repository.save(client);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Client update(Long id, Client client) {
        try {
            Client clientTemp = repository.getOne(id);
            clientTemp.setName(client.getName());
            clientTemp.setEmail(client.getEmail());
            clientTemp.setCpf(client.getCpf());
            clientTemp.setRg(client.getRg());
            clientTemp.setStreet(client.getStreet());
            clientTemp.setNumber(client.getNumber());
            clientTemp.setNeighborhood(client.getNeighborhood());
            clientTemp.setCity(client.getCity());
            clientTemp.setUf(client.getUf());
            return repository.save(clientTemp);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
