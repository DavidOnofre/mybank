package com.kodigo.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kodigo.model.Movimiento;

public interface IMovimientoRepo extends IGenericRepo<Movimiento, Integer> {
	
	@Query("FROM Movimiento c WHERE c.fecha BETWEEN :fechaDesde AND :fechaHasta")
	List<Movimiento> buscarPorFecha(@Param("fechaDesde") LocalDateTime fechaDesde,
			@Param("fechaHasta") LocalDateTime fechaHasta);

}
