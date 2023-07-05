package com.control.fitness.application;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.UtilidadesAdapter;
import com.control.fitness.application.port.in.MovimientosUseCase;
import com.control.fitness.application.port.out.EnviarCorreoPort;
import com.control.fitness.application.port.out.MovimientosPort;
import com.control.fitness.application.port.out.TipoMovimientosPort;
import com.control.fitness.application.port.out.UsuariosPort;
import com.control.fitness.domain.Adeudo;
import com.control.fitness.domain.Movimiento;
import com.control.fitness.domain.MovimientoEntity;
import com.control.fitness.domain.TipoMovimientoEntity;
import com.control.fitness.domain.UsuarioEntity;
@Service
public class MovimientosService implements MovimientosUseCase{
	
	Logger log = LoggerFactory.getLogger(MovimientosService.class);
	
	@Autowired
	private MovimientosPort movPort;
	
	@Autowired
	private EnviarCorreoPort envCorrPort;
	
	@Autowired
	private UsuariosPort usPort;
	
	@Autowired
	private TipoMovimientosPort tmovPort;

	@Override
	public List<MovimientoEntity> obtenerUltimosMovimientosDePaciente(int idPaciente) {
		return movPort.obtenerUltimosMovimientosDePaciente(idPaciente);
	}

	@Override
	public List<MovimientoEntity> obtenerMovimientosDePacientePorFecha(int idPaciente, Date fechai, Date fechaf) {
		return movPort.obtenerMovimientosDePacientePorFecha(idPaciente, fechai, fechaf);
	}
	
	@Override
	public List<MovimientoEntity> obtenerMovimientosPorFecha(Date fechai, Date fechaf) {
		return movPort.obtenerMovimientosPorFecha(fechai, fechaf);
	}

	@Override
	public void crearMovimiento(Movimiento mov) {
		
		MovimientoEntity me = convertirAMovimientoEntity(mov);
		
		
		if(me.getTipoMovimiento().getIdTipo() ==  1){
			
			Map<String, Object> params = new HashMap<>();

			
			params.put("${usuario}", me.getUsuario().getNombre());
			String saldo = UtilidadesAdapter.currencyFormat(movPort.obtenerAdeudo(me.getUsuario().getIdUsuario()));
			params.put("${saldo}", saldo);
			String abono = UtilidadesAdapter.currencyFormat( mov.getMonto());
			params.put("${abono}", abono);
			
			log.info("cliente:"+me.getUsuario().getSociedad().getSociedad());
			envCorrPort.enviarCorreoAbono(me.getUsuario().getCorreoElectronico(), params, me.getUsuario().getSociedad());
			
		}
		movPort.crearMovimiento(me);
	}
	
	@Override
	public MovimientoEntity obtenerMovimiento(int idMovimiento){
		return movPort.obtenerMovimiento(idMovimiento);
	}

	@Override
	public void actualizarMovimiento(Movimiento mov) {
		movPort.actualizarMovimiento(convertirAMovimientoEntity(mov));
	}

	@Override
	public void eliminarMovimiento(int idMov) {
		MovimientoEntity m = movPort.obtenerMovimiento(idMov);
		movPort.eliminarMovimiento(m);
	}

	@Override
	public Adeudo obtenerAdeudo(int idCliente) {
		Adeudo a = new Adeudo();
		a.setAdeudo(movPort.obtenerAdeudo(idCliente));
		return a;
	}
	
	private MovimientoEntity convertirAMovimientoEntity(Movimiento m){
		MovimientoEntity o = new MovimientoEntity();
		o.setFecha(m.getFecha());
		o.setIdMovimiento(m.getIdMovimiento());
		o.setMonto(m.getMonto());
		o.setDescripcion(m.getDescripcion());
		UsuarioEntity p = usPort.buscarPorId(m.getIdUsuario());
		o.setUsuario(p);
		TipoMovimientoEntity tm = tmovPort.obtenerMovimiento(m.getIdTipoMovimiento());
		o.setTipoMovimiento(tm);
		return o;
	}


}
