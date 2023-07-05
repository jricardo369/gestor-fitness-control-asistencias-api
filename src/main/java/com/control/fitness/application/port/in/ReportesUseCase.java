package com.control.fitness.application.port.in;

import java.util.Date;

import com.control.fitness.domain.ReporteMovimientos;

public interface ReportesUseCase {

	public ReporteMovimientos reporteMovDePaciente(int idPaciente,Date fechai, Date fechaf);
	public ReporteMovimientos reporteMovPorFecha(Date fechai, Date fechaf);
	public ReporteMovimientos reporteMovGeneral();
	
}
