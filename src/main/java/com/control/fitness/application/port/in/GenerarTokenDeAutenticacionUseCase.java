package com.control.fitness.application.port.in;

import com.control.fitness.ApiException;

public interface GenerarTokenDeAutenticacionUseCase {

	public String autenticarUsuario(String usuario,String passwd) throws ApiException;
	
}
