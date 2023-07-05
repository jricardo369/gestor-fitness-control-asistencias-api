package com.control.fitness.application.port.in;

public interface CorreoElectronicoUseCase {

	public void enviarCorreoRecuperacion(String usuario);
	public void enviarCorreoAbono(int idCliente);
	public void enviarSaldoVencido(String usuario);
	
}
