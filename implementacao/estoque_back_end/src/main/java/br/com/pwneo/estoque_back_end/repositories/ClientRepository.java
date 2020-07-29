package br.com.pwneo.estoque_back_end.repositories;

import br.com.pwneo.estoque_back_end.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByNameContains(String name);

    Client findByRg(String rg);

    Client findByCpf(String cpf);

    Client findByEmail(String email);
}
