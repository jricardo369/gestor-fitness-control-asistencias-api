package com.control.fitness.adapter.out.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.TipoMovimientosPort;
import com.control.fitness.application.port.out.jpa.TipoMovimientoJpa;
import com.control.fitness.domain.TipoMovimientoEntity;

@Service
public class TipoMovimientoRepository implements TipoMovimientosPort {

	@Autowired
	TipoMovimientoJpa movJpa;

	@Override
	public List<TipoMovimientoEntity> obtenerTiposMovimientos() {
		return movJpa.findAll();
	}

	@Override
	public TipoMovimientoEntity obtenerMovimiento(int idTipo) {
		return movJpa.findByIdTipo(idTipo);
	}

	@Override
	public void crearTipoMovimiento(TipoMovimientoEntity mov) {
		movJpa.save(mov);
	}

	@Override
	public void actualizarTipoMovimiento(TipoMovimientoEntity mov) {
		movJpa.save(mov);
	}

	@Override
	public void eliminarTipoMovimiento(TipoMovimientoEntity mov) {
		movJpa.delete(mov);
	}

}
