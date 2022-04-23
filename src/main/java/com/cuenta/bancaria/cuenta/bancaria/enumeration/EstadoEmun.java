/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.enumeration;

/**
 * @author JAVIM
 *
 */
public enum EstadoEmun {

	ACTIVO("A", "True"), INACTIVO("I", "False");

	private String codigo;
	private String descripcion;

	private EstadoEmun(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	

}
