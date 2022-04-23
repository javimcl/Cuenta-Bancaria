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

import com.cuenta.bancaria.cuenta.bancaria.model.Cuenta;
import com.cuenta.bancaria.cuenta.bancaria.service.CuentaService;

/**
 * @author JAVIM
 *
 */
@RestController
@RequestMapping("/api/cuenta")
public class CuentaController {

	@Autowired
	private CuentaService service;

	@PostMapping
	public ResponseEntity<Cuenta> create(@Validated @RequestBody Cuenta cuenta) {
		Cuenta cuentaGuardada = service.create(cuenta);
		return new ResponseEntity<Cuenta>(cuentaGuardada, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Cuenta>> read() {
		List<Cuenta> list = service.read();
		return new ResponseEntity<List<Cuenta>>(list, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Cuenta> update(@Validated @RequestBody Cuenta cuenta) {
		Cuenta cuentaGuardada = service.update(cuenta);
		return new ResponseEntity<Cuenta>(cuentaGuardada, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
