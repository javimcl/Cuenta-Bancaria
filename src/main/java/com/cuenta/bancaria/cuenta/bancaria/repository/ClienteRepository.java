/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuenta.bancaria.cuenta.bancaria.model.Cliente;

/**
 * @author JAVIM
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Optional<Cliente> findByEstadoAndIdCliente(String estado, Long id);
	
	Optional<Cliente> findByIdCliente(Long id);
	

}
