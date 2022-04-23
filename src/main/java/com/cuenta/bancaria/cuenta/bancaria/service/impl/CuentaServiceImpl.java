/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuenta.bancaria.cuenta.bancaria.model.Cuenta;
import com.cuenta.bancaria.cuenta.bancaria.repository.CuentaRepository;
import com.cuenta.bancaria.cuenta.bancaria.service.CuentaService;

/**
 * @author JAVIM
 *
 */
@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public Cuenta create(Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}

	@Override
	public List<Cuenta> read() {
		return cuentaRepository.findAll();
	}

	@Override
	public Cuenta update(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return cuentaRepository.save(cuenta);
	}

	@Override
	public void delete(Long id) {
		cuentaRepository.deleteById(id);

	}

}
