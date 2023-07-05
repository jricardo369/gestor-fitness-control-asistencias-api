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

import com.control.fitness.application.port.in.TiposMovimientosUseCase;
import com.control.fitness.domain.TipoMovimientoEntity;

@RequestMapping("/tipos-movimientos")
@RestController
public class TiposMovimientosController {

	Logger log = LoggerFactory.getLogger(TiposMovimientosController.class);

	@Autowired
	TiposMovimientosUseCase TipoMovimientoUseCase;

	@GetMapping
	public List<TipoMovimientoEntity> obtenerTipoMovimientos() {
		return TipoMovimientoUseCase.obtenerTiposMovimientos();
	}

	@PostMapping
	public void crearTipoMovimiento(@RequestBody TipoMovimientoEntity TipoMovimiento) {
		TipoMovimientoUseCase.crearTipoMovimiento(TipoMovimiento);
	}
	
	@PutMapping
	public void actualizarTipoMovimiento(@RequestBody TipoMovimientoEntity TipoMovimiento) {
		TipoMovimientoUseCase.crearTipoMovimiento(TipoMovimiento);
	}
	
	@DeleteMapping
	public void eliminarTipoMovimiento(@RequestBody TipoMovimientoEntity TipoMovimiento) {
		TipoMovimientoUseCase.eliminarTipoMovimiento(TipoMovimiento);
	}

}
