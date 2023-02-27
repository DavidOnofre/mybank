package com.kodigo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kodigo.model.Cliente;
import com.kodigo.model.Persona;
import com.kodigo.service.IClienteService;

@SpringBootTest
public class ClienteServiceTest {

	@Autowired
	private IClienteService service;

	@Test
	void grabar() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setClave(Integer.toString("clave_nttdata".hashCode()));
		cliente.setEstado(true);

		Persona persona = new Persona();
		persona.setNombre("Priscila Onofre");
		persona.setGenero("F");
		persona.setEdad(21);
		persona.setIdentificacion("1715553698");
		persona.setDireccion("El Conde");
		persona.setTelefono("0955532123");

		cliente.setPersona(persona);

		Cliente retorno = service.registrar(cliente);
		assertTrue(retorno.getIdCliente() != 0);
	}

	@Test
	void obtener() throws Exception {
		Integer idCliente = 1;
		Cliente retorno = service.listarPorId(idCliente);
		assertTrue(retorno.getIdCliente() != null);
	}

	@Test
	void modificar() throws Exception {
		Integer idCliente = 2;
		Cliente retorno = service.listarPorId(idCliente);
		retorno.setEstado(false);
		service.registrar(retorno);
		assertTrue(!retorno.getEstado());
	}

	@Test
	void eliminar() {
		Integer idPersona = 999;
		assertThatThrownBy(() -> service.eliminar(idPersona));
	}
}
