/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuenta.bancaria.cuenta.bancaria.model.Cliente;

/**
 * @author JAVIM
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
