package com.control.fitness.adapter.out.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.AsistenciaAClasePort;
import com.control.fitness.application.port.out.jpa.AsistenciaAClaseJpa;
import com.control.fitness.domain.AsistenciaAClaseEntity;


@Service
public class AsistenciaAClaseRepository implements AsistenciaAClasePort {

	@Autowired
	AsistenciaAClaseJpa claseJpa;

	@Override
	public void crearAsistenciaAClase(AsistenciaAClaseEntity ac) {
		claseJpa.save(ac);
	}

	@Override
	public void actualizarAsistenciaAClase(AsistenciaAClaseEntity ac) {
		claseJpa.save(ac);
	}

	@Override
	public void eliminarAsistenciaAClase(AsistenciaAClaseEntity ac) {
		claseJpa.delete(ac);
	}

	@Override
	public List<AsistenciaAClaseEntity> obtenerAsistenciaAClases(int idClase,String fecha) {
		return claseJpa.findAll();
	}
	
	@Override
	public int obtenerNumeroDePersonasDeClase(int idClase, String fecha){
		return claseJpa.obtenerNumeroDePersonasDeClase(fecha, idClase);
	}

	@Override
	public void obtenerAsistenciasPorFechasYClase(int idClase, List<String> fechas) {
		List<AsistenciaAClaseEntity> fechasD = claseJpa.obtenerAsistenciasPorFechasYClase(idClase, fechas);
		claseJpa.deleteAll(fechasD);
	}

}
