/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuenta.bancaria.cuenta.bancaria.model.Persona;
import com.cuenta.bancaria.cuenta.bancaria.service.PersonaService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JAVIM
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/personas")
public class PersonaController {
	private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

	@Autowired
	private PersonaService service;

	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody Persona persona) {
		try {
			Optional<Persona> personaEncontrada = service.obtenerPorIdentificacion(persona.getIdentificacion());
			if (personaEncontrada.isPresent()) {
				return new ResponseEntity<>("Un usuario ya existe con esta identificación", HttpStatus.BAD_REQUEST);
			}
			Persona clienteGuardado = service.create(persona);
			return new ResponseEntity<Persona>(clienteGuardado, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public ResponseEntity<List<Persona>> read() {
		List<Persona> list = service.read();
		return new ResponseEntity<List<Persona>>(list, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@Validated @RequestBody Persona persona) {
		try {
			Optional<Persona> personaEncontrada = service.obtenerPorId(persona.getIdPersona());
			if (personaEncontrada.isPresent()) {
				Persona personaGuardado = service.update(persona);
				return new ResponseEntity<Persona>(personaGuardado, HttpStatus.OK);
			}
			return new ResponseEntity<String>("No exist la persona", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			Optional<Persona> personaEncontrada = service.obtenerPorId(id);
			if (personaEncontrada.isPresent()) {
				service.delete(id);
				return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
			}
			return new ResponseEntity<String>(
					"No se puede eliminar el registro con id: " + id + " no existe el registro",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
