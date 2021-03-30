package com.estoque.Desafio.repository;

import com.estoque.Desafio.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
