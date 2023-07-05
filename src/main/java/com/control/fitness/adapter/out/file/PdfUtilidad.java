package com.control.fitness.adapter.out.file;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.control.fitness.UtilidadesAdapter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

@Service
@PropertySource(value = "classpath:configuraciones-global.properties")
public class PdfUtilidad {

	@Value("${pdf.font}")
	private String rutaFont;

	@Value("${pdf.fontQas}")
	private String rutaFontQas;

	@Value("${pdf.fontPro}")
	private String rutaFontPro;

	@Value("${ambiente}")
	private String ambiente;

	public static PdfPCell cell(Object valor, Font font, int border, String align, String colorFondo,
			String colorBorder) {

		String texto = "";
		texto = valor.toString();
		if (valor.getClass() == Integer.class) {
			texto = valor.toString();
		} else if (valor.getClass() == Float.class) {
			texto = valor.toString();
		} else if (valor.getClass() == BigDecimal.class) {
			texto = UtilidadesAdapter.formatNumber(valor);
		}

		PdfPCell cell = new PdfPCell(new Phrase(texto, font));

		if (align.equals("centro")) {
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else if (align.equals("derecha")) {
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else if (align.equals("izquierda")) {
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		}

		if (border == 0) {
			cell.setBorder(Rectangle.NO_BORDER);
		} else if (border == -1) {
			cell.setBorder(Rectangle.BOTTOM);
		} else if (border == -2) {
			cell.setBorder(Rectangle.TOP);
		} else {
			cell.setBorder(Rectangle.BOX);
		}

		// Color border
		if (colorBorder.equals("negro"))
			cell.setBorderColorTop(BaseColor.BLACK);
		if (colorBorder.equals("gris"))
			cell.setBorderColor(new BaseColor(180, 180, 180));

		if (colorFondo.equals("gris"))
			cell.setBackgroundColor(new BaseColor(243, 243, 243));
		if (colorFondo.equals("blanco"))
			cell.setBackgroundColor(BaseColor.WHITE);

		return cell;
	}

	public Font obtenerFont(String[] colores, String tipo) {

		String rutaFontFinal = "";
		if ("qas".equals(ambiente)) {
			rutaFontFinal = rutaFontQas;
		} else if ("pro".equals(ambiente)) {
			rutaFontFinal = rutaFontPro;
		} else {
			rutaFontFinal = rutaFont;
		}

		//System.out.println("ruta font:" + rutaFontFinal);

		Font f = null;

		if ("normal".equals(tipo)) {
			f = FontFactory.getFont(rutaFontFinal + "/Roboto-Light.ttf", 8, Font.NORMAL);
		} else if ("negrita".equals(tipo)) {
			f = FontFactory.getFont(rutaFontFinal + "/Roboto-Light.ttf", 8, Font.BOLD);
		} else if ("negritaTitulos".equals(tipo)) {
			f = FontFactory.getFont(rutaFontFinal + "/Roboto-Light.ttf", 9, Font.BOLD, new BaseColor(
					Integer.valueOf(colores[0]), Integer.valueOf(colores[1]), Integer.valueOf(colores[2])));
		} else if ("negritaTitulo".equals(tipo)) {
			f = FontFactory.getFont(rutaFontFinal + "/Roboto-Light.ttf", 9, Font.BOLD, new BaseColor(
					Integer.valueOf(colores[0]), Integer.valueOf(colores[1]), Integer.valueOf(colores[2])));
		} else if ("verde".equals(tipo)) {
			f = FontFactory.getFont(rutaFontFinal + "/Roboto-Light.ttf", 8, Font.BOLD, new BaseColor(0, 204, 102));
		} else if ("rojo".equals(tipo)) {
			f = FontFactory.getFont(rutaFontFinal + "/Roboto-Light.ttf", 8, Font.BOLD, new BaseColor(255, 102, 102));
		}

		return f;

	}

}
