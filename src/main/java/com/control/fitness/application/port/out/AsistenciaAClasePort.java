package com.control.fitness.application.port.out;

import java.util.List;

import com.control.fitness.domain.AsistenciaAClaseEntity;

public interface AsistenciaAClasePort {
	
	public List<AsistenciaAClaseEntity> obtenerAsistenciaAClases(int idClase,String fecha);
	public void crearAsistenciaAClase(AsistenciaAClaseEntity clase);
	public void actualizarAsistenciaAClase(AsistenciaAClaseEntity clase);
	public void eliminarAsistenciaAClase(AsistenciaAClaseEntity clasae);
	public int obtenerNumeroDePersonasDeClase(int idClase, String fecha);
	public void obtenerAsistenciasPorFechasYClase(int idClase,List<String> fecha);

}
