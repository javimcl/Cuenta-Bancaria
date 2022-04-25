/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.cuenta.bancaria.cuenta.bancaria.controller.dto.ReporteDto;
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

	Optional<Movimiento> obtenerPorId(Long id);

	List<Movimiento> obtenerPorClienteCuenta(Long idCliente, Long idCuenta);
	
	List<Movimiento> obtenerPorIdentificacionNumeroCuenta(String identificacion, int numeroCuenta);
	
	Double obtenerSumaValorClienteCuentaFecha(Long clienteId, Long idCuenta, String tipoMovimiento, Date fecha);
	
	List<ReporteDto> obtenerPorFechas(Date fechaInicial, Date fechaFinal);	

}
