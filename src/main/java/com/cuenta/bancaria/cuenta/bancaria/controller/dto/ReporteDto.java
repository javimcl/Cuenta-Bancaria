/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author JAVIM
 *
 */
public class ReporteDto {
	
	private Date fecha;
	private String nombre;
	private String numero;
	private String tipoCuenta;
	private String saldoAnterior;
	private String estado;
	private BigDecimal valor;
	private BigDecimal saldo;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getSaldoAnterior() {
		return saldoAnterior;
	}
	public void setSaldoAnterior(String saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	

}
