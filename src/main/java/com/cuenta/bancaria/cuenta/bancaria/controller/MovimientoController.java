/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuenta.bancaria.cuenta.bancaria.controller.dto.MovimientoEntradaDto;
import com.cuenta.bancaria.cuenta.bancaria.enumeration.TipoMovimientoEnum;
import com.cuenta.bancaria.cuenta.bancaria.model.Cliente;
import com.cuenta.bancaria.cuenta.bancaria.model.Cuenta;
import com.cuenta.bancaria.cuenta.bancaria.model.Movimiento;
import com.cuenta.bancaria.cuenta.bancaria.service.ClienteService;
import com.cuenta.bancaria.cuenta.bancaria.service.CuentaService;
import com.cuenta.bancaria.cuenta.bancaria.service.MovimientoService;

/**
 * @author JAVIM
 *
 */
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

	private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

	private static final int limiteDiario = 1000;

	@Autowired
	private MovimientoService service;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CuentaService cuentaService;

	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody MovimientoEntradaDto movimientoEntradaDto) {

		try {
			if (ObjectUtils.isEmpty(movimientoEntradaDto.getIdentificacion())
					|| ObjectUtils.isEmpty(movimientoEntradaDto.getNumeroCuenta())) {
				return new ResponseEntity<>("La identificación y el número de cuenta son obligatorios",
						HttpStatus.BAD_REQUEST);
			}
			Optional<Cliente> cliente = clienteService
					.obtenerPorIdentificacion(movimientoEntradaDto.getIdentificacion());
			if (ObjectUtils.isEmpty(cliente)) {
				return new ResponseEntity<>("El cliente no existe con la identificación", HttpStatus.BAD_REQUEST);
			}
			Optional<Cuenta> cuenta = cuentaService.obtenerPorNumeroCuenta(movimientoEntradaDto.getNumeroCuenta());
			if (ObjectUtils.isEmpty(cuenta)) {
				return new ResponseEntity<>("La cuenta no existe con el número de cuenta", HttpStatus.BAD_REQUEST);
			}
			Movimiento movimiento = new Movimiento();
			movimiento.setIdCliente(cliente.get().getClienteId());
			movimiento.setIdCuenta(cuenta.get().getIdCuenta());
			BigDecimal saldo;
			if (TipoMovimientoEnum.CREDITO.getDescripcion()
					.equalsIgnoreCase(movimientoEntradaDto.getTipoMovimiento())) {
				saldo = cuenta.get().getSaldoInicial().add(BigDecimal.valueOf(movimientoEntradaDto.getValor()));
			} else if (TipoMovimientoEnum.DEBITO.getDescripcion()
					.equalsIgnoreCase(movimientoEntradaDto.getTipoMovimiento())) {
				if (BigDecimal.ZERO.compareTo(cuenta.get().getSaldoInicial()) == 0) {
					return new ResponseEntity<>("Saldo no disponible", HttpStatus.BAD_REQUEST);
				}
				Double sumaDiariaDebito = service.obtenerSumaValorClienteCuentaFecha(cliente.get().getClienteId(),
						cuenta.get().getIdCuenta(), TipoMovimientoEnum.DEBITO.getDescripcion(), new Date());
				if (limiteDiario <= sumaDiariaDebito.doubleValue() + movimientoEntradaDto.getValor()) {
					return new ResponseEntity<>("Cupo diario Excedido", HttpStatus.BAD_REQUEST);
				}
				saldo = cuenta.get().getSaldoInicial().subtract(BigDecimal.valueOf(movimientoEntradaDto.getValor()));
			} else {
				return new ResponseEntity<>("Tipo de movimiento no encontrado", HttpStatus.BAD_REQUEST);
			}

			movimiento.setSaldo(saldo);
			movimiento.setTipoMovimiento(movimientoEntradaDto.getTipoMovimiento());
			movimiento.setFecha(new Date());
			movimiento.setValor(movimientoEntradaDto.getValor());
			Movimiento movimientoGuardado = service.create(movimiento);
			cuenta.get().setSaldoInicial(saldo);
			cuentaService.update(cuenta.get());
			return new ResponseEntity<Movimiento>(movimientoGuardado, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> obtenerPorClienteCuenta(
			@Validated @RequestBody MovimientoEntradaDto movimientoEntradaDto) {
		try {

			List<Movimiento> listadoMovimiento = service.obtenerPorClienteCuenta(movimientoEntradaDto.getIdCliente(),
					movimientoEntradaDto.getIdCuenta());
			if (null == listadoMovimiento || listadoMovimiento.isEmpty()) {
				return new ResponseEntity<>("No existen movimientos con lo parametros indicados.", HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Movimiento>>(listadoMovimiento, HttpStatus.OK);
			}

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@Validated @RequestBody Movimiento movimiento) {
		try {
			Optional<Movimiento> movimientoEncontrado = service.obtenerPorId(movimiento.getIdMovimiento());
			if (movimientoEncontrado.isPresent()) {
				Movimiento movimientoGuardado = service.update(movimiento);
				return new ResponseEntity<Movimiento>(movimientoGuardado, HttpStatus.OK);
			}
			return new ResponseEntity<>("No se encuentra en movimiento", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			Optional<Movimiento> movimiento = service.obtenerPorId(id);
			if (movimiento.isPresent()) {
				service.delete(id);
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<String>(
					"No se puede eliminar el movimiento con id: " + id + " no existe el registro",
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
