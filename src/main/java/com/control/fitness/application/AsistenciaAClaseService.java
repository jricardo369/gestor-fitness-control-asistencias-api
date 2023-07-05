package com.control.fitness.application;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.control.fitness.ApiException;
import com.control.fitness.MessageAdapter;
import com.control.fitness.UtilidadesAdapter;
import com.control.fitness.application.port.in.AsistenciaAClaseUseCase;
import com.control.fitness.application.port.out.AsistenciaAClasePort;
import com.control.fitness.application.port.out.ClasePort;
import com.control.fitness.application.port.out.UsuariosPort;
import com.control.fitness.domain.AsistenciaAClase;
import com.control.fitness.domain.AsistenciaAClaseEntity;
import com.control.fitness.domain.AsistenciaAClaseUsuarios;
import com.control.fitness.domain.ClaseEntity;
import com.control.fitness.domain.UsuarioEntity;

@Service
public class AsistenciaAClaseService implements AsistenciaAClaseUseCase {

	@Autowired
	private MessageAdapter message;

	Logger log = LoggerFactory.getLogger(AsistenciaAClaseService.class);

	@Autowired
	private AsistenciaAClasePort acPort;

	@Autowired
	private UsuariosPort usPort;

	@Autowired
	private ClasePort clPort;

	@Override
	public void agregarUsuarioAClase(AsistenciaAClase clase) {

		int idClase = clase.getIdClase();
		int idUsuario = clase.getIdUsuario();
		String fecha = UtilidadesAdapter.formatearFecha(clase.getFecha());

		// Tomar usuario
		UsuarioEntity usuario = usPort.buscarPorId(idUsuario);
		if (usuario == null) {
			throw new ApiException(500, message.msg("clase.noexiste"));
		}
		// Tomar clase
		ClaseEntity cl = clPort.obtenerClasePorId(idClase);
		if (cl == null) {
			throw new ApiException(500, message.msg("clase.usuarionoexiste"));
		}

		log.info("Agregando asistencia Id clase:" + idClase + " / Id usuario:" + idUsuario + " / Fecha:" + fecha);

		log.info("--- Validando que usuario este activo");
		String estatus = usuario.getEstatus() != null ? usuario.getEstatus() : "";
		if (estatus.equals("0")) {
			throw new ApiException(500, message.msg("usuario.inactivo"));
		}
		if (estatus.equals("3")) {
			throw new ApiException(500, message.msg("usuario.bloqueado"));
		}

		// Tomando personas permitidas de clase
		log.info("--- Validando personas permitidas");
		int numPersonasPermitidas = Integer.valueOf(cl.getPersonas());
		int numPersonsasActuales = acPort.obtenerNumeroDePersonasDeClase(idClase, fecha);
		if (numPersonasPermitidas == numPersonsasActuales) {
			throw new ApiException(500,
					message.msg("asistencia.clasellena"));
		}

		// Validar que no este dado de alta en la clase
		log.info("--- Validando que no este dado de alta el usuario en alguna clase en la fecha");
		ClaseEntity clEnc = clPort.estaEnClaseEnFecha(fecha, idUsuario);
		if (clEnc != null) {
			String[] params = new String[] { cl.getNombre(), fecha };
			throw new ApiException(500, message.msg("asistencia.usuarioenclase", params));
		}

		// Tomar si es fecha actual ono
		log.info("--- Comparando si es fecha actual o no");
		int compFechas = UtilidadesAdapter.compararFechaActualVsFecha(clase.getFecha());
		boolean horarioFueraDeClase = false;

		if (compFechas == 0) {

			String hora = null;
			try {
				hora = UtilidadesAdapter.generarFecha(false, true, false, "", 0, fecha).substring(0, 2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			horarioFueraDeClase = horarioFueraDeClase(hora, cl.getHoraInicio(), cl.getHorario());

			if (horarioFueraDeClase) {
				log.info("Clase fuera de horario");
				String[] params = new String[] { cl.getNombre()};
				throw new ApiException(500, message.msg("asistencia.usuarioenclase", params));
			}
		} else if (compFechas == -1) {
			throw new ApiException(500, message.msg("asistencia.fechaanterior"));
		}

		log.info("--- Paso todas las validaciones para poder agregarlo a clase");
		AsistenciaAClaseEntity ace = new AsistenciaAClaseEntity();
		ace.setIdClase(idClase);
		ace.setIdUsuario(idUsuario);
		ace.setFecha(clase.getFecha());
		acPort.crearAsistenciaAClase(ace);

	}

	@Override
	public AsistenciaAClaseUsuarios obtenerAsistenciaAClases(int idClase, String fecha) {

		log.info("Agregando asistencia Id clase:" + idClase + " / Fecha:" + fecha + "-locale:"
				+ LocaleContextHolder.getLocale());
		// Obtener usuarios de clase
		List<UsuarioEntity> usuarios = usPort.obtenerUsuariosDeClase(idClase, fecha);
		// Obtener clase
		ClaseEntity clase = clPort.obtenerClasePorId(idClase);
		if (clase == null) {
			throw new ApiException(500, message.msg("clase.noexiste"));
		}
		AsistenciaAClaseUsuarios acu = new AsistenciaAClaseUsuarios();
		acu.setUsuarios(usuarios);
		acu.setFecha(fecha);
		acu.setClase(clase);

		return acu;
	}
	
	@Override
	public List<AsistenciaAClaseUsuarios> obtenerAsistenciaAClasesPorSemana(String fecha){
		List<AsistenciaAClaseUsuarios> asistencias = new ArrayList<>(); 
		
		List<ClaseEntity> cls = clPort.obtenerClases();
		List<String> diasSemana = UtilidadesAdapter.diasSemana();
		for (String dia : diasSemana) {
			for (ClaseEntity cl : cls) {
				log.info("Agregando asistencia Id clase:" + cl.getIdClase() + " / Fecha:" + dia + "-locale:"
						+ LocaleContextHolder.getLocale());
				// Obtener usuarios de clase
				List<UsuarioEntity> usuarios = usPort.obtenerUsuariosDeClase(cl.getIdClase(), dia);
				AsistenciaAClaseUsuarios acu = new AsistenciaAClaseUsuarios();
				acu.setUsuarios(usuarios);
				acu.setFecha(dia);
				acu.setClase(cl);
				asistencias.add(acu);
			}
		}
		
		return asistencias;
	}

	@Override
	public void crearAsistenciaAClase(AsistenciaAClaseEntity ac) {
		acPort.crearAsistenciaAClase(ac);
	}

	@Override
	public void actualizarAsistenciaAClase(AsistenciaAClaseEntity ac) {
		acPort.actualizarAsistenciaAClase(ac);
	}

	@Override
	public void eliminarAsistenciaAClase(AsistenciaAClaseEntity ac) {
		acPort.eliminarAsistenciaAClase(ac);
	}

	public boolean horarioFueraDeClase(String hora, String horaClase, String horario) {

		boolean salida = false;

		log.info("Hora Ingresada:" + hora);
		log.info("Hora Clase:" + horaClase);

		if ("V".equals(horario)) {
			horaClase = UtilidadesAdapter.convertirHoraA24Hrs(horaClase);
		}

		horaClase = UtilidadesAdapter.completaCeros(horaClase, 2);

		log.info("Hora Ingresada despues formato:" + hora);
		log.info("Hora Clase despues de formato:" + horaClase);

		log.info(hora + " es mayor que " + horaClase);

		if (Integer.valueOf(hora) >= Integer.valueOf(horaClase)) {
			salida = true;
		}

		log.info("Clase fuera de horario?:" + salida);

		return salida;
	}

	@Override
	public void crearOActualizarSiAsistioAClase(String usuario) {
		 //Buscar usuario
		UsuarioEntity us = usPort.buscarPorUsuario(usuario);
		if(us == null){
			throw new ApiException(500, message.msg("usuario.noexiste"));
		}
		
	}

}
