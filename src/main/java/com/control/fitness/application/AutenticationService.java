package com.control.fitness.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.control.fitness.ApiException;
import com.control.fitness.UtilidadesAdapter;
import com.control.fitness.application.port.in.GenerarTokenDeAutenticacionUseCase;
import com.control.fitness.application.port.out.GeneraTokenDeAutenticacionPort;
import com.control.fitness.application.port.out.UsuariosPort;
import com.control.fitness.domain.UsuarioEntity;

@Service
@PropertySource(value = "classpath:configuraciones-global.properties")
public class AutenticationService implements GenerarTokenDeAutenticacionUseCase {

	Logger log = LoggerFactory.getLogger(AutenticationService.class);

	@Value("${intentos-inicio-sesion}")
	private int intentosInicioSesion;

	@Autowired
	private GeneraTokenDeAutenticacionPort genTokenAutPort;

	@Autowired
	private UsuariosPort usPort;

	@Override
	public String autenticarUsuario(String usuario, String passwd) throws ApiException {

		String token = "";
		UsuarioEntity usuarioBD = usPort.buscarSoloPorUsuario(usuario);
		log.info("Usuario:"+usuario);
		log.info("Password:"+passwd);

		if (usuarioBD == null) {

			log.info("no se encontro usuario");
			throw new ApiException(500, "Usuario y/o Contraseña incorrectos");

		} else {
			
			log.info("Password sha256 bd:"+usuarioBD.getContrasenia());
			log.info("Password sha256 login:"+UtilidadesAdapter.sha256(usuario, passwd));

			if (!usuarioBD.getContrasenia().equals(UtilidadesAdapter.sha256(usuario, passwd))) {

				throw new ApiException(500, "Usuario y/o Contraseña incorrectos");

			} else {

				log.info(usuarioBD.getNombre());

				int intentos = usuarioBD.getIntentos() + 1;
				usuarioBD.setIntentos(intentos);
				if (intentosInicioSesion == intentos) {
					usuarioBD.setEstatus("2");
					usPort.actualizarUsuario(usuarioBD);
				}
				if (usuarioBD.getEstatus().equals("2")) {
					throw new ApiException(500, "El usuario esta inactivo por intentos fallidos");
				}
				if (usuarioBD.getEstatus().equals("2")) {
					throw new ApiException(500, "El usuario esta inactivo, contacte al Administrador");
				}

				token = genTokenAutPort.generarTokenDeAutenticacion(usuario);

			}

		}

		return token;
	}

}
