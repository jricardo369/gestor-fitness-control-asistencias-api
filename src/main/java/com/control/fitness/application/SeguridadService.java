package com.control.fitness.application;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.control.fitness.application.SeguridadService;
import com.control.fitness.application.port.out.ConfiguracionPort;
import com.control.fitness.domain.ConfiguracionEntity;
import com.control.fitness.ApiException;
import com.control.fitness.MessageAdapter;
import com.control.fitness.UtilidadesAdapter;
import com.control.fitness.adapter.out.mail.TemplateMail;
import com.control.fitness.application.port.in.SeguridadUseCase;
import com.control.fitness.application.port.in.UsuariosUseCase;
import com.control.fitness.application.port.out.ArchivosPort;
import com.control.fitness.application.port.out.EnviarCorreoPort;
import com.control.fitness.application.port.out.SociedadPort;
import com.control.fitness.domain.CambiosUsuario;
import com.control.fitness.domain.SociedadEntity;
import com.control.fitness.domain.UsuarioEntity;

@Service
@PropertySource(value = "classpath:configuraciones-global.properties")
public class SeguridadService implements SeguridadUseCase {

	Logger log = LoggerFactory.getLogger(SeguridadService.class);

	@Value("${ruta.host}")
	private String rutaHost;

	@Value("${ruta.host.qas}")
	private String rutaHostQas;

	@Autowired
	private TemplateMail tpMail;

	@Value("${ruta.host.pro}")
	private String rutaHostPro;

	@Value("${ambiente}")
	private String ambiente;

	@Autowired
	private ConfiguracionPort configPort;

	@Autowired
	private ArchivosPort archPort;

	@Autowired
	private UsuariosUseCase usrUse;

	@Autowired
	private EnviarCorreoPort enviarCorreoPort;

	@Override
	public void cambioPassword(CambiosUsuario cambiosUsuario) {
		if (!cambiosUsuario.getCont().equals(cambiosUsuario.getCont2())) {
			throw new ApiException(409, "Las contraseñas no coinciden");
		} else {
			UsuarioEntity usuario = usrUse.obtenerPorIdUsuario(cambiosUsuario.getIdUsuario());
			usuario.setContrasenia(UtilidadesAdapter.sha256(usuario.getUsuario(), cambiosUsuario.getCont()));
			usrUse.actualizarUsuario(usuario);
		}
	}

	@Override
	public void cambioEmail(CambiosUsuario cambiosUsuario) {

		if (!cambiosUsuario.getCorreo().equals(cambiosUsuario.getCorreo2())) {
			throw new ApiException(409, "Los correos no coinciden");
		} else {
			UsuarioEntity usuario = usrUse.obtenerPorIdUsuario(cambiosUsuario.getIdUsuario());
			usuario.setCorreoElectronico(cambiosUsuario.getCorreo());
			usrUse.actualizarUsuario(usuario);
		}

	}

	@Override
	public void enviarCorreoResetPassword(String email) {

		UsuarioEntity ue = usrUse.buscarPorCorreo(email);
		String rutaHostFinal = "";
		
		switch (ambiente) {
		case "qas":
			rutaHostFinal = rutaHostQas;
			break;
		case "pro":
			rutaHostFinal = rutaHostPro;
			break;
		case "test":
			rutaHostFinal = rutaHost;
			break;
		}

		String uuid = UUID.randomUUID().toString();
		String url = rutaHostFinal + "dental/seguridad/reset-password/" + uuid+"/"+ue.getIdUsuario();
		System.out.println("url:" + url);

		Map<String, Object> params = new HashMap<>();
		params.put("${password-reset-url}", url);

		
		System.out.println("soc:" + ue.getSociedad().getSociedad());
		enviarCorreoPort.enviarResetPassword(email, params, ue.getSociedad());

	}

	@Override
	public String obtenerFormReset(String uuid,int idUsuario) {

		String template = "";

		String rutaHostFinal = "";
		
		switch (ambiente) {
		case "qas":
			rutaHostFinal = rutaHostQas;
			break;
		case "pro":
			rutaHostFinal = rutaHostPro;
			break;
		case "test":
			rutaHostFinal = rutaHost;
			break;
		}

		Map<String, Object> params = new HashMap<>();
		// Logo
		UsuarioEntity ue = usrUse.obtenerPorIdUsuario(idUsuario);
		ConfiguracionEntity conf = configPort.obtenerConfiguracion(ue.getSociedad());
		if (conf != null) {

			log.info("ruta logo:" + conf.getLogo());
			byte[] logoByte = archPort.obtenerArchivo(conf.getLogo());
			if (logoByte != null) {
				String encodedString = Base64.getEncoder().encodeToString(logoByte);
				params.put("${logo}", encodedString);
			} else {
				params.put("${logo}", "");
			}

		}

		String action = rutaHostFinal + "dental/seguridad/reset-password-request/" + uuid +"/"+idUsuario;
		params.put("${action}", action);
		params.put("${error}", "");

		try {
			template = tpMail.solveTemplate("html-templates/app-password-reset-form.html", params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(template);
		return template;
	}

	@Override
	public String enviarForRest(String uuid, String password, String passwordRepeat,int idUsuario) {
		String template = "";

		Map<String, Object> params = new HashMap<>();
		UsuarioEntity ue = usrUse.obtenerPorIdUsuario(idUsuario);
		ConfiguracionEntity conf = configPort.obtenerConfiguracion(ue.getSociedad());
		if (conf != null) {

			log.info("ruta logo:" + conf.getLogo());
			byte[] logoByte = archPort.obtenerArchivo(conf.getLogo());
			if (logoByte != null) {
				String encodedString = Base64.getEncoder().encodeToString(logoByte);
				params.put("${logo}", encodedString);
			} else {
				params.put("${logo}", "");
			}

		}
		params.put("${request-type}", "Reseteo de contraseña");
		params.put("${user}", ue.getSociedad().getNombre());
		System.err.println("PASSS:"+password);
		
		ue.setContrasenia(UtilidadesAdapter.sha256(ue.getUsuario(), password));
		usrUse.actualizarUsuario(ue);

		try {
			template = tpMail.solveTemplate("html-templates/app-confirmation-message.html", params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(template);
		return template;
	}

}
