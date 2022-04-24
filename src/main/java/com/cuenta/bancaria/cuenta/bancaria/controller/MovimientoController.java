/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cuenta.bancaria.cuenta.bancaria.model.Movimiento;
import com.cuenta.bancaria.cuenta.bancaria.service.MovimientoService;

/**
 * @author JAVIM
 *
 */
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

	private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

	@Autowired
	private MovimientoService service;

	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody MovimientoEntradaDto movimientoEntradaDto) {
		
		try {
			
			Movimiento movimiento = new Movimiento();
			movimiento.setIdCliente(movimientoEntradaDto.getIdCliente());
			movimiento.setIdCuenta(movimientoEntradaDto.getIdCuenta());
			movimiento.setSaldo(movimientoEntradaDto.getSaldo());
			movimiento.setTipoMovimiento(movimientoEntradaDto.getTipoMovimiento());
			movimiento.setFecha(new Date());
			movimiento.setValor(movimientoEntradaDto.getValor());
			Movimiento movimientoGuardado = service.create(movimiento);
			return new ResponseEntity<Movimiento>(movimientoGuardado, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> obtenerPorClienteCuenta(@Validated @RequestBody MovimientoEntradaDto movimientoEntradaDto) {
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
