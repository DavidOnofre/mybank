package com.kodigo.service;

import com.kodigo.model.Movimiento;
import com.kodigo.model.dto.MovimientoDTO;
import com.kodigo.model.dto.ReporteDTO;

public interface IMovimientoService extends ICRUD<Movimiento, Integer> {

	Movimiento registrarTransaccional(Movimiento movimiento);
	
	MovimientoDTO generarReporte(ReporteDTO reporteDTO);
}