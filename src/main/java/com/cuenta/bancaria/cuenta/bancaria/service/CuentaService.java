/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service;

import java.util.List;

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

}
