package com.kodigo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kodigo.model.Persona;
import com.kodigo.repo.IPersonaRepo;

@SpringBootTest
public class PersonaRepoTest {

	@Autowired
	private IPersonaRepo repo;

	@Test
	void grabarPersona() {
		Persona persona = new Persona();
		persona.setNombre("Jonatan Onofre");
		persona.setGenero("M");
		persona.setEdad(33);
		persona.setIdentificacion("1719555987");
		persona.setDireccion("Quicentro");
		persona.setTelefono("0955567845");

		Persona retorno = repo.save(persona);

		assertTrue(retorno.getIdPersona() != 0);
	}

	@Test
	void obtenerPersona() {
		Integer idPesona = 1;
		Optional<Persona> optional = repo.findById(idPesona);
		Persona retorno = optional.get();
		assertTrue(retorno.getIdPersona() != null);
	}

	@Test
	void modificarPersona() {
		Integer idPesona = 2;
		Optional<Persona> optional = repo.findById(idPesona);
		Persona retorno = optional.get();
		retorno.setNombre("Darío Onofre");

		repo.save(retorno);

		assertTrue(retorno.getNombre() == "Darío Onofre");
	}

	@Test
	void eliminarPersona() {
		Integer idPersona = 999;
		repo.deleteById(idPersona);
		assertThatThrownBy(() -> repo.deleteById(idPersona));
	}
}
