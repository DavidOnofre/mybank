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
import com.kodigo.model.Cuenta;
import com.kodigo.service.ICuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private ICuentaService service;

	@GetMapping
	public ResponseEntity<List<Cuenta>> listar() throws Exception {
		List<Cuenta> lista = service.listar();
		return new ResponseEntity<List<Cuenta>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Cuenta obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("id no encontrado: " + id);
		}

		return new ResponseEntity<Cuenta>(obj, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Cuenta> registrar(@Valid @RequestBody Cuenta cuenta) throws Exception {
		Cuenta obj = service.registrarCuenta(cuenta);
		return new ResponseEntity<Cuenta>(obj, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Cuenta> modificar(@Valid @RequestBody Cuenta cuenta) throws Exception {
		Cuenta obj = service.modificar(cuenta);
		return new ResponseEntity<Cuenta>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable("id") Integer id) throws Exception {
		Cuenta obj = service.listarPorId(id);
		if (obj == null) {
			throw new ModeloNotFoundException("id no encontrado" + id);
		}

		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
