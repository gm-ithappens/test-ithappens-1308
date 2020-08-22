package br.com.pwneo.estoque_back_end.repositories.users;

import br.com.pwneo.estoque_back_end.models.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByRg(String rg);

    Client findByCpf(String cpf);
}