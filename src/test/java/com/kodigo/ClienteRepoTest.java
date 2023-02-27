package com.kodigo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kodigo.model.Cliente;
import com.kodigo.model.Persona;
import com.kodigo.repo.IClienteRepo;

@SpringBootTest
public class ClienteRepoTest {

	@Autowired
	private IClienteRepo repo;

	@Test
	void grabar() {
		Cliente cliente = new Cliente();
		cliente.setClave(Integer.toString("clave_nttdata".hashCode()));
		cliente.setEstado(true);

		Persona persona = new Persona();
		persona.setNombre("Estefania Onofre");
		persona.setGenero("F");
		persona.setEdad(29);
		persona.setIdentificacion("1715553698");
		persona.setDireccion("El Conde");
		persona.setTelefono("0955532123");

		cliente.setPersona(persona);

		Cliente retorno = repo.save(cliente);
		assertTrue(retorno.getIdCliente() != 0);
	}

	@Test
	void obtener() {
		Integer idCliente = 1;
		Optional<Cliente> optional = repo.findById(idCliente);
		Cliente retorno = optional.get();
		assertTrue(retorno.getIdCliente() != null);
	}

	@Test
	void modificar() {
		Integer idCliente = 2;
		Optional<Cliente> optional = repo.findById(idCliente);
		Cliente retorno = optional.get();
		retorno.setEstado(false);
		repo.save(retorno);
		assertTrue(!retorno.getEstado());
	}

	@Test
	void eliminar() {
		Integer idPersona = 999;
		assertThatThrownBy(() -> repo.deleteById(idPersona));
	}
}
