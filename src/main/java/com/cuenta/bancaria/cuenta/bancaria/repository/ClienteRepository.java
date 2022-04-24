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

//	List<Cliente> findByIdPersona(Long id);

//	@Query(value = "SELECT c FROM Cliente c WHERE c.persona.idPersona= :idPersona")
//	List<Cliente> buscarPorPersona(Long idPersona);

	Optional<Cliente> findByIdentificacion(String id);

}
