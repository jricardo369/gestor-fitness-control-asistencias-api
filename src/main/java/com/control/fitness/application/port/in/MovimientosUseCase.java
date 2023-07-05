package com.control.fitness.application.port.in;

import java.util.Date;
import java.util.List;

import com.control.fitness.domain.Adeudo;
import com.control.fitness.domain.Movimiento;
import com.control.fitness.domain.MovimientoEntity;

public interface MovimientosUseCase {

	public List<MovimientoEntity> obtenerUltimosMovimientosDePaciente(int idPaciente); 
	public List<MovimientoEntity> obtenerMovimientosDePacientePorFecha(int idPaciente,Date fechai,Date fechaf);
	public List<MovimientoEntity> obtenerMovimientosPorFecha(Date fechai,Date fechaf);
	public MovimientoEntity obtenerMovimiento(int idMovimiento);
	public void crearMovimiento(Movimiento mov);
	public void actualizarMovimiento(Movimiento mov);
	public void eliminarMovimiento(int idMov);
	public Adeudo obtenerAdeudo(int idCliente);
	
}
