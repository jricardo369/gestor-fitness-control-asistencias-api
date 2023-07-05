package com.control.fitness.application.port.out;

import java.util.Date;
import java.util.List;

import com.control.fitness.domain.AsuetoEntity;

public interface AsuetoPort {
	
	public List<AsuetoEntity> obtenerAsuetos(Date fecha);
	public AsuetoEntity obtenerAsueto(int idAsueto);
	public void crearAsueto(AsuetoEntity asueto);
	public void crearVariosAsueto(List<AsuetoEntity> asuetos);
	public void eliminarAsueto(AsuetoEntity asueto);
	public int esAsuetoGeneral(Date fecha);
	public int asuetoDuplicado(int idClase, String fecha);

}
