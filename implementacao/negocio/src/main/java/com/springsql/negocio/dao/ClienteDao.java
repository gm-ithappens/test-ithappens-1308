package com.springsql.negocio.dao;

import com.springsql.negocio.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente,Integer>{
}
