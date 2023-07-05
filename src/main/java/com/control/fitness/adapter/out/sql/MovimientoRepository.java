package com.control.fitness.adapter.out.sql;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.MovimientosPort;
import com.control.fitness.application.port.out.jpa.MovimientoJpa;
import com.control.fitness.domain.MovimientoEntity;
import com.control.fitness.domain.UsuarioEntity;

@Service
public class MovimientoRepository implements MovimientosPort {

	@Autowired
	MovimientoJpa movJpa;
	
	@Override
	public List<MovimientoEntity> obtenerMovimientos(){
		return movJpa.findAll();
	}

	@Override
	public List<MovimientoEntity> obtenerUltimosMovimientosDePaciente(int idPaciente) {
		UsuarioEntity p = new UsuarioEntity();
		p.setIdUsuario(idPaciente);
		return movJpa.findByUsuario(p);
	}

	@Override
	public List<MovimientoEntity> obtenerMovimientosDePacientePorFecha(int idPaciente, Date fechai, Date fechaf) {
		return movJpa.obtenerMovimientosDeClientePorFecha(idPaciente, fechai, fechaf);
	}
	
	@Override
	public List<MovimientoEntity> obtenerMovimientosPorFecha(Date fechai, Date fechaf) {
		return movJpa.obtenerMovimientosPorFecha(fechai, fechaf);
	}
	
	@Override
	public MovimientoEntity obtenerMovimiento(int idMovimiento){
		return movJpa.findByIdMovimiento(idMovimiento);
	}

	@Override
	public void crearMovimiento(MovimientoEntity mov) {
		movJpa.save(mov);
	}

	@Override
	public void actualizarMovimiento(MovimientoEntity mov) {
		movJpa.save(mov);
	}

	@Override
	public void eliminarMovimiento(MovimientoEntity mov) {
		movJpa.delete(mov);
	}

	@Override
	public BigDecimal obtenerAdeudo(int idCliente) {
		return movJpa.obtenerAdeudo(idCliente);
	}

}
