package com.control.fitness.adapter.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.application.port.in.SociedadUseCase;
import com.control.fitness.domain.SociedadEntity;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST,RequestMethod.OPTIONS})
@RequestMapping("/sociedades")
@RestController
public class SociedadesController {
	
	Logger log = LoggerFactory.getLogger(SociedadesController.class);
	
	@Autowired
	private SociedadUseCase socUseCase;
	
	@GetMapping()
	public List<SociedadEntity> obtenerSociedades() { 
		return socUseCase.obtenerSociedades();
	}
	
	@PostMapping()
	public void crearSociedad(@RequestBody SociedadEntity sociedad) { 
		socUseCase.crearSociedad(sociedad);
	}
	
	@PutMapping()
	public void actualizarSociedad(@RequestBody SociedadEntity sociedad) { 
		socUseCase.actualizarSociedad(sociedad);
	}
	
	@DeleteMapping()
	public void eliminarSociedad(@RequestBody SociedadEntity sociedad) { 
		socUseCase.eliminarSociedad(sociedad);
	}

}
