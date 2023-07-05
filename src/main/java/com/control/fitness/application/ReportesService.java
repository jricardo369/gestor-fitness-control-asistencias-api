package com.control.fitness.application;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.in.ReportesUseCase;
import com.control.fitness.application.port.out.jpa.MovimientoJpa;
import com.control.fitness.domain.ReporteMovimientos;

@Service
public class ReportesService implements ReportesUseCase {

	Logger log = LoggerFactory.getLogger(ReportesService.class);

	
	@Autowired
	MovimientoJpa movJpa;
	
	@Override
	public ReporteMovimientos reporteMovDePaciente(int idPaciente,Date fechai, Date fechaf){
		ReporteMovimientos r = new ReporteMovimientos();
		BigDecimal cargos = movJpa.obtenerSumaTipoMovimientoYPaciente(1, idPaciente,fechai,fechaf) == null ? BigDecimal.ZERO : movJpa.obtenerSumaTipoMovimientoYPaciente(1, idPaciente,fechai,fechaf);
		BigDecimal abonos = movJpa.obtenerSumaTipoMovimientoYPaciente(2, idPaciente,fechai,fechaf) == null ? BigDecimal.ZERO : movJpa.obtenerSumaTipoMovimientoYPaciente(2, idPaciente,fechai,fechaf);
		BigDecimal saldo = cargos.subtract(abonos);
		r.setSaldo(saldo);
		r.setTotalAdeudos(cargos);
		r.setTotalIngreso(abonos);
		return r;
	}
	
	@Override
	public ReporteMovimientos reporteMovGeneral(){
		ReporteMovimientos r = new ReporteMovimientos();
		BigDecimal cargos = movJpa.obtenerSumaTipoMovimiento(1) == null ? BigDecimal.ZERO : movJpa.obtenerSumaTipoMovimiento(1);
		BigDecimal abonos = movJpa.obtenerSumaTipoMovimiento(2) == null ? BigDecimal.ZERO : movJpa.obtenerSumaTipoMovimiento(2);
		BigDecimal saldo = cargos.subtract(abonos);
		r.setSaldo(saldo);
		r.setTotalAdeudos(cargos);
		r.setTotalIngreso(abonos);
		return r;
	}
	
	@Override
	public ReporteMovimientos reporteMovPorFecha(Date fechai, Date fechaf){
		ReporteMovimientos r = new ReporteMovimientos();
		BigDecimal cargos = movJpa.obtenerSumaTipoMovimientoPorFecha(1,fechai,fechaf) == null ? BigDecimal.ZERO : movJpa.obtenerSumaTipoMovimientoPorFecha(1,fechai,fechaf);
		BigDecimal abonos = movJpa.obtenerSumaTipoMovimientoPorFecha(2,fechai,fechaf) == null ? BigDecimal.ZERO : movJpa.obtenerSumaTipoMovimientoPorFecha(2,fechai,fechaf);
		BigDecimal saldo = cargos.subtract(abonos);
		r.setSaldo(saldo);
		r.setTotalAdeudos(cargos);
		r.setTotalIngreso(abonos);
		return r;
	}

}
