package com.kodigo.service;

import com.kodigo.model.Movimiento;

public interface IMovimientoService extends ICRUD<Movimiento, Integer> {

	Movimiento registrarTransaccional(Movimiento movimiento);
}