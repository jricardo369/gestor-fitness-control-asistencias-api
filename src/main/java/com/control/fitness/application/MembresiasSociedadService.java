package com.control.fitness.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.in.MembresiasSociedadUseCase;
import com.control.fitness.application.port.out.MembresiaPort;
import com.control.fitness.application.port.out.MembresiasSociedadPort;
import com.control.fitness.application.port.out.SociedadPort;
import com.control.fitness.domain.MembresiaEntity;
import com.control.fitness.domain.MembresiaSociedad;
import com.control.fitness.domain.MembresiaSociedadEntity;
import com.control.fitness.domain.SociedadEntity;

@Service
public class MembresiasSociedadService implements MembresiasSociedadUseCase {
	
	@Autowired
	private MembresiasSociedadPort memSocPort;
	
	@Autowired
	private MembresiaPort memPort;
	
	@Autowired
	private SociedadPort socPort;

	@Override
	public List<MembresiaSociedad> obtenerMembresiasSociedades() {
		return convertirAObjeto(memSocPort.obtenerMembresiasSociedades());
	}

	@Override
	public List<MembresiaSociedad> obtenerMembresiasSociedadesDe(int idSociedad) {
		return convertirAObjeto(memSocPort.obtenerMembresiasSociedadesDe(idSociedad));
	}

	@Override
	public void crearMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad) {
		memSocPort.crearMembresiaSociedad(membresiaSociedad);
	}

	@Override
	public void actualizarMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad) {
		memSocPort.actualizarMembresiaSociedad(membresiaSociedad);
	}

	@Override
	public void eliminarMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad) {
		memSocPort.eliminarMembresiaSociedad(membresiaSociedad);
	}
	
	private List<MembresiaSociedad> convertirAObjeto(List<MembresiaSociedadEntity> memebresias){
		List<MembresiaSociedad> list = new ArrayList<>();
		for (MembresiaSociedadEntity msE : memebresias) {
			MembresiaSociedad ms = new MembresiaSociedad();
			MembresiaEntity m = memPort.obtenerMembresiaPorId(msE.getId_membresia());
			ms.setMembresia(m.getDescripcion());
			SociedadEntity s = socPort.buscarSociedadPorId(msE.getId_sociedad());
			ms.setSociedad(s.getNombre());
			ms.setFechaCaducidad(msE.getFechaCaducidad());
			list.add(ms);
			
		}
		return list;
	}

}
