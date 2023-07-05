package com.control.fitness.adapter.rest;


import java.io.FileNotFoundException;
import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.adapter.out.file.PdfPagos;
import com.control.fitness.application.port.in.ReportesUseCase;
import com.control.fitness.domain.ReporteMovimientos;
import com.itextpdf.text.DocumentException;

@RequestMapping("/reportes")
@RestController
public class ReportesController {

	Logger log = LoggerFactory.getLogger(ReportesController.class);

	@Autowired
	private ReportesUseCase reportes;
	
	@Autowired
	private PdfPagos pdfPagos;
	
	@GetMapping("totales-movimientos-usuario/{idPaciente}")
	public ReporteMovimientos obtenerTotalesMovUsuarios(@PathVariable("idPaciente") int idPaciente) {
		return reportes.reporteMovDePaciente(idPaciente,null,null);
	}
	
	@GetMapping("totales-movimientos-usuario/por-fecha/{idPaciente}")
	public ReporteMovimientos obtenerTotalesMovUsuariosPorFecha(@PathVariable("idPaciente") int idPaciente,
			@RequestParam("fechai") Date fechai,
			@RequestParam("fechaf") Date fechaf) {
		return reportes.reporteMovDePaciente(idPaciente,fechai,fechaf);
	}
	
	@GetMapping("totales-movimientos-usuario/por-fecha")
	public ReporteMovimientos obtenerTotalesMovPorFecha(
			@RequestParam("fechai") Date fechai,
			@RequestParam("fechaf") Date fechaf) {
		return reportes.reporteMovPorFecha(fechai,fechaf);
	}
	
	@GetMapping("pdf-pagos-paciente/{idPaciente}")
	public byte[] pdfPagosPaciente(@PathVariable("idPaciente") int idPaciente,@RequestParam("fechai") Date fechai,
			@RequestParam("fechaf") Date fechaf) throws FileNotFoundException, DocumentException {
	    return pdfPagos.generarPdf(idPaciente,fechai,fechaf,0);
	}
	
	@GetMapping("pdf-pagos-general/{idSociedad}")
	public byte[] pdfPagosGeneralPorSoc(@RequestParam("fechai") Date fechai,
			@RequestParam("fechaf") Date fechaf,@PathVariable("idSociedad") int idSociedad) throws FileNotFoundException, DocumentException {
		 return pdfPagos.generarPdf(0,fechai,fechaf,idSociedad);
	}
	
	@GetMapping("pdf-pagos-general")
	public byte[] pdfPagosGeneral(@RequestParam("fechai") Date fechai,
			@RequestParam("fechaf") Date fechaf) throws FileNotFoundException, DocumentException {
		 return pdfPagos.generarPdf(0,fechai,fechaf,1);
	}

}
