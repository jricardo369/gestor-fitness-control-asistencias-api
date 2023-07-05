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

import com.control.fitness.application.port.in.TipoHistorialUseCase;
import com.control.fitness.domain.TipoHistorialEntity;

@RequestMapping("/tipos-historiales")
@RestController
public class TiposHistorialController {

	Logger log = LoggerFactory.getLogger(TiposHistorialController.class);

	@Autowired
	TipoHistorialUseCase TipoHistorialUseCase;

	@GetMapping
	public List<TipoHistorialEntity> obtenerTipoHistorials() {
		return TipoHistorialUseCase.obtenerTiposHistorials();
	}

	@PostMapping
	public void crearTipoHistorial(@RequestBody TipoHistorialEntity TipoHistorial) {
		TipoHistorialUseCase.crearTipoHistorial(TipoHistorial);
	}
	
	@PutMapping
	public void actualizarTipoHistorial(@RequestBody TipoHistorialEntity TipoHistorial) {
		TipoHistorialUseCase.crearTipoHistorial(TipoHistorial);
	}
	
	@DeleteMapping
	public void eliminarTipoHistorial(@RequestBody TipoHistorialEntity TipoHistorial) {
		TipoHistorialUseCase.eliminarTipoHistorial(TipoHistorial);
	}

}
