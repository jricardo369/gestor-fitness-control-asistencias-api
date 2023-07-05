package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.MovimientoEntity;
import com.control.fitness.domain.UsuarioEntity;

@Repository
public interface MovimientoJpa extends CrudRepository<MovimientoEntity, Serializable> {

	public List<MovimientoEntity> findAll();

	public List<MovimientoEntity> findByUsuario(UsuarioEntity paciente);

	public MovimientoEntity findByIdMovimiento(int Movimiento);

	@Query(value = "SELECT * FROM movimiento WHERE id_usuario = ?1 AND fecha BETWEEN ?2 AND ?3", nativeQuery = true)
	public List<MovimientoEntity> obtenerMovimientosDeClientePorFecha(int idPaciente, Date fechai, Date fechaf);
	
	@Query(value = "SELECT * FROM movimiento WHERE fecha BETWEEN ?1 AND ?2", nativeQuery = true)
	public List<MovimientoEntity> obtenerMovimientosPorFecha(Date fechai, Date fechaf);

	@Query(value = "SELECT " + "SUM(case when id_tipo_movimiento = 1 then monto else 0 end) - "
			+ "SUM(case when id_tipo_movimiento = 2 then monto else 0 end) as adeudo "
			+ "FROM movimiento WHERE id_usuario = ?1", nativeQuery = true)
	public BigDecimal obtenerAdeudo(int idPaciente);

	@Query(value = "SELECT sum(monto) FROM movimiento WHERE id_tipo_movimiento = ?1", nativeQuery = true)
	public BigDecimal obtenerSumaTipoMovimiento(int tipo);

	@Query(value = "SELECT sum(monto) FROM movimiento WHERE id_tipo_movimiento = ?1 and id_usuario = ?2 AND fecha BETWEEN ?3 AND ?4", nativeQuery = true)
	public BigDecimal obtenerSumaTipoMovimientoYPaciente(int tipo, int idPaciente,Date fechai, Date fechaf);
	
	@Query(value = "SELECT sum(monto) FROM movimiento WHERE id_tipo_movimiento = ?1 AND fecha BETWEEN ?2 AND ?3", nativeQuery = true)
	public BigDecimal obtenerSumaTipoMovimientoPorFecha(int tipo, Date fechai, Date fechaf);
	
	@Query(value = "SELECT sum(m.monto) FROM movimiento m  JOIN usuario p on p.id_usuario = m.id_usuario "
			+ "WHERE m.id_tipo_movimiento = ?1 AND m.fecha BETWEEN ?2 AND ?3", nativeQuery = true)
	public BigDecimal obtenerSumaTipoMovimientoyFecha(int tipo,Date fechai, Date fechaf);
	

}
