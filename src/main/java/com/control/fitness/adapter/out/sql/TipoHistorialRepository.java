package com.control.fitness.adapter.out.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.TipoHistorialPort;
import com.control.fitness.application.port.out.jpa.TipoHistorialJpa;
import com.control.fitness.domain.TipoHistorialEntity;

@Service
public class TipoHistorialRepository implements TipoHistorialPort {

	@Autowired
	TipoHistorialJpa tipoHisJpa;

	@Override
	public List<TipoHistorialEntity> obtenerTiposHistorials() {
		return tipoHisJpa.findAll();
	}

	@Override
	public TipoHistorialEntity obtenerTipoHistorial(int idTipoHistorial) {
		return tipoHisJpa.findByIdTipoHistorial(idTipoHistorial);
	}

	@Override
	public void crearTipoHistorial(TipoHistorialEntity mov) {
		tipoHisJpa.save(mov);
	}

	@Override
	public void actualizarTipoHistorial(TipoHistorialEntity mov) {
		tipoHisJpa.save(mov);
	}

	@Override
	public void eliminarTipoHistorial(TipoHistorialEntity mov) {
		tipoHisJpa.delete(mov);
	}

}
