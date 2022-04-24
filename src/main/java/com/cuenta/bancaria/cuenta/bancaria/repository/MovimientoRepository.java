/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuenta.bancaria.cuenta.bancaria.model.Movimiento;

/**
 * @author JAVIM
 *
 */
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	List<Movimiento> findByIdClienteAndIdCuenta(Long idCliente, Long idCuenta);
}
