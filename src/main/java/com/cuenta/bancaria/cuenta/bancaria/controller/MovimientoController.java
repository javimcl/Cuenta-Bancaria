/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller;

import java.util.List;

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

import com.cuenta.bancaria.cuenta.bancaria.model.Movimiento;
import com.cuenta.bancaria.cuenta.bancaria.service.MovimientoService;

/**
 * @author JAVIM
 *
 */
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

	@Autowired
	private MovimientoService service;

	@PostMapping
	public ResponseEntity<Movimiento> create(@Validated @RequestBody Movimiento movimiento) {
		Movimiento movimientoGuardado = service.create(movimiento);
		return new ResponseEntity<Movimiento>(movimientoGuardado, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Movimiento>> read() {
		List<Movimiento> list = service.read();
		return new ResponseEntity<List<Movimiento>>(list, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Movimiento> update(@Validated @RequestBody Movimiento movimiento) {
		Movimiento movimientoGuardado = service.update(movimiento);
		return new ResponseEntity<Movimiento>(movimientoGuardado, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
