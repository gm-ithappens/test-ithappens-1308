package com.springsql.negocio.dao;

import com.springsql.negocio.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoDao extends CrudRepository<Produto,Integer>{
}
