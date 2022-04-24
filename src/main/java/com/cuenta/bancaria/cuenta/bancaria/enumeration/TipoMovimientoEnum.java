/**
 * 
 */
package com.cuenta.bancaria.cuenta.bancaria.enumeration;

/**
 * @author JAVIM
 *
 */
public enum TipoMovimientoEnum {

	CREDITO("CRE", "credito"), DEBITO("DEB", "debito");

	private String codigo;
	private String descripcion;

	private TipoMovimientoEnum(String codigo, String descripcion) {
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
