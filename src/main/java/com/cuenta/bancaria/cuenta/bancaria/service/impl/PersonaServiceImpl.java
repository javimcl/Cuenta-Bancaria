/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuenta.bancaria.cuenta.bancaria.model.Persona;
import com.cuenta.bancaria.cuenta.bancaria.repository.PersonaRepository;
import com.cuenta.bancaria.cuenta.bancaria.service.PersonaService;

/**
 * @author JAVIM
 *
 */
@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Persona create(Persona persona) {
		return personaRepository.save(persona);
	}

	@Override
	public List<Persona> read() {
		return personaRepository.findAll();
	}

	@Override
	public Persona update(Persona persona) {
		return personaRepository.save(persona);
	}

	@Override
	public void delete(Long id) {
		personaRepository.deleteById(id);

	}

	@Override
	public Optional<Persona> obtenerPorId(Long id) {
		return personaRepository.findById(id);
	}

	@Override
	public Optional<Persona> obtenerPorIdentificacion(String identificacion) {
		return personaRepository.findByIdentificacion(identificacion);
	}




}
