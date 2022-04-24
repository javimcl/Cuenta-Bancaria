/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuenta.bancaria.cuenta.bancaria.model.Movimiento;

/**
 * @author JAVIM
 *
 */
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	List<Movimiento> findByIdClienteAndIdCuenta(Long idCliente, Long idCuenta);

	@Query(value = "SELECT c FROM Movimiento c WHERE c.cliente.identificacion= :identificacion AND c.cuenta.numero= :numero")
	List<Movimiento> buscarPorClienteCuenta(String identificacion, int numero);

	@Query(value = "SELECT COALESCE(SUM(c.valor),0) FROM Movimiento c WHERE c.cliente.clienteId= :clienteId AND c.cuenta.idCuenta= :idCuenta AND tipoMovimiento= :tipoMovimiento AND CONVERT(c.fecha, DATE) = CONVERT(:fecha, DATE)")
	Double sumaValorPorClienteCuentaFecha(Long clienteId, Long idCuenta, String tipoMovimiento, Date fecha);
}
