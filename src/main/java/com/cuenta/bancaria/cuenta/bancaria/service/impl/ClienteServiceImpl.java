/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		return clienteRepository.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteRepository.deleteById(id);

	}

}
