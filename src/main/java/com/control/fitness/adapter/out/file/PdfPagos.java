package com.control.fitness.adapter.out.file;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.control.fitness.UtilidadesAdapter;
import com.control.fitness.application.port.in.ReportesUseCase;
import com.control.fitness.application.port.out.ConfiguracionPort;
import com.control.fitness.application.port.out.MovimientosPort;
import com.control.fitness.application.port.out.SociedadPort;
import com.control.fitness.application.port.out.UsuariosPort;
import com.control.fitness.domain.MovimientoEntity;
import com.control.fitness.domain.ReporteMovimientos;
import com.control.fitness.domain.SociedadEntity;
import com.control.fitness.domain.UsuarioEntity;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:configuraciones-global.properties")
public class PdfPagos {

	static Logger log = LoggerFactory.getLogger(PdfPagos.class);

	private BigDecimal TotalMenosNoAplica;

	@Autowired
	private UsuariosPort usuariosPort;

	@Autowired
	private MovimientosPort movPort;

	@Autowired
	private SociedadPort socPort;

	@Autowired
	private ReportesUseCase repUse;

	@Autowired
	private PdfUtilidad pdfUtil;

	@Autowired
	private ConfiguracionPort configPort;

	@Value("${pdf.font}")
	private static String rutaFont;

	@Value("${rutaArchivos}")
	private String rutaArchivos;

	@Value("${rutaArchivos.qas}")
	private String rutaArchivosQAS;

	@Value("${rutaArchivos.pro}")
	private String rutaArchivosPRO;

	@Value("${ambiente}")
	private String ambiente;

	private String[] colores;

	public String[] getColores() {
		return colores;
	}

	public void setColores(String[] colores) {
		this.colores = colores;
	}

	public static void main(String args[]) {

		PdfPagos p = new PdfPagos();
		try {
			try {

				p.generarPdf(1, null, null, 0);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public byte[] generarPdf(int idPaciente, Date fechai, Date fechaf, int idSociedad)
			throws FileNotFoundException, DocumentException {

		UsuarioEntity p;
		List<MovimientoEntity> movs = null;

		if (idPaciente == 0) {
			// Obtener todos los pagos en generañ
			movs = movPort.obtenerMovimientos();
			p = null;
		} else {
			// Obtener datos de paciente
			movs = movPort.obtenerMovimientosDePacientePorFecha(idPaciente, fechai, fechaf);
			p = usuariosPort.buscarPorId(idPaciente);
		}

		SociedadEntity soc = null;
		if (idPaciente == 0) {
			soc = socPort.buscarSociedadPorId(idSociedad);
			String coloresRGB = configPort.obtenerConfiguracion(soc).getColor_base();
			String[] colors = coloresRGB.split(",");
			setColores(colors);
		} else {
			String coloresRGB = configPort.obtenerConfiguracion(p.getSociedad()).getColor_base();
			String[] colors = coloresRGB.split(",");
			setColores(colors);
			soc = p.getSociedad();
		}

		byte[] pdfBytes = null;
		final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		Document document = new Document(PageSize.LETTER);
		document.setMargins(12, 12, 30, 20);
		document.setMarginMirroring(true);

		PdfWriter.getInstance(document, byteStream);

		document.open();
		generaDocumentoGeneral(document, p, movs, fechai, fechaf, soc);

		document.close();

		pdfBytes = byteStream.toByteArray();
		return pdfBytes;
	}

	public void generaDocumentoGeneral(Document document, UsuarioEntity p, List<MovimientoEntity> movimientos,
			Date fechai, Date fechaf, SociedadEntity soc) throws FileNotFoundException, DocumentException {
		crearTablaLogo(document, p, fechai, fechaf, soc);
		espacio(document, 20f);
		if (p != null) {
			cabecera(document, p, movimientos);
		}
		espacio(document, 20f);
		if (movimientos != null) {
			log.info("Tamaño movimientos:" + movimientos.size());
			if (!movimientos.isEmpty()) {
				datosDetalle(document, p, movimientos,fechai,fechaf);
			}else{
				if (p != null) {
					titulo(document, "No se han cargado movimientos aun", 70);
				} else {
					titulo(document, "No se han cargado movimientos aun", 90);
				}
				espacio(document, 5f);
			}
		} else {
			if (p != null) {
				titulo(document, "No se han cargado movimientos aun", 70);
			} else {
				titulo(document, "No se han cargado movimientos aun", 90);
			}
			espacio(document, 5f);
		}
		espacio(document, 20f);

		espacio(document, 5f);
		espacio(document, 100f);
	}

	public void crearTablaLogo(Document document, UsuarioEntity p, Date fechai, Date fechaf, SociedadEntity soc)
			throws DocumentException {

		log.info("generando sección de logo");

		String rutaArchivosFinal = "";
		if ("qas".equals(ambiente)) {
			rutaArchivosFinal = rutaArchivosQAS;
		} else if ("pro".equals(ambiente)) {
			rutaArchivosFinal = rutaArchivosPRO;
		} else {
			rutaArchivosFinal = rutaArchivos;
		}

		try {

			PdfPTable tabla = new PdfPTable(2);
			tabla.setWidthPercentage(100);
			tabla.setWidths(new int[] { 7, 12 });
			PdfPCell cell = null;
			// Logo
			String rutaLogo = configPort.obtenerRutaLogoSociedad(soc);
			System.out.println("ruta logo:" + rutaArchivosFinal + rutaLogo);
			Image img = Image.getInstance(rutaArchivosFinal + rutaLogo);
			img.setWidthPercentage(50);

			cell = new PdfPCell(img, true);
			cell = new PdfPCell();
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(0);
			cell.setFixedHeight(50f);
			tabla.addCell(cell);
			document.add(tabla);

			if (p != null) {

				PdfPTable tabla2 = new PdfPTable(1);
				tabla2.setWidthPercentage(100);
				tabla2.setWidths(new int[] { 10 });
				cell = null;
				tabla2.addCell(PdfUtilidad.cell("Reporte de movimientos ",
						pdfUtil.obtenerFont(getColores(), "negritaTitulos"), 0, "centro", "","negro"));
				tabla2.addCell(PdfUtilidad.cell(
						UtilidadesAdapter.formatearFecha(fechai) + " a " + UtilidadesAdapter.formatearFecha(fechaf),
						pdfUtil.obtenerFont(getColores(), "negritaTitulos"), 0, "centro", "","negro"));
				document.add(tabla2);
			} else {
				PdfPTable tabla2 = new PdfPTable(1);
				tabla2.setWidthPercentage(100);
				tabla2.setWidths(new int[] { 10 });
				cell = null;
				tabla2.addCell(PdfUtilidad.cell("Reporte de movimientos",
						pdfUtil.obtenerFont(getColores(), "negritaTitulos"), 0, "centro", "","negro"));
				tabla2.addCell(PdfUtilidad.cell(
						UtilidadesAdapter.formatearFecha(fechai) + " a " + UtilidadesAdapter.formatearFecha(fechaf),
						pdfUtil.obtenerFont(getColores(), "negritaTitulos"), 0, "centro", "","negro"));
				document.add(tabla2);
			}

			document.add(tabla);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public PdfPTable cabecera(Document document, UsuarioEntity p, List<MovimientoEntity> movimientos)
			throws FileNotFoundException, DocumentException {

		log.info("generando sección de cabecera");

		BigDecimal totalT = new BigDecimal("0.00");
		BigDecimal noAplicaT = new BigDecimal("0.00");

		totalT = totalT.subtract(noAplicaT);
		setTotalMenosNoAplica(totalT);

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(70);
		table.setWidths(new int[] { 10 });

		table.addCell(PdfUtilidad.cell("Usuario:"+p.getNombre() ,
				pdfUtil.obtenerFont(getColores(), "normal"), 0, "izquierda", "","negro"));
		table.addCell(PdfUtilidad.cell("Correo:"+p.getCorreoElectronico()+", Telefono:"+p.getTelefono(), pdfUtil.obtenerFont(getColores(), "normal"), 0,
				"izquierda", "","negro"));

		document.add(table);

		return table;
	}

	public void datosDetalle(Document document, UsuarioEntity p, List<MovimientoEntity> movimientos,Date fechai, Date fechaf)
			throws FileNotFoundException, DocumentException {

		log.info("generando sección de datos detalle de pago");

		ReporteMovimientos mp;
		if (p != null) {
			mp = repUse.reporteMovDePaciente(p.getIdUsuario(),fechai,fechaf);
		} else {
			mp = repUse.reporteMovGeneral();
		}

		BigDecimal saldo = new BigDecimal("0.00");
		BigDecimal monto = new BigDecimal("0.00");

		if (p != null) {
			titulo(document, "Resumen de movimientos", 70);
		} else {
			titulo(document, "Resumen de movimientos", 90);
		}
		espacio(document, 3f);

		// Tabla 2
		PdfPTable table2 = new PdfPTable(3);
		if (p != null) {
			table2.setWidthPercentage(70);
		} else {
			table2.setWidthPercentage(90);
		}
		table2.setWidths(new int[] { 1, 2, 1 });
		table2.addCell(
				PdfUtilidad.cell("Total de cargos", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		table2.addCell(PdfUtilidad.cell(mp.getTotalAdeudos(), pdfUtil.obtenerFont(getColores(), "normal"), 0,
				"izquierda", "","negro"));
		table2.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		table2.addCell(
				PdfUtilidad.cell("Total de abonos", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		table2.addCell(PdfUtilidad.cell(mp.getTotalIngreso(), pdfUtil.obtenerFont(getColores(), "normal"), 0,
				"izquierda", "","negro"));
		table2.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		table2.addCell(
				PdfUtilidad.cell("Saldo total", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		table2.addCell(
				PdfUtilidad.cell(mp.getSaldo(), pdfUtil.obtenerFont(getColores(), "normal"), 0, "izquierda", "","negro"));
		table2.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		document.add(table2);

		espacio(document, 20f);

		if (p != null) {
			titulo(document, "Detalle de movimientos", 70);
		} else {
			titulo(document, "Detalle de movimientos", 90);
		}
		espacio(document, 3f);
		PdfPTable table;

		if (p != null) {
			table = new PdfPTable(5);
			table.setWidthPercentage(70);
			table.setWidths(new int[] { 2, 2, 1, 1, 1 });
			table.addCell(
					PdfUtilidad.cell("Fecha", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda", "gris","negro"));
			table.addCell(PdfUtilidad.cell("Descripción", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda",
					"gris","negro"));
			table.addCell(
					PdfUtilidad.cell("Cargo", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda", "gris","negro"));
			table.addCell(
					PdfUtilidad.cell("Abono", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda", "gris","negro"));
			table.addCell(
					PdfUtilidad.cell("Saldo", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda", "gris","negro"));

			if (movimientos != null) {
				if (!movimientos.isEmpty()) {
					for (MovimientoEntity m : movimientos) {

						monto = new BigDecimal("0.00");
						monto = m.getMonto();

						table.addCell(PdfUtilidad.cell(UtilidadesAdapter.fechaEnLetra(m.getFecha()),
								pdfUtil.obtenerFont(getColores(), "normal"), 0, "izquierda", "","negro"));
						table.addCell(PdfUtilidad.cell(m.getDescripcion(), pdfUtil.obtenerFont(getColores(), "normal"),
								0, "izquierda", "","negro"));

						// Si es cargo
						if (m.getTipoMovimiento().getDescripcion().equals("cargo")) {
							table.addCell(PdfUtilidad.cell(monto, pdfUtil.obtenerFont(getColores(), "verde"), 0,
									"izquierda", "","negro"));
							table.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "rojo"), 0,
									"izquierda", "","negro"));
							saldo = saldo.add(monto);
						}

						// Si es abono
						if (m.getTipoMovimiento().getDescripcion().equals("abono")) {
							table.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "rojo"), 0,
									"izquierda", "","negro"));
							table.addCell(PdfUtilidad.cell(monto, pdfUtil.obtenerFont(getColores(), "rojo"), 0,
									"izquierda", "","negro"));
							saldo = saldo.subtract(monto);

						}

						table.addCell(
								PdfUtilidad.cell(saldo, pdfUtil.obtenerFont(getColores(), "normal"), 0, "derecha", "","negro"));

					}

				}
			}

		} else {
			table = new PdfPTable(6);
			table.setWidthPercentage(90);
			table.setWidths(new int[] { 3, 1, 2, 1, 1, 1 });
			table.addCell(PdfUtilidad.cell("Paciente", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda",
					"gris","negro"));
			table.addCell(
					PdfUtilidad.cell("Fecha", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda", "gris","negro"));
			table.addCell(PdfUtilidad.cell("Descripción", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda",
					"gris","negro"));
			table.addCell(
					PdfUtilidad.cell("Cargo", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda", "gris","negro"));
			table.addCell(
					PdfUtilidad.cell("Abono", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda", "gris","negro"));
			table.addCell(
					PdfUtilidad.cell("Saldo", pdfUtil.obtenerFont(getColores(), "negrita"), -1, "izquierda", "gris","negro"));

			if (movimientos != null) {
				if (!movimientos.isEmpty()) {
					for (MovimientoEntity m : movimientos) {

						monto = new BigDecimal("0.00");
						monto = m.getMonto();

						table.addCell(PdfUtilidad.cell(m.getUsuario().getNombre(),
								pdfUtil.obtenerFont(getColores(), "normal"), 0, "izquierda", "","negro"));
						table.addCell(PdfUtilidad.cell(UtilidadesAdapter.formatearFecha(m.getFecha()),
								pdfUtil.obtenerFont(getColores(), "normal"), 0, "izquierda", "","negro"));
							table.addCell(PdfUtilidad.cell(m.getDescripcion(), pdfUtil.obtenerFont(getColores(), "normal"),
								0, "izquierda", "","negro"));

						// Si es cargo
						if (m.getTipoMovimiento().getDescripcion().equals("cargo")) {
							table.addCell(PdfUtilidad.cell(monto, pdfUtil.obtenerFont(getColores(), "verde"), 0,
									"izquierda", "","negro"));
							table.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "rojo"), 0,
									"izquierda", "","negro"));
							saldo = saldo.add(monto);
						}

						// Si es abono
						if (m.getTipoMovimiento().getDescripcion().equals("abono")) {
							table.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "rojo"), 0,
									"izquierda", "","negro"));
							table.addCell(PdfUtilidad.cell(monto, pdfUtil.obtenerFont(getColores(), "rojo"), 0,
									"izquierda", "","negro"));
							saldo = saldo.subtract(monto);

						}

						table.addCell(
								PdfUtilidad.cell(saldo, pdfUtil.obtenerFont(getColores(), "normal"), 0, "derecha", "","negro"));

					}

				}
			}
		}

		document.add(table);
	}

	public void titulo(Document document, String titulo, int tamaño) throws DocumentException {
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(tamaño);
		table.setWidths(new int[] { 2, 1, 1, 1, });
		table.addCell(
				PdfUtilidad.cell(titulo, pdfUtil.obtenerFont(getColores(), "negritaTitulos"), 0, "izquierda", "","negro"));
		table.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		table.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		table.addCell(PdfUtilidad.cell("", pdfUtil.obtenerFont(getColores(), "negrita"), 0, "izquierda", "","negro"));
		document.add(table);
	}

	public static void espacio(Document document, float altura) throws FileNotFoundException, DocumentException {

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 20 });

		PdfPCell cell = new PdfPCell();
		cell.setFixedHeight(altura);
		cell.setBorder(0);
		table.addCell(cell);

		document.add(table);
	}

	public BigDecimal getTotalMenosNoAplica() {
		return TotalMenosNoAplica;
	}

	public void setTotalMenosNoAplica(BigDecimal totalMenosNoAplica) {
		TotalMenosNoAplica = totalMenosNoAplica;
	}

}
