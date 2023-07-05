package com.control.fitness.application;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.ApiException;
import com.control.fitness.application.port.in.CorreoElectronicoUseCase;
import com.control.fitness.application.port.out.EnviarCorreoPort;
import com.control.fitness.application.port.out.UsuariosPort;
import com.control.fitness.domain.UsuarioEntity;

@Service
public class CorreoElectronicoService implements CorreoElectronicoUseCase {

	Logger log = LoggerFactory.getLogger(CorreoElectronicoService.class);

	@Autowired
	private UsuariosPort usPort;

	@Autowired
	private EnviarCorreoPort envCorrPort;

	@Override
	public void enviarCorreoRecuperacion(String usuario) {

		// Usuario
		UsuarioEntity us = usPort.buscarPorUsuario(usuario);
		if (us != null) {

			Map<String, Object> params = new HashMap<>();
			params.put("${usuario}", us.getNombre());

			envCorrPort.enviarCorreoRecuperacion(us.getCorreoElectronico(), params, us.getSociedad());

		} else {

			throw new ApiException(500, "No se econtro tu usuario");

		}
	}

	@Override
	public void enviarCorreoAbono(int idCliente) {

		// Usuario
		UsuarioEntity clt = usPort.buscarPorId(idCliente);
		if (clt != null) {

			Map<String, Object> params = new HashMap<>();

			params.put("${usuario}", clt.getNombre());
			params.put("${saldo}", new BigDecimal("5000"));
			params.put("${abono}", new BigDecimal("400"));
			
			log.info("cliente:"+clt.getSociedad().getSociedad());

			envCorrPort.enviarCorreoAbono(clt.getCorreoElectronico(), params, clt.getSociedad());

		} else {

			throw new ApiException(500, "No se econtro el cliente");

		}

	}

	@Override
	public void enviarSaldoVencido(String usuario) {

		// Usuario
		UsuarioEntity us = usPort.buscarPorUsuario(usuario);
		if (us != null) {

			Map<String, Object> params = new HashMap<>();

			params.put("${usuario}", us.getNombre());
			params.put("${saldo}", new BigDecimal("5000"));

			envCorrPort.enviarCorreoSaldoVencido(us.getCorreoElectronico(), params, us.getSociedad());

		} else {

			throw new ApiException(500, "No se econtro tu usuario");

		}

	}

}
