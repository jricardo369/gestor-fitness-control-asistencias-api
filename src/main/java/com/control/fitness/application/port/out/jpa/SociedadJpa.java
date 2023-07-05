package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.SociedadEntity;

@Repository
public interface SociedadJpa extends CrudRepository<SociedadEntity, Serializable> {

	public List<SociedadEntity> findAll();
	
	public SociedadEntity findBySociedad(int idSociedad);

	@Query(value = "SELECT * FROM movimiento WHERE id_cliente = ?1 AND fecha BETWEEN ?2 AND ?3", nativeQuery = true)
	public List<SociedadEntity> obtenerMovimientosDeClientePorFecha(int idCliente, Date fechai, Date fechaf);

	@Query(value = "SELECT " + "SUM(case when id_tipo_movimiento = 1 then monto else 0 end) - "
			+ "SUM(case when id_tipo_movimiento = 2 then monto else 0 end) as adeudo "
			+ "FROM movimiento WHERE id_cliente = ?1", nativeQuery = true)
	public BigDecimal obtenerAdeudo(int idCliente);

}
