/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuenta.bancaria.cuenta.bancaria.model.Cuenta;

/**
 * @author JAVIM
 *
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	List<Cuenta> findByIdCliente(Long id);
}
