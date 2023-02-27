package com.kodigo.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.exception.ModeloNotFoundException;
import com.kodigo.model.Cuenta;
import com.kodigo.model.Movimiento;
import com.kodigo.model.Persona;
import com.kodigo.model.dto.MovimientoDTO;
import com.kodigo.model.dto.ReporteDTO;
import com.kodigo.repo.ICuentaRepo;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.repo.IMovimientoRepo;
import com.kodigo.repo.IPersonaRepo;
import com.kodigo.service.IMovimientoService;

@Service
public class MovimientoServiceImpl extends CRUDImpl<Movimiento, Integer> implements IMovimientoService {

	@Autowired
	private IMovimientoRepo movimientoRepo;

	@Autowired
	private ICuentaRepo cuentaRepo;

	@Autowired
	private IPersonaRepo personaRepo;

	@Override
	protected IGenericRepo<Movimiento, Integer> getRepo() {
		return movimientoRepo;
	}

	@Transactional
	@Override
	public Movimiento registrarTransaccional(Movimiento movimiento) {
		Cuenta cuenta = obtenerCuenta(movimiento);
		movimiento.setCuenta(cuenta);
		registrarSaldoCuenta(movimiento, cuenta);
		registrarMovimiento(movimiento, cuenta);
		return movimiento;
	}

	// Método que recupera la cuenta que se va a utilizar en un movimiento.
	private Cuenta obtenerCuenta(Movimiento movimiento) {
		Optional<Cuenta> cuentaOptional = cuentaRepo.findById(movimiento.getCuenta().getIdCuenta());
		return cuentaOptional.get();
	}

	// Método que dependiendo del tipo de movimiento suma o resta el valor del
	// movimiento.
	private void registrarMovimiento(Movimiento movimiento, Cuenta cuenta) {
		switch (movimiento.getTipoMovimiento()) {
		case "retiro":
			
			movimiento.setSaldo(cuenta.getSaldoDisponible());
			movimientoRepo.save(movimiento);
			
			break;
		case "deposito":
			
			movimiento.setSaldo(cuenta.getSaldoDisponible());
			movimientoRepo.save(movimiento);
			
			break;
		default:
			throw new ModeloNotFoundException("valor tipoMovimiento no valido");
		}
	}

	// Método que dependiendo del tipo de movimiento suma o resta al saldo
	// disponible en la cuenta.
	private void registrarSaldoCuenta(Movimiento movimiento, Cuenta cuenta) {
		switch (movimiento.getTipoMovimiento()) {
		case "retiro":

			BigDecimal saldoRetiro = cuenta.getSaldoDisponible().subtract(movimiento.getValor());
			if (saldoRetiro.compareTo(BigDecimal.ZERO) <= 0) {
				throw new ModeloNotFoundException("Saldo no disponible");
			}

			cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().subtract(movimiento.getValor()));
			cuentaRepo.save(cuenta);
			
			break;
		case "deposito":
			
			cuenta.setSaldoDisponible(cuenta.getSaldoDisponible().add(movimiento.getValor()));
			cuentaRepo.save(cuenta);
			
			break;
		}

	}

	@Override
	public MovimientoDTO generarReporte(ReporteDTO parametrosDTO) {

		MovimientoDTO dto = new MovimientoDTO();
		List<Movimiento> movimientos = movimientoRepo.buscarPorFecha(parametrosDTO.getFechaDesde(),
				parametrosDTO.getFechaHasta());

		for (Movimiento movimiento : movimientos) {
			if (movimiento.getCuenta().getCliente().getIdCliente() == parametrosDTO.getIdCliente()) {

				Persona persona = obtenerPersona(parametrosDTO.getIdCliente());
				Cuenta cuenta = obtenerCuenta(movimiento.getCuenta().getIdCuenta());
				dto.setCliente(persona.getNombre());
				dto.setNumeroCuenta(cuenta.getNumeroCuenta());
				dto.setTipoMovimiento(movimiento.getTipoMovimiento());
				dto.setSaldoInicial(cuenta.getSaldoInicial());
				dto.setEstado(cuenta.getEstado());
				dto.setSaldoDisponible(cuenta.getSaldoDisponible());
				dto.setFecha(movimiento.getFecha());
				dto.setValor(movimiento.getValor());
			}
		}

		return dto;
	}

	private Cuenta obtenerCuenta(Integer idCuenta) {
		Optional<Cuenta> cuentaOptional = cuentaRepo.findById(idCuenta);
		return cuentaOptional.get();
	}

	private Persona obtenerPersona(Integer idCliente) {
		Optional<Persona> optional = personaRepo.findById(idCliente);
		return optional.get();
	}

}
