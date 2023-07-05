package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.ClaseEntity;

@Repository
public interface ClaseJpa extends CrudRepository<ClaseEntity, Serializable> {

	public List<ClaseEntity> findAll();
	
	public ClaseEntity findByIdClase(int idClase);
	
	@Query(value = "SELECT a.id_clase, c.nombre FROM asistencia_a_clase  a JOIN clase c on a.id_clase = c.id_clase WHERE a.fecha = ? AND a.id_usuario = ?", nativeQuery = true)
	public ClaseEntity estaEnClaseEnFecha(String fecha, int idUsuario);
 
}
