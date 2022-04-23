/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service;

import java.util.List;
import java.util.Optional;

import com.cuenta.bancaria.cuenta.bancaria.model.Persona;

/**
 * @author JAVIM
 *
 */
public interface PersonaService {
	Persona create(Persona persona);

	List<Persona> read();

	Persona update(Persona persona);

	void delete(Long id);
	
	Optional<Persona> obtenerPorId(Long id);
	
	Optional<Persona> obtenerPorIdentificacion(String identificacion);

}
