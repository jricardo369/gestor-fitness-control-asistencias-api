package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.MembresiaSociedadEntity;

@Repository
public interface MembresiasSociedadJpa extends CrudRepository<MembresiaSociedadEntity, Serializable> {

	public List<MembresiaSociedadEntity> findAll();
	
	@Query(value = "SELECT * FROM membresia_sociedad_entity WHERE id_sociedad = ?1", nativeQuery = true)
	public List<MembresiaSociedadEntity> buscarPorIdSoc(int idSociedad);

}
