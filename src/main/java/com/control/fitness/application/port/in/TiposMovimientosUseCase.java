package com.control.fitness.application.port.in;

import java.util.List;

import com.control.fitness.domain.TipoMovimientoEntity;

public interface TiposMovimientosUseCase {

	public List<TipoMovimientoEntity> obtenerTiposMovimientos(); 
	public TipoMovimientoEntity obtenerMovimiento(int idTipo);
	public void crearTipoMovimiento(TipoMovimientoEntity mov);
	public void actualizarTipoMovimiento(TipoMovimientoEntity mov);
	public void eliminarTipoMovimiento(TipoMovimientoEntity mov);
	
}
