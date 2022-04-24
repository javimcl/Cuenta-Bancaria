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
import com.cuenta.bancaria.cuenta.bancaria.service.ClienteService;
import com.cuenta.bancaria.cuenta.bancaria.service.CuentaService;

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

//	@Autowired
//	private PersonaService personaService;

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody CuentaEntradaDto cuentaEntradaDto) {
		try {
//			if (!ObjectUtils.isEmpty(cuentaEntradaDto.getIdentificacion())) {
//				Optional<Persona> personaEncontrada = personaService
//						.obtenerPorIdentificacion(cuentaEntradaDto.getIdentificacion());
//				if (personaEncontrada.isPresent()) {
//					cuentaEntradaDto.setIdPersona(personaEncontrada.get().getIdPersona());
//				} else {
//					return new ResponseEntity<>("No existe la persona con esta identificación", HttpStatus.BAD_REQUEST);
//				}
//			}

			Optional<Cliente> cliente = clienteService.obtenerPorIdCliente(cuentaEntradaDto.getIdPersona());
			if (cliente.isPresent()) {
				if (EstadoEmun.INACTIVO.getDescripcion().equals(cliente.get().getEstado())) {
					return new ResponseEntity<>("El cliente se encuentra inactivo", HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<?> obtenerCuentaPorCliente(@Validated @RequestBody CuentaEntradaDto cuentaEntradaDto) {
		try {
			List<Cuenta> listaCuenta = service.obtenerPorCliente(cuentaEntradaDto.getIdPersona());
			if (null == listaCuenta || listaCuenta.isEmpty()) {
				return new ResponseEntity<>("No existe cuenta con el id", HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Cuenta>>(listaCuenta, HttpStatus.OK);
			}

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping
	public ResponseEntity<?> update(@Validated @RequestBody CuentaEntradaDto cuentaEntradaDto) {
		try {
			Optional<Cuenta> cuenta = service.obtenerPorId(cuentaEntradaDto.getIdCuenta());
			if (cuenta.isPresent()) {
				Cuenta cuentaActualizar = cuenta.get();
				cuentaActualizar.setNumero(Integer.parseInt(cuentaEntradaDto.getNumero()));
				cuentaActualizar.setTipoCuenta(cuentaEntradaDto.getTipoCuenta());
				cuentaActualizar
						.setSaldoInicial(BigDecimal.valueOf(Double.valueOf(cuentaEntradaDto.getSaldoInicial())));
				cuentaActualizar.setEstado(cuentaEntradaDto.getEstado());
				Cuenta cuentaGuardada = service.update(cuentaActualizar);
				return new ResponseEntity<Cuenta>(cuentaGuardada, HttpStatus.OK);
			}
			return new ResponseEntity<>("No se encuentra la cuenta", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			Optional<Cuenta> cuenta = service.obtenerPorId(id);
			if (cuenta.isPresent()) {
				service.delete(id);
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<String>("No se puede eliminar la cuenta con id: " + id + " no existe el registro",
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
