package com.estoque.Desafio.repository;

import com.estoque.Desafio.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
