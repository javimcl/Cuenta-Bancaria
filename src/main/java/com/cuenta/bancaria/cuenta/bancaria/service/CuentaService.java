/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service;

import java.util.List;
import java.util.Optional;

import com.cuenta.bancaria.cuenta.bancaria.model.Cuenta;

/**
 * @author JAVIM
 *
 */
public interface CuentaService {
	Cuenta create(Cuenta cuenta);

	List<Cuenta> read();

	Cuenta update(Cuenta cuenta);

	void delete(Long id);
	
	Optional<Cuenta> obtenerPorId(Long id);
	
	List<Cuenta> obtenerPorCliente(Long id);
	
	
	

}
