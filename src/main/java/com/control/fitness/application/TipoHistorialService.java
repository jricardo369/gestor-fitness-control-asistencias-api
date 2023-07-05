package com.control.fitness.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.in.TipoHistorialUseCase;
import com.control.fitness.application.port.out.TipoHistorialPort;
import com.control.fitness.domain.TipoHistorialEntity;

@Service
public class TipoHistorialService implements TipoHistorialUseCase {

	Logger log = LoggerFactory.getLogger(TipoHistorialService.class);

	@Autowired
	private TipoHistorialPort TipoHistorialPort;

	@Override
	public List<TipoHistorialEntity> obtenerTiposHistorials() {
		return TipoHistorialPort.obtenerTiposHistorials();
	}

	@Override
	public void crearTipoHistorial(TipoHistorialEntity TipoHistorial) {
		TipoHistorialPort.crearTipoHistorial(TipoHistorial);
	}

	@Override
	public void actualizarTipoHistorial(TipoHistorialEntity TipoHistorial) {
		TipoHistorialPort.actualizarTipoHistorial(TipoHistorial);
	}

	@Override
	public void eliminarTipoHistorial(TipoHistorialEntity TipoHistorial) {
		TipoHistorialPort.eliminarTipoHistorial(TipoHistorial);
	}

	@Override
	public TipoHistorialEntity obtenerHistorial(int idTipoHistorial) {
		return TipoHistorialPort.obtenerTipoHistorial(idTipoHistorial);
	}

}
