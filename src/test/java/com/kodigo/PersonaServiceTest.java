package com.kodigo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kodigo.model.Persona;
import com.kodigo.service.IPersonaService;

@SpringBootTest
public class PersonaServiceTest {

	@Autowired
	private IPersonaService service;

	@Test
	void grabarPersona() throws Exception {
		Persona persona = new Persona();
		persona.setNombre("Jonatan Onofre");
		persona.setGenero("M");
		persona.setEdad(33);
		persona.setIdentificacion("1719555987");
		persona.setDireccion("Quicentro");
		persona.setTelefono("0955567845");
		Persona retorno = service.registrar(persona);
		assertTrue(retorno.getIdPersona() != 0);
	}

	@Test
	void obtenerPersona() throws Exception {
		Integer idPesona = 1;
		Persona retorno = service.listarPorId(idPesona);
		assertTrue(retorno.getIdPersona() != null);
	}

	@Test
	void modificarPersona() throws Exception {
		Integer idPesona = 2;
		Persona retorno = service.listarPorId(idPesona);
		retorno.setNombre("Darío Onofre");
		service.registrar(retorno);
		assertTrue(retorno.getNombre() == "Darío Onofre");
	}

	@Test
	void eliminarPersona() {
		Integer idPersona = 999;
		assertThatThrownBy(() -> service.eliminar(idPersona));
	}
}
