package com.kodigo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodigo.exception.ModeloNotFoundException;
import com.kodigo.model.Movimiento;
import com.kodigo.service.IMovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	private IMovimientoService service;

	@GetMapping
	public ResponseEntity<List<Movimiento>> listar() throws Exception{
		List<Movimiento> lista = service.listar();
		return new ResponseEntity<List<Movimiento>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movimiento> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Movimiento obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("id no encontrado: " + id);
		}
		
		return new ResponseEntity<Movimiento>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Movimiento> registrar(@Valid @RequestBody Movimiento movimiento) throws Exception{
		//Movimiento obj = service.registrar(movimiento);
		Movimiento obj = service.registrarTransaccional(movimiento);
		
		return new ResponseEntity<Movimiento>(obj, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Movimiento> modificar(@Valid @RequestBody Movimiento movimiento) throws Exception{
		Movimiento obj = service.modificar(movimiento);
		return new ResponseEntity<Movimiento>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable("id") Integer id) throws Exception{
		Movimiento obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("id no encontrado" + id);
		} 
		
		service.eliminar(id);
		 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
