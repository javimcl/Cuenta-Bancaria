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

import com.cuenta.bancaria.cuenta.bancaria.model.Cliente;
import com.cuenta.bancaria.cuenta.bancaria.service.ClienteService;

/**
 * @author JAVIM
 *
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	
	@PostMapping
	public ResponseEntity<Cliente> create(@Validated @RequestBody Cliente cliente) {
		Cliente clienteGuardado = service.create(cliente);
		return new ResponseEntity<Cliente>(clienteGuardado, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> read() {
		List<Cliente> list = service.read();
		return new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> update(@Validated @RequestBody Cliente employee) {
		Cliente clienteGuardado = service.update(employee);
		return new ResponseEntity<Cliente>(clienteGuardado, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
