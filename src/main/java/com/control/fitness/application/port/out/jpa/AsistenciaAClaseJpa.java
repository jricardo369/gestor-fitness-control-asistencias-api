package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.control.fitness.domain.AsistenciaAClaseEntity;

@Repository
public interface AsistenciaAClaseJpa extends CrudRepository<AsistenciaAClaseEntity, Serializable> {

	public List<AsistenciaAClaseEntity> findAll();
	
	@Query(value = "SELECT count(id_clase) FROM asistencia_a_clase  a WHERE a.fecha = ?1 AND a.id_clase = ?2", nativeQuery = true)
	public int obtenerNumeroDePersonasDeClase(String fecha, int idClase);

	@Query(value = "SELECT * FROM asistencia_a_clase WHERE id_clase = ?1 AND fecha IN ?2", nativeQuery = true)
	public List<AsistenciaAClaseEntity> obtenerAsistenciasPorFechasYClase(int idClase,List<String> fecha);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM AsistenciaAClaseEntity WHERE idClase = :idClase AND fecha IN :fechas")
	public void el(@Param("idClase")int idClase,@Param("fechas")List<Date> fechas);
	
}
