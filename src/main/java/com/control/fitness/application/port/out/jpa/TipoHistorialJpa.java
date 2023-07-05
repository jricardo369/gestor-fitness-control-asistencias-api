package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.TipoHistorialEntity;

@Repository
public interface TipoHistorialJpa extends CrudRepository<TipoHistorialEntity, Serializable> {

	public List<TipoHistorialEntity> findAll();
	
	public TipoHistorialEntity findByIdTipoHistorial(int idTipoHistorial);

}
