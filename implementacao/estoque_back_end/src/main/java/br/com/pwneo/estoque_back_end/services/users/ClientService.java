package br.com.pwneo.estoque_back_end.services.users;

import br.com.pwneo.estoque_back_end.models.users.Client;
import br.com.pwneo.estoque_back_end.repositories.users.ClientRepository;
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

    public Client findById(Integer id) {
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

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Client update(Integer id, Client client) {
        try {
            Client clientTemp = repository.getOne(id);
            clientTemp.setName(client.getName());
            clientTemp.setEmail(client.getEmail());
            clientTemp.setCpf(client.getCpf());
            clientTemp.setRg(client.getRg());
            clientTemp.getAddress().setStreet(client.getAddress().getStreet());
            clientTemp.getAddress().setNumber(client.getAddress().getNumber());
            clientTemp.getAddress().setNeighborhood(client.getAddress().getNeighborhood());
            clientTemp.getAddress().setCity(client.getAddress().getCity());
            clientTemp.getAddress().setUf(client.getAddress().getUf());
            return repository.save(clientTemp);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
