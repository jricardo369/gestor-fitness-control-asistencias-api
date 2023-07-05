package com.control.fitness.application.port.out;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.control.fitness.domain.MovimientoEntity;

public interface MovimientosPort {
	
	public List<MovimientoEntity> obtenerMovimientos(); 
	public List<MovimientoEntity> obtenerUltimosMovimientosDePaciente(int idPaciente); 
	public List<MovimientoEntity> obtenerMovimientosDePacientePorFecha(int idPaciente,Date fechai,Date fechaf);
	public List<MovimientoEntity> obtenerMovimientosPorFecha(Date fechai,Date fechaf);
	public MovimientoEntity obtenerMovimiento(int idMovimiento);
	public void crearMovimiento(MovimientoEntity mov);
	public void actualizarMovimiento(MovimientoEntity mov);
	public void eliminarMovimiento(MovimientoEntity mov);
	public BigDecimal obtenerAdeudo(int idCliente);

}
