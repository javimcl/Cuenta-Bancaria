/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller.dto;

/**
 * @author JAVIM
 *
 */
public class CuentaEntradaDto {
	
	private String identificacion;
	private String numero;
	private String tipoCuenta;
	private String saldoInicial;
	private String estado;
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
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
	public String getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(String saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}
