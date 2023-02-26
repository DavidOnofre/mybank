package com.kodigo.service;

import com.kodigo.model.Cuenta;

public interface ICuentaService extends ICRUD<Cuenta, Integer> {

	Cuenta registrarCuenta(Cuenta cuenta) throws Exception;

}