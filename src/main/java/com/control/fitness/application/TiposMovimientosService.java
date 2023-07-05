package com.control.fitness.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.in.TiposMovimientosUseCase;
import com.control.fitness.application.port.out.TipoMovimientosPort;
import com.control.fitness.domain.TipoMovimientoEntity;

@Service
public class TiposMovimientosService implements TiposMovimientosUseCase {

	Logger log = LoggerFactory.getLogger(TiposMovimientosService.class);

	@Autowired
	private TipoMovimientosPort movPort;

	@Override
	public List<TipoMovimientoEntity> obtenerTiposMovimientos() {
		return movPort.obtenerTiposMovimientos();
	}

	@Override
	public TipoMovimientoEntity obtenerMovimiento(int idTipo) {
		return movPort.obtenerMovimiento(idTipo);
	}

	@Override
	public void crearTipoMovimiento(TipoMovimientoEntity mov) {
		movPort.crearTipoMovimiento(mov);
	}

	@Override
	public void actualizarTipoMovimiento(TipoMovimientoEntity mov) {
		movPort.actualizarTipoMovimiento(mov);
	}

	@Override
	public void eliminarTipoMovimiento(TipoMovimientoEntity mov) {
		movPort.eliminarTipoMovimiento(mov);
	}

}
