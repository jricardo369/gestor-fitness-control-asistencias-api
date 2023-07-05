package com.control.fitness.adapter.rest;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.application.port.in.MovimientosUseCase;
import com.control.fitness.domain.Adeudo;
import com.control.fitness.domain.Movimiento;
import com.control.fitness.domain.MovimientoEntity;

@RequestMapping("/movimientos")
@RestController
public class MovimientosController {

	Logger log = LoggerFactory.getLogger(MovimientosController.class);

	@Autowired
	MovimientosUseCase movUseCase;

	@GetMapping("por-paciente/{idPaciente}")
	public List<MovimientoEntity> obtenerUltimosMovimientosDeCliente(@PathVariable("idPaciente") int idPaciente) {
		return movUseCase.obtenerUltimosMovimientosDePaciente(idPaciente);
	}

	@GetMapping("por-paciente/{idPaciente}/por-fecha")
	public List<MovimientoEntity> obtenerMovimientosDeClientePorFecha(@RequestParam("fechai") Date fechai,
			@RequestParam("fechaf") Date fechaf,@PathVariable("idPaciente") int idPaciente) {
		return movUseCase.obtenerMovimientosDePacientePorFecha(idPaciente, fechai, fechaf);
	}
	
	@GetMapping("por-fecha")
	public List<MovimientoEntity> obtenerMovimientosPorFecha(@RequestParam("fechai") Date fechai,
			@RequestParam("fechaf") Date fechaf) {
		return movUseCase.obtenerMovimientosPorFecha(fechai, fechaf);
	}

	@GetMapping("{idCliente}/adeudo")
	public Adeudo obtenerAdeudoCliente(@PathVariable("idCliente") int idCliente) {
		return movUseCase.obtenerAdeudo(idCliente);
	}
	
	@GetMapping("{idMovimiento}")
	public MovimientoEntity obtenerMovimiento(@PathVariable("idMovimiento") int idMovimiento) {
		return movUseCase.obtenerMovimiento(idMovimiento);
	}

	@PostMapping
	public void insertarMovimiento(@RequestBody Movimiento mov) {
		movUseCase.crearMovimiento(mov);
	}

	@PutMapping
	public void actulizarMovimiento(@RequestBody Movimiento mov) {
		movUseCase.actualizarMovimiento(mov);
	}

	@DeleteMapping("{idMovimiento}")
	public void eliminarMovimiento(@PathVariable("idMovimiento") int idMovimiento) {
		movUseCase.eliminarMovimiento(idMovimiento);
	}

}
