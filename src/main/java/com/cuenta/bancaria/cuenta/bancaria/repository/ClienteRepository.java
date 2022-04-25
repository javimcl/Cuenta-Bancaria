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

	Optional<Cliente> findByEstadoAndClienteId(String estado, Long id);

	Optional<Cliente> findByClienteId(Long id);

	Optional<Cliente> findByIdentificacion(String id);

}
