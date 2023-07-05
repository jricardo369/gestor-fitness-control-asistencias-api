package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.AsuetoEntity;

@Repository
public interface AsuetoJpa extends CrudRepository<AsuetoEntity, Serializable> {

	public List<AsuetoEntity> findAll();
	
	public AsuetoEntity findByIdAsueto(int idAsueto);
	
	@Query(value = "SELECT * FROM asueto a WHERE a.fecha = ?1", nativeQuery = true)
	public List<AsuetoEntity> findByFecha(Date fecha);
	
	@Query(value = "SELECT count(id_asueto) FROM asueto  a WHERE a.fecha = ?1 AND a.id_clase = 0", nativeQuery = true)
	public int esAsuetoGeneral(Date fecha);
	
	@Query(value = "SELECT count(id_asueto) FROM asueto  a WHERE a.fecha = ?1 AND a.id_clase = ?2", nativeQuery = true)
	public int asuetoDuplicado(String fecha, int idClase);
 
}
