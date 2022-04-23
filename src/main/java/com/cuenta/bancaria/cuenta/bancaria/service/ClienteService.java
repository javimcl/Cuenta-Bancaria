/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service;

import java.util.List;
import java.util.Optional;

import com.cuenta.bancaria.cuenta.bancaria.model.Cliente;

/**
 * @author JAVIM
 *
 */
public interface ClienteService {
	Cliente create(Cliente cliente);

	List<Cliente> read();

	Cliente update(Cliente cliente);

	void delete(Long id);

	Optional<Cliente> obtenerPorEstadoIdCliente(String estado, Long id);
	
	Optional<Cliente> obtenerPorId(Long id);
	
	Optional<Cliente> obtenerPorIdCliente(Long id);

}
