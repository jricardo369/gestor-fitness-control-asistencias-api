package com.control.fitness.adapter.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.application.port.in.MembresiasSociedadUseCase;
import com.control.fitness.domain.MembresiaSociedad;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST,
		RequestMethod.OPTIONS })
@RequestMapping("/membresias-sociedad")
@RestController
public class MembresiasSociedadController {

	@Autowired
	private MembresiasSociedadUseCase memSocUseCase;

	@GetMapping()
	public List<MembresiaSociedad> obtenerMembresiasSociedades() {
		return memSocUseCase.obtenerMembresiasSociedades();

	}

	@GetMapping("{idSociedad}")
	public List<MembresiaSociedad> obtenerMembresiasSociedadesDeSociedad(
			@PathVariable("idSociedad") int idSociedad) {
		return memSocUseCase.obtenerMembresiasSociedadesDe(idSociedad);
	}

}
