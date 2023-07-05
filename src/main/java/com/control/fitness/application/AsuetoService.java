package com.control.fitness.application;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.ApiException;
import com.control.fitness.MessageAdapter;
import com.control.fitness.UtilidadesAdapter;
import com.control.fitness.application.port.in.AsuetoUseCase;
import com.control.fitness.application.port.out.AsistenciaAClasePort;
import com.control.fitness.application.port.out.AsuetoPort;
import com.control.fitness.domain.Asueto;
import com.control.fitness.domain.AsuetoEntity;

@Service
public class AsuetoService implements AsuetoUseCase {

	Logger log = LoggerFactory.getLogger(AsuetoService.class);

	@Autowired
	private MessageAdapter message;

	@Autowired
	private AsuetoPort asuetoPort;

	@Autowired
	private AsistenciaAClasePort asisPort;

	@Override
	public List<AsuetoEntity> obtenerAsuetos(String fecha) {
		Date fechaDate = null;
		try {
			fechaDate = UtilidadesAdapter.cadenaAFecha(fecha);
			log.info("Fecha asuetos:" + fecha + "-Formato date:" + fechaDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		List<AsuetoEntity> asuetos = null;
		asuetos = asuetoPort.obtenerAsuetos(fechaDate);
		return asuetos;
	}

	@Override
	public void crearAsueto(Asueto asueto, int tipo) {

		log.info("Asueto:" + asueto + "-tipo:" + tipo + "-Fecha:" + asueto.getFecha());

		List<String> fechas = null;
		List<AsuetoEntity> asuetos = null;
		Date fechaAsueto = null;
		try {
			fechaAsueto = UtilidadesAdapter.cadenaAFecha(asueto.getFecha());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		// Validar que tipo de asueto es
		if (tipo == 1) {

			fechas = new ArrayList<>();
			// Validar si existe
			if (asuetoPort.asuetoDuplicado(asueto.getIdClase(), asueto.getFecha()) > 0) {
				throw new ApiException(500, message.msg("asueto.duplicado"));
			}
			fechas.add(asueto.getFecha());

			asisPort.obtenerAsistenciasPorFechasYClase(asueto.getIdClase(), fechas);

			asuetoPort.crearAsueto(AsuetoToAsuetoEntity(asueto));

		} else if (tipo == 7 || tipo == 14) {

			fechas = UtilidadesAdapter.obtenerTodosLosDiasDelAniooAPartirDeFecha(fechaAsueto, tipo);
			for (String f : fechas) {
				AsuetoEntity a = new AsuetoEntity();
				try {
					a.setFecha(UtilidadesAdapter.cadenaAFecha(f));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				a.setIdClase(asueto.getIdClase());
			}

			asuetoPort.crearVariosAsueto(asuetos);

		} else {
			throw new ApiException(500, message.msg("asueto.solodias"));
		}

	}

	@Override
	public void eliminarAsueto(int idAsueto) {
		asuetoPort.eliminarAsueto(asuetoPort.obtenerAsueto(idAsueto));
	}

	public AsuetoEntity AsuetoToAsuetoEntity(Asueto a) {
		AsuetoEntity ae = new AsuetoEntity();
		try {
			ae.setFecha(UtilidadesAdapter.cadenaAFecha(a.getFecha()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ae.setIdClase(a.getIdClase());
		return ae;
	}

}
