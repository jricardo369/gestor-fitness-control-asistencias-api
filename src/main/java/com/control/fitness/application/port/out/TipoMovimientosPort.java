package com.control.fitness.application.port.out;

import java.util.List;

import com.control.fitness.domain.TipoMovimientoEntity;

public interface TipoMovimientosPort {
	
	public List<TipoMovimientoEntity> obtenerTiposMovimientos(); 
	public TipoMovimientoEntity obtenerMovimiento(int idTipo);
	public void crearTipoMovimiento(TipoMovimientoEntity mov);
	public void actualizarTipoMovimiento(TipoMovimientoEntity mov);
	public void eliminarTipoMovimiento(TipoMovimientoEntity mov);

}
