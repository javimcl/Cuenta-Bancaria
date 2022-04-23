/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller;

import java.math.BigDecimal;
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

import com.cuenta.bancaria.cuenta.bancaria.controller.dto.CuentaEntradaDto;
import com.cuenta.bancaria.cuenta.bancaria.enumeration.EstadoEmun;
import com.cuenta.bancaria.cuenta.bancaria.model.Cliente;
import com.cuenta.bancaria.cuenta.bancaria.model.Cuenta;
import com.cuenta.bancaria.cuenta.bancaria.model.Persona;
import com.cuenta.bancaria.cuenta.bancaria.service.ClienteService;
import com.cuenta.bancaria.cuenta.bancaria.service.CuentaService;
import com.cuenta.bancaria.cuenta.bancaria.service.PersonaService;

/**
 * @author JAVIM
 *
 */
@RestController
@RequestMapping("/api/cuenta")
public class CuentaController {

	private static final Logger log = LoggerFactory.getLogger(CuentaController.class);

	@Autowired
	private CuentaService service;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody CuentaEntradaDto cuentaEntradaDto) {
		try {
			Optional<Persona> personaEncontrada = personaService
					.obtenerPorIdentificacion(cuentaEntradaDto.getIdentificacion());
			if (!personaEncontrada.isPresent()) {
				return new ResponseEntity<>("No existe la persona con esta identificación", HttpStatus.BAD_REQUEST);
			}
			Optional<Cliente> cliente = clienteService.obtenerPorIdCliente(personaEncontrada.get().getIdPersona());
			if (cliente.isPresent()) {
				if (EstadoEmun.INACTIVO.getDescripcion().equals(cliente.get().getEstado())) {

				} else {
					Cuenta cuenta = new Cuenta();
					cuenta.setNumero(Integer.parseInt(cuentaEntradaDto.getNumero()));
					cuenta.setTipoCuenta(cuentaEntradaDto.getTipoCuenta());
					cuenta.setSaldoInicial(BigDecimal.valueOf(Double.valueOf(cuentaEntradaDto.getSaldoInicial())));
					cuenta.setEstado(EstadoEmun.ACTIVO.getDescripcion());
					Cuenta cuentaGuardada = service.create(cuenta);
					return new ResponseEntity<Cuenta>(cuentaGuardada, HttpStatus.CREATED);
				}

			}
			return new ResponseEntity<>("No se encuentra registrado como cliente", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<Cuenta>> read() {
		List<Cuenta> list = service.read();
		return new ResponseEntity<List<Cuenta>>(list, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@Validated @RequestBody Cuenta cuenta) {
		try {
			Cuenta cuentaGuardada = service.update(cuenta);
			return new ResponseEntity<Cuenta>(cuentaGuardada, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
