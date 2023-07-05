package com.control.fitness.adapter.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.application.port.in.AsuetoUseCase;
import com.control.fitness.domain.Asueto;
import com.control.fitness.domain.AsuetoEntity;

@RequestMapping("/asuetos")
@RestController
public class AsuetoController {

	Logger log = LoggerFactory.getLogger(AsuetoController.class);

	@Autowired
	AsuetoUseCase asuetoUseCase;

	@GetMapping("{fecha}")
	public List<AsuetoEntity> obtenerAsueto(@PathVariable(value = "fecha") String fecha) {
		return asuetoUseCase.obtenerAsuetos(fecha);
	}

	@PostMapping("{tipo}")
	public void crearAsueto(@RequestBody Asueto asueto, @PathVariable(value = "tipo") int tipo) {
		asuetoUseCase.crearAsueto(asueto, tipo);
	}

	@DeleteMapping("{idAsueto}")
	public void eliminarAsueto(@PathVariable(value = "idAsueto") int idAsueto) {
		asuetoUseCase.eliminarAsueto(idAsueto);
	}

}
