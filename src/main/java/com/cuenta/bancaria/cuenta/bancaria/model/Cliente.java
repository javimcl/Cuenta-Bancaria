package com.cuenta.bancaria.cuenta.bancaria.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String contrasena;

	@Lob
	private String direccion;

	private String estado;

	private String nombre;

	private int telefono;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="cliente", fetch = FetchType.LAZY)
	private List<Cuenta> cuentas;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="cliente", fetch = FetchType.LAZY)
	private List<Movimiento> movimientos;


}