package com.kodigo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.model.Cliente;
import com.kodigo.model.Cuenta;
import com.kodigo.repo.IClienteRepo;
import com.kodigo.repo.ICuentaRepo;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.service.ICuentaService;

@Service
public class CuentaServiceImpl extends CRUDImpl<Cuenta, Integer> implements ICuentaService {

	@Autowired
	private ICuentaRepo repo;

	@Autowired
	private IClienteRepo clienteRepo;

	@Override
	protected IGenericRepo<Cuenta, Integer> getRepo() {
		return repo;
	}

	@Override
	public Cuenta registrarCuenta(Cuenta cuenta) throws Exception {

		Cliente cliente = obtenerCliente(cuenta);
		cuenta.setCliente(cliente);
		cuenta.setSaldoDisponible(cuenta.getSaldoInicial());
		return repo.save(cuenta);
	}

	//Método usado para obtener el cliente de una cuenta
	private Cliente obtenerCliente(Cuenta cuenta) {
		Optional<Cliente> optional = clienteRepo.findById(cuenta.getCliente().getIdCliente());
		return optional.get();
	}

}
