package com.cuenta.bancaria.cuenta.bancaria.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String estado;

	private int numero;

	@Column(name="saldo_inicial")
	private BigDecimal saldoInicial;

	private String tipo;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="cuenta")
	private List<Movimiento> movimientos;



}