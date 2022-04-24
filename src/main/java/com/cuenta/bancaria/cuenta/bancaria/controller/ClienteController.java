/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller;

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

import com.cuenta.bancaria.cuenta.bancaria.controller.dto.ClienteEntradaDto;
import com.cuenta.bancaria.cuenta.bancaria.model.Cliente;
import com.cuenta.bancaria.cuenta.bancaria.model.Persona;
import com.cuenta.bancaria.cuenta.bancaria.service.ClienteService;
import com.cuenta.bancaria.cuenta.bancaria.service.PersonaService;

/**
 * @author JAVIM
 *
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService service;

	@Autowired
	private PersonaService personaService;

	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody ClienteEntradaDto clienteEntradaDto) {
		try {
			if (!ObjectUtils.isEmpty(clienteEntradaDto.getIdentificacion())) {
				Optional<Persona> personaEncontrada = personaService
						.obtenerPorIdentificacion(clienteEntradaDto.getIdentificacion());
				if (personaEncontrada.isPresent()) {
					clienteEntradaDto.setIdPersona(personaEncontrada.get().getIdPersona());
				} else {
					return new ResponseEntity<>("No existe la persona con esta identificación", HttpStatus.BAD_REQUEST);
				}
			}

			Optional<Cliente> clienteEncontrado = service.obtenerPorEstadoIdCliente(Boolean.TRUE.toString(),
					clienteEntradaDto.getIdPersona());
			if (clienteEncontrado.isEmpty()) {
				Cliente cliente = new Cliente();
				cliente.setContrasena(clienteEntradaDto.getContrasenia());
				cliente.setEstado(Boolean.TRUE.toString());
				cliente.setIdPersona(clienteEntradaDto.getIdPersona());
				Cliente clienteGuardado = service.create(cliente);
				return new ResponseEntity<Cliente>(clienteGuardado, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Cliente ya se encuentra registrado", HttpStatus.CREATED);
			}

		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> obtenerClientePorPersona(@Validated @RequestBody ClienteEntradaDto clienteEntradaDto) {
		if (!ObjectUtils.isEmpty(clienteEntradaDto.getIdentificacion())) {
			Optional<Persona> personaEncontrada = personaService
					.obtenerPorIdentificacion(clienteEntradaDto.getIdentificacion());
			if (personaEncontrada.isPresent()) {
				clienteEntradaDto.setIdPersona(personaEncontrada.get().getIdPersona());
			} else {
				return new ResponseEntity<>("No existe la persona con esta identificación", HttpStatus.BAD_REQUEST);
			}
		}

		List<Cliente> listaCliente = service.obtenerPorIdPersona(Long.valueOf(clienteEntradaDto.getIdPersona()));
		if (null == listaCliente || listaCliente.isEmpty()) {
			return new ResponseEntity<>("No existe clientes con el parametro", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Cliente>>(listaCliente, HttpStatus.OK);
		}

	}

	@PutMapping
	public ResponseEntity<?> update(@Validated @RequestBody ClienteEntradaDto clienteEntradaDto) {
		try {

			Optional<Persona> personaEncontrada = personaService
					.obtenerPorIdentificacion(clienteEntradaDto.getIdentificacion());
			if (!personaEncontrada.isPresent()) {
				return new ResponseEntity<>("No existe el cliente con esta identificación", HttpStatus.BAD_REQUEST);
			}
			Optional<Cliente> clienteEncontrado = service.obtenerPorEstadoIdCliente(Boolean.TRUE.toString(),
					personaEncontrada.get().getIdPersona());
			if (clienteEncontrado.isPresent()) {
				Cliente cliente = clienteEncontrado.get();
				cliente.setContrasena(clienteEntradaDto.getContrasenia());
				cliente.setEstado(Boolean.TRUE.toString());
				Cliente clienteGuardado = service.update(cliente);
				return new ResponseEntity<Cliente>(clienteGuardado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Cliente no se encuentra registrado", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {

			Optional<Cliente> personaEncontrada = service.obtenerPorId(id);
			if (personaEncontrada.isPresent()) {
				service.delete(id);
				return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
			}
			return new ResponseEntity<String>(
					"No se puede eliminar el registro con id: " + id + " no existe el registro",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Por favor comuniquese con el administrador", e);
			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
