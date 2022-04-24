/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JAVIM
 *
 */
//@Slf4j
//@RestController
//@RequestMapping("/api/personas")
public class PersonaController {
	private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

//	@Autowired
//	private PersonaService service;
//
//	@PostMapping
//	public ResponseEntity<?> create(@Validated @RequestBody Persona persona) {
//		try {
//			Optional<Persona> personaEncontrada = service.obtenerPorIdentificacion(persona.getIdentificacion());
//			if (personaEncontrada.isPresent()) {
//				return new ResponseEntity<>("Un usuario ya existe con esta identificación", HttpStatus.BAD_REQUEST);
//			}
//			Persona clienteGuardado = service.create(persona);
//			return new ResponseEntity<Persona>(clienteGuardado, HttpStatus.CREATED);
//		} catch (Exception e) {
//			log.error("Por favor comuniquese con el administrador", e);
//			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
//
//	@GetMapping
//	public ResponseEntity<?> obtenerPorIdentificacion(@Validated @RequestBody ClienteEntradaDto clienteEntradaDto) {
//		try {
//			Optional<Persona> personaEncontrada = service.obtenerPorIdentificacion(clienteEntradaDto.getIdentificacion());
//			if (personaEncontrada.isPresent()) {
//				return new ResponseEntity<Persona>(personaEncontrada.get(), HttpStatus.OK);
//			}
//			return new ResponseEntity<>("No existe una persona con esta identificacion", HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			log.error("Por favor comuniquese con el administrador", e);
//			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@PutMapping
//	public ResponseEntity<?> update(@Validated @RequestBody Persona persona) {
//		try {
//			Optional<Persona> personaEncontrada = service.obtenerPorId(persona.getIdPersona());
//			if (personaEncontrada.isPresent()) {
//				Persona personaGuardado = service.update(persona);
//				return new ResponseEntity<Persona>(personaGuardado, HttpStatus.OK);
//			}
//			return new ResponseEntity<String>("No exist la persona", HttpStatus.BAD_REQUEST);
//
//		} catch (Exception e) {
//			log.error("Por favor comuniquese con el administrador", e);
//			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
//		try {
//			Optional<Persona> personaEncontrada = service.obtenerPorId(id);
//			if (personaEncontrada.isPresent()) {
//				service.delete(id);
//				return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
//			}
//			return new ResponseEntity<String>(
//					"No se puede eliminar el registro con id: " + id + " no existe el registro",
//					HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			log.error("Por favor comuniquese con el administrador", e);
//			return new ResponseEntity<>("Por favor comuniquese con el administrador", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}

}
