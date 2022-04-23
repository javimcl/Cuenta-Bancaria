/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service;

import java.util.List;

import com.cuenta.bancaria.cuenta.bancaria.model.Movimiento;

/**
 * @author JAVIM
 *
 */
public interface MovimientoService {
	Movimiento create(Movimiento movimiento);

	List<Movimiento> read();

	Movimiento update(Movimiento movimiento);

	void delete(Long id);

}
