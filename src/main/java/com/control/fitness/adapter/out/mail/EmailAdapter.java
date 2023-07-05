package com.control.fitness.adapter.out.mail;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.ConfiguracionPort;
import com.control.fitness.application.port.out.EnviarCorreoPort;
import com.control.fitness.domain.ConfiguracionEntity;
import com.control.fitness.domain.SociedadEntity;

@Service
@PropertySource(value = "classpath:configuraciones-global.properties")
public class EmailAdapter implements EnviarCorreoPort {

	Logger log = LoggerFactory.getLogger(EmailAdapter.class);

	@Value("${ruta.host}")
	private String rutaHost;

	@Value("${ruta.host.qas}")
	private String rutaHostQas;

	@Value("${ruta.host.pro}")
	private String rutaHostPro;

	@Value("${ruta.servidor}")
	private String rutaServidor;

	@Value("${ruta.servidor.qas}")
	private String rutaServidorQas;

	@Value("${ruta.servidor.pro}")
	private String rutaServidorPro;

	@Value("${ambiente}")
	private String ambiente;

	@Autowired
	private TemplateMail tpMail;

	@Autowired
	private EnvioCorreoAdapter envioCorreo;

	@Autowired
	private ConfiguracionPort configPort;


	@Override
	public void enviarCorreoRecuperacion(String email, Map<String, Object> params, SociedadEntity sociedad) {

		// Logo
		ConfiguracionEntity conf = configPort.obtenerConfiguracion(sociedad);
		if (conf != null) {

			System.out.println("ruta logo:" + rutaServidor() + conf.getLogo());
			params.put("${logo}", rutaServidor() + conf.getLogo());

		}
		params.put("${sociedad}", sociedad.getNombre());
		params.put("${url}", rutaServer());

		String template = "";

		try {
			template = tpMail.solveTemplate("email-templates/email-recuperacion-pwd.html", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		envioCorreo.enviarAsync(email, "Recuperación de contraseña de sistema " + sociedad.getNombre(), template);
	}

	@Override
	public void enviarCorreoAbono(String email, Map<String, Object> params, SociedadEntity sociedad) {

		// Logo
		ConfiguracionEntity conf = configPort.obtenerConfiguracion(sociedad);
		if (conf != null) {

			System.out.println("ruta logo:" + rutaServidor() + conf.getLogo());
			params.put("${logo}", rutaServidor() + conf.getLogo());

		}
		params.put("${sociedad}", sociedad.getNombre());
		params.put("${url}", rutaServer());

		String template = "";

		try {
			template = tpMail.solveTemplate("email-templates/email-abono.html", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		envioCorreo.enviarAsync(email, "Abono generado para " + sociedad.getNombre(), template);
	}

	@Override
	public void enviarCorreoNuevaCita(String email, Map<String, Object> params, SociedadEntity sociedad) {

		// Logo
		ConfiguracionEntity conf = configPort.obtenerConfiguracion(sociedad);
		if (conf != null) {

			System.out.println("ruta logo:" + rutaServidor() + conf.getLogo());
			params.put("${logo}", rutaServidor() + conf.getLogo());

		}
		params.put("${sociedad}", sociedad.getNombre());
		params.put("${url}", rutaServer());

		String template = "";

		try {
			template = tpMail.solveTemplate("email-templates/email-cita-nueva.html", params);
			// envioCorreo.enviarCorreo(email, "Abono generado para " +
			// sociedad.getNombre(), template);
			envioCorreo.enviarAsync(email, "Abono generado para " + sociedad.getNombre(), template);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void enviarCorreoSaldoVencido(String email, Map<String, Object> params, SociedadEntity sociedad) {

		// Logo
		ConfiguracionEntity conf = configPort.obtenerConfiguracion(sociedad);
		if (conf != null) {

			System.out.println("ruta logo:" + rutaServidor() + conf.getLogo());
			params.put("${logo}", rutaServidor() + conf.getLogo());

		}
		params.put("${sociedad}", sociedad.getNombre());
		params.put("${url}", rutaServer());

		String template = "";

		try {
			template = tpMail.solveTemplate("email-templates/email-saldo-vencido.html", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		envioCorreo.enviarAsync(email, "Saldo vencido de " + sociedad.getNombre(), template);

	}

	@Override
	public void enviarCorreoCalendario(String email, Map<String, Object> params, SociedadEntity sociedad) {
		try {
			envioCorreo.enviarCorreoCalendar("jricardo369@gmail.com", "test", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void enviarResetPassword(String email, Map<String, Object> params, SociedadEntity sociedad) {

		// Logo
		ConfiguracionEntity conf = configPort.obtenerConfiguracion(sociedad);
		if (conf != null) {

			System.out.println("ruta logo:" + rutaServidor() + conf.getLogo());
			params.put("${logo}", rutaServidor() + conf.getLogo());

		}
		params.put("${sociedad}", sociedad.getNombre());
		params.put("${url}", rutaServer());

		String template = "";

		try {
			template = tpMail.solveTemplate("email-templates/email-password-reset.html", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		envioCorreo.enviarAsync(email, "Reset password", template);
	}

	public String rutaServer() {
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
		return rutaHostFinal;
	}

	public String rutaServidor() {
		String rutaServidorFinal = "";
		switch (ambiente) {
		case "qas":
			rutaServidorFinal = rutaServidorQas;
			break;
		case "pro":
			rutaServidorFinal = rutaServidorPro;
			break;
		case "test":
			rutaServidorFinal = rutaServidor;
			break;
		}
		return rutaServidorFinal;
	}

	@Override
	public void testMail() {
		// try {
		// envioCorreo.sendEmail("jricardo369@gmail.com",
		// "jose.vazquezj@agilethought.com");
		// } catch (MessagingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
