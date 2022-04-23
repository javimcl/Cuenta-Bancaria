/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuenta.bancaria.cuenta.bancaria.model.Cuenta;

/**
 * @author JAVIM
 *
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	Optional<Cuenta> findByIdCliente(Long id);
}
