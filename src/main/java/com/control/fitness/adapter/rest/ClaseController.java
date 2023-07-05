package com.control.fitness.adapter.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.application.port.in.ClaseUseCase;
import com.control.fitness.domain.ClaseEntity;

@RequestMapping("/clases")
@RestController
public class ClaseController {

	Logger log = LoggerFactory.getLogger(ClaseController.class);

	@Autowired
	ClaseUseCase claseUseCase;
	
	@GetMapping
	public List<ClaseEntity> obtenerClase() {
		return claseUseCase.obtenerClases();
	}
	
//	@GetMapping
//	public List<ClaseEntity> obtenerClase(@RequestBody ClaseEntity clase) {
//		return claseUseCase.obtenerClases();
//	}

	@PostMapping
	public void crearClase(@RequestBody ClaseEntity clase) {
		claseUseCase.crearClase(clase);
	}
	
	@PutMapping
	public void actulizarClase(@RequestBody ClaseEntity clase) {
		claseUseCase.actualizarClase(clase);
	}
	
	@DeleteMapping
	public void eliminarClase(@RequestBody ClaseEntity clase) {
		claseUseCase.eliminarClase(clase);
	}

}
