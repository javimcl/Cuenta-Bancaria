/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.controller.dto;

/**
 * @author JAVIM
 *
 */
public class ClienteEntradaDto {

	private String identificacion;
	private String contrasenia;
	private String estado;

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
