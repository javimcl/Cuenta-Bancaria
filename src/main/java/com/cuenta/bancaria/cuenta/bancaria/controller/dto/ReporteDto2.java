/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

/**
 * @author JAVIM
 *
 */
@SqlResultSetMapping(name = "reporteDto", entities = { @EntityResult(entityClass = ReporteDto.class, fields = {
		@FieldResult(name = "tipoCuenta", column = "tipoCuenta") }) })
public class ReporteDto2 {

	private Date fecha;
	private String nombre;
	private int numero;
	private String tipoCuenta;
	private BigDecimal saldoAnterior;
	private String estado;
	private BigDecimal valor;
	private BigDecimal saldo;
	public Date getFecha() {
		return fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public int getNumero() {
		return numero;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}
	public String getEstado() {
		return estado;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}

	

}
