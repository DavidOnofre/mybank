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
import com.kodigo.model.Cliente;
import com.kodigo.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService service;

	@GetMapping
	public ResponseEntity<List<Cliente>> listar() throws Exception{
		List<Cliente> lista = service.listar();
		return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Cliente obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("id no encontrado: " + id);
		}
		
		return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> registrar(@Valid @RequestBody Cliente persona) throws Exception{
		Cliente obj = service.registrar(persona);
		return new ResponseEntity<Cliente>(obj, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Cliente> modificar(@Valid @RequestBody Cliente persona) throws Exception{
		Cliente obj = service.modificar(persona);
		return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable("id") Integer id) throws Exception{
		Cliente obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("id no encontrado" + id);
		} 
		
		service.eliminar(id);
		 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
