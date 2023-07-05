package com.control.fitness.adapter.out.sql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.AsuetoPort;
import com.control.fitness.application.port.out.jpa.AsuetoJpa;
import com.control.fitness.domain.AsuetoEntity;

@Service
public class AsuetoRepository implements AsuetoPort {

	@Autowired
	AsuetoJpa asuetoJpa;

	@Override
	public List<AsuetoEntity> obtenerAsuetos(Date fecha) {
		return asuetoJpa.findByFecha(fecha);
	}

	@Override
	public void crearAsueto(AsuetoEntity asueto) {
		asuetoJpa.save(asueto);
	}

	@Override
	public void crearVariosAsueto(List<AsuetoEntity> asuetos) {
		asuetoJpa.saveAll(asuetos);
	}

	@Override
	public void eliminarAsueto(AsuetoEntity asueto) {
		asuetoJpa.delete(asueto);
	}

	@Override
	public int esAsuetoGeneral(Date fecha) {
		return asuetoJpa.esAsuetoGeneral(fecha);
	}

	@Override
	public int asuetoDuplicado(int idClase, String fecha) {
		return asuetoJpa.asuetoDuplicado(fecha, idClase);
	}

	@Override
	public AsuetoEntity obtenerAsueto(int idAsueto) {
		return asuetoJpa.findByIdAsueto(idAsueto);
	}	

}
