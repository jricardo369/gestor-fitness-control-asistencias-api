package com.control.fitness.application.port.out;

import java.util.Map;

import com.control.fitness.domain.SociedadEntity;

public interface EnviarCorreoPort {
	
	public void enviarCorreoRecuperacion(String email, Map<String, Object> params,SociedadEntity sociedad);
	public void enviarCorreoAbono(String email, Map<String, Object> params,SociedadEntity sociedad);
	public void enviarCorreoSaldoVencido(String email, Map<String, Object> params,SociedadEntity sociedad);
	public void enviarCorreoNuevaCita(String email, Map<String, Object> params, SociedadEntity sociedad);
	public void enviarCorreoCalendario(String email, Map<String, Object> params, SociedadEntity sociedad);
	public void enviarResetPassword(String email, Map<String, Object> params, SociedadEntity sociedad);
	public void testMail();
	

}
