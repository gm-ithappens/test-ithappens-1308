package com.springsql.negocio.dao;

import com.springsql.negocio.model.Estoque;
import org.springframework.data.repository.CrudRepository;

public interface EstoqueDao extends CrudRepository<Estoque,Integer>{
}
