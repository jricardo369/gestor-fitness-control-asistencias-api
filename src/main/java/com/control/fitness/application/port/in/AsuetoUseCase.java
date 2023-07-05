package com.control.fitness.application.port.in;

import java.util.List;

import com.control.fitness.domain.Asueto;
import com.control.fitness.domain.AsuetoEntity;

public interface AsuetoUseCase {
     
	public List<AsuetoEntity> obtenerAsuetos(String fecha);
	public void crearAsueto(Asueto asueto,int tipo);
	public void eliminarAsueto(int idAsueto);
	
}
