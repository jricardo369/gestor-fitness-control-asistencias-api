package com.control.fitness;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.security.crypto.codec.Hex;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class UtilidadesAdapter {
	
	public static String currencyFormat(BigDecimal n) {
		NumberFormat.getInstance(new Locale("es", "MX"));
	    return NumberFormat.getCurrencyInstance().format(n);
	}

	public static String formatearFecha(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "MX"));
		String strFecha = "";
		if (fecha != null) {
			strFecha = formatter.format(fecha);
		} else {
			return strFecha;
		}
		return strFecha;
	}

	public static String formatearFechaConHora(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("es", "MX"));
		String strFecha = formatter.format(fecha);
		return strFecha;
	}

	public static String formatearHora(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", new Locale("es", "MX"));
		String strFecha = formatter.format(fecha);
		return strFecha;
	}

	public static String horaActual(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss z");
		String strFecha = formatter.format(fecha);
		return strFecha;
	}

	public static Date cadenaAFecha(String fecha) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd",new Locale("es", "MX")).parse(fecha);
		return date;
	}

	public static String formatNumber(Object number) {
		Locale currentLocale = Locale.getDefault();
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
		return currencyFormatter.format(number);
	}

	public static String tomarAnioActual() {
		return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	}

	public static Date tomarFecha() {
		Date date = new Date();
		return date;
	}

	public static String tomarFechaYHora() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		System.out.print(dateFormat.format(date));
		return dateFormat.format(date);
	}

	public static Properties readPropertiesFile(String fileName) throws IOException {
		File f = new File(fileName);
		FileInputStream fis = null;
		Properties prop = null;

		try {
			fis = new FileInputStream(f);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}

		return prop;
	}

	public static Date sumarDiasAFecha(Date fecha, int dias) {
		if (dias == 0)
			return fecha;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}

	public static String sha256(String usuario, String cont) {
		String contranenia = usuario + cont;
		MessageDigest digest;
		String sha256hex = "";
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(contranenia.getBytes(StandardCharsets.UTF_8));
			sha256hex = new String(Hex.encode(hash));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sha256hex;
	}

	public static String isJSONValid(String json) {

		try {

			JsonParser parser = new JsonParser();
			if (!parser.parse(json).isJsonObject())
				return "No es un fromato JSON correcto";

		} catch (JsonSyntaxException jse) {
			return jse.getMessage();
		}

		return "";
	}

	public static String generarRutaArchivo(int idHistorial) {
		return "/" + Calendar.getInstance().get(Calendar.YEAR) + "/" + idHistorial;
	}

	public static String fechaEnLetra(Date d) {
		String fecha = UtilidadesAdapter.formatearFecha(d);
		System.out.println("Fecha:" + fecha);
		String salida = "";
		String mesLetra = "";
		int mes = 0;
		int diaFecha = 0;
		String dia = "";
		int anio = 0;

		try {
			String pattern = "YYYY-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("es", "MX"));
			Calendar c = Calendar.getInstance();
			c.setTime(simpleDateFormat.parse(fecha));

			mes = Integer.valueOf(fecha.substring(5, 7));
			diaFecha = c.get(Calendar.DAY_OF_WEEK);
			dia = fecha.substring(8, 10);
			anio = Integer.valueOf(fecha.substring(0, 4));

			System.out.println("Día fecha:" + diaFecha);
			System.out.println("Día:" + dia);
			System.out.println("Mes:" + mes);
			System.out.println("Anio:" + anio);

			if (diaFecha == 2) {
				salida = "Lunes";
			}
			if (diaFecha == 3) {
				salida = "Martes";
			}
			if (diaFecha == 4) {
				salida = "Miercoles";
			}
			if (diaFecha == 5) {
				salida = "Jueves";
			}
			if (diaFecha == 6) {
				salida = "Viernes";
			}
			if (diaFecha == 7) {
				salida = "Sabado";
			}
			if (diaFecha == 1) {
				salida = "Domingo";
			}

			switch (mes) {
			case 1:
				mesLetra = "Enero";
				break;
			case 2:
				mesLetra = "Febrero";
				break;
			case 3:
				mesLetra = "Marzo";
				break;
			case 4:
				mesLetra = "Abril";
				break;
			case 5:
				mesLetra = "Mayo";
				break;
			case 6:
				mesLetra = "Junio";
				break;
			case 7:
				mesLetra = "Julio";
				break;
			case 8:
				mesLetra = "Agosto";
				break;
			case 9:
				mesLetra = "Septiembre";
				break;
			case 10:
				mesLetra = "Octubre";
				break;
			case 11:
				mesLetra = "Noviembre";
				break;
			case 12:
				mesLetra = "Diciembre";
				break;
			default:
				break;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return salida + " " + dia + " de " + mesLetra + " del " + anio;
	}

	public static void main(String args[]) {

		System.out.println(UtilidadesAdapter.fechaEnLetra(new Date()));
	}

	public static int compararFechaActualVsFecha(Date fechaEntrada) {

		int salida = 0;

		System.out.println("fecha recibida:" + fechaEntrada);
		Date fechaActual;
		fechaActual = UtilidadesAdapter.tomarFecha();
		System.out.println("fecha actual:" + fechaActual);
		salida = fechaEntrada.compareTo(fechaActual);

		if (salida == 0) {
			System.out.println("Fechas iguales");
		}
		if (salida > 0) {
			System.out.println("La fecha recibida es mayor que la fecha actual");
		}
		if (salida < 0) {
			System.out.println("La fecha recibida es menor que la fecha actual");
		}
		return salida;
	}

	public static String convertirHoraA24Hrs(String hora) {
		String salida = "";
		switch (hora) {
		case "1":
			salida = "13";
			break;
		case "2":
			salida = "14";
			break;
		case "3":
			salida = "15";
			break;
		case "4":
			salida = "16";
			break;
		case "5":
			salida = "17";
			break;
		case "6":
			salida = "18";
			break;
		case "7":
			salida = "19";
			break;
		case "8":
			salida = "20";
			break;
		case "9":
			salida = "21";
			break;
		case "10":
			salida = "22";
			break;
		case "11":
			salida = "23";
			break;
		case "12":
			salida = "24";
			break;
		default:
			salida = hora;
			break;
		}
		return salida;
	}

	public static String completaCeros(String valor, int total) {
		String ceros = "";
		total = total - valor.length();

		for (int i = 0; i < total; i++) {
			ceros += "0";
		}
		valor = ceros + valor;
		return valor;
	}

	public static String generarFecha(boolean soloFecha, boolean soloHora, boolean dateTime, String replace,
			int diasASumar, String fechaEntrada) throws ParseException {
		Date d = new Date();
		String fecha = "";
		String hora = "";
		String salida = "";
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("es", "MX"));
		String horapatt = "HH:mm:ss.SSS";
		SimpleDateFormat simpleDateFormatS = new SimpleDateFormat(horapatt, new Locale("es", "MX"));

		if (fechaEntrada != null) {

			Date fechaS = simpleDateFormat.parse(fechaEntrada);

			if (diasASumar == 0) {
				fecha = simpleDateFormat.format(fechaS);
			} else {
				fecha = simpleDateFormat.format(fechaS);
				Calendar c = Calendar.getInstance();
				c.setTime(simpleDateFormat.parse(fecha));
				c.add(Calendar.DATE, diasASumar);
				fecha = simpleDateFormat.format(c.getTime());
			}

			hora = simpleDateFormatS.format(d);

		} else {
			if (diasASumar == 0) {
				fecha = simpleDateFormat.format(d);
			} else {
				fecha = simpleDateFormat.format(d);
				Calendar c = Calendar.getInstance();
				c.setTime(simpleDateFormat.parse(fecha));
				c.add(Calendar.DATE, diasASumar);
				fecha = simpleDateFormat.format(c.getTime());
			}

			hora = simpleDateFormatS.format(d);
		}
		// log.info("Fecha:" + fecha);
		// log.info("Hora:" + hora);

		if (soloFecha) {
			salida = fecha;
		}
		if (soloHora) {
			salida = hora;
		}
		if (dateTime) {
			salida = fecha + "T" + hora + "Z";
		}
		if (!replace.equals("")) {
			salida = fecha.toString().substring(0, 10).replace(replace, "");
		}

		return salida;
	}
	
	public static List<String> obtenerTodosLosDiasDelAniooAPartirDeFecha(Date fecha,int dias) {
		List<String> list = new ArrayList<>();
		String fechaS = UtilidadesAdapter.formatearFecha(fecha);
		String anio = fechaS.substring(0, 4);
		int anioS = Integer.valueOf(anio) + 1;
		list.add(fechaS);
		for (;;) {

			if (fechaS.substring(0, 4).equals(String.valueOf(anioS))) {
				break;
			}
			list.add(fechaS);
		}
		return list;
	}

public static List<String> diasSemana(){
		
		String fecha = formatearFecha(new Date());
		List<String> dias = new ArrayList<>();
		
		System.out.println(fecha.substring(0, 4)+"/"+ fecha.substring(5,7)+"/"+ fecha.subSequence(8,10));
		
		LocalDate mDate = LocalDate.of(Integer.valueOf(fecha.substring(0, 4)),Integer.valueOf(fecha.substring(5, 7)),Integer.valueOf(fecha.substring(8,10))); 

		dias.add(mDate.with(DayOfWeek.MONDAY).toString());
		dias.add(mDate.with(DayOfWeek.TUESDAY).toString());
		dias.add(mDate.with(DayOfWeek.WEDNESDAY).toString());
		dias.add(mDate.with(DayOfWeek.THURSDAY).toString());
		dias.add(mDate.with(DayOfWeek.FRIDAY).toString());
		dias.add(mDate.with(DayOfWeek.SATURDAY).toString());
		dias.add(mDate.with(DayOfWeek.SUNDAY).toString());
//	    System.out.println("Lunes ==> " +mDate.with(DayOfWeek.MONDAY));
//	    System.out.println("Martes ==> " +mDate.with(DayOfWeek.TUESDAY));
//	    System.out.println("Miercoles ==> " +mDate.with(DayOfWeek.WEDNESDAY));
//	    System.out.println("Jueves ==> " +mDate.with(DayOfWeek.THURSDAY));
//	    System.out.println("Viernes ==> " +mDate.with(DayOfWeek.FRIDAY));
//	    System.out.println("Sabado ==> " +mDate.with(DayOfWeek.SATURDAY));
//	    System.out.println("Domingo ==> " +mDate.with(DayOfWeek.SUNDAY));
        
		return dias;
	}
	
}
