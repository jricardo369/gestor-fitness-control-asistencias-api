package com.control.fitness.adapter.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.application.port.in.AsistenciaAClaseUseCase;
import com.control.fitness.domain.AsistenciaAClase;
import com.control.fitness.domain.AsistenciaAClaseEntity;
import com.control.fitness.domain.AsistenciaAClaseUsuarios;

@RequestMapping("/asistencia-a-clases")
@RestController
public class AsistenciaAClaseController {

	Logger log = LoggerFactory.getLogger(AsistenciaAClaseController.class);

	@Autowired
	AsistenciaAClaseUseCase acUseCase;

	@GetMapping("{idClase}/{fecha}")
	public AsistenciaAClaseUsuarios obtenerAsitenciaAClasePorClase(@PathVariable(value = "idClase") int idClase,@PathVariable(value="fecha") String fecha) {
		return acUseCase.obtenerAsistenciaAClases(idClase,fecha);
	}
	
	@GetMapping("{fecha}")
	public List<AsistenciaAClaseUsuarios> obtenerAsitenciaDeSemana(@PathVariable(value="fecha") String fecha) {
		return acUseCase.obtenerAsistenciaAClasesPorSemana(fecha);
	}

	@PostMapping
	public void agregarUsuarioAClase(@RequestBody AsistenciaAClase asistenciaAClase) {
		acUseCase.agregarUsuarioAClase(asistenciaAClase);
	}
	
	@PutMapping
	public void actualizarAsistenciaAClase(@RequestBody AsistenciaAClaseEntity ac) {
		acUseCase.actualizarAsistenciaAClase(ac);
	}
	
	@DeleteMapping
	public void eliminarAsistenciaAClase(@RequestBody AsistenciaAClaseEntity ac) {
		acUseCase.eliminarAsistenciaAClase(ac);
	}
	
	@PutMapping("{usuario}")
	public void crearOActualizarSiAsistioAClase(@PathVariable(value = "usuario") String usuario) {
		acUseCase.crearOActualizarSiAsistioAClase(usuario);
	}

}
