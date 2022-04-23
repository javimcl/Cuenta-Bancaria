/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuenta.bancaria.cuenta.bancaria.model.Cliente;
import com.cuenta.bancaria.cuenta.bancaria.repository.ClienteRepository;
import com.cuenta.bancaria.cuenta.bancaria.service.ClienteService;

/**
 * @author JAVIM
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente create(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> read() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteRepository.deleteById(id);

	}

	@Override
	public Optional<Cliente> obtenerPorEstadoIdCliente(String estado, Long id) {
		return clienteRepository.findByEstadoAndIdCliente(estado, id);
	}

	@Override
	public Optional<Cliente> obtenerPorId(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	public Optional<Cliente> obtenerPorIdCliente(Long id) {
		return clienteRepository.findByIdCliente(id);
	}

}
