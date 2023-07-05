package com.control.fitness.application.port.in;

import java.util.List;

import com.control.fitness.domain.AsistenciaAClase;
import com.control.fitness.domain.AsistenciaAClaseEntity;
import com.control.fitness.domain.AsistenciaAClaseUsuarios;

public interface AsistenciaAClaseUseCase {
     
	public void agregarUsuarioAClase(AsistenciaAClase clase);
	public AsistenciaAClaseUsuarios obtenerAsistenciaAClases(int idClase,String fecha);
	public List<AsistenciaAClaseUsuarios> obtenerAsistenciaAClasesPorSemana(String fecha);
	public void crearAsistenciaAClase(AsistenciaAClaseEntity clase);
	public void actualizarAsistenciaAClase(AsistenciaAClaseEntity clase);
	public void eliminarAsistenciaAClase(AsistenciaAClaseEntity clasae);
	public void crearOActualizarSiAsistioAClase(String usuario);
	
}
