package com.cuenta.bancaria.cuenta.bancaria.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the movimiento database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private double saldo;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCuenta")
	private Cuenta cuenta;


}