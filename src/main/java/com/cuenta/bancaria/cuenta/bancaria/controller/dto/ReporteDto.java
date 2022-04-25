package com.cuenta.bancaria.cuenta.bancaria.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

public interface ReporteDto {

	public Date getFecha();

	public String getNombre();

	public int getNumero();

	public String getTipoCuenta();

	public BigDecimal getSaldoAnterior();

	public String getEstado();

	public BigDecimal getValor();

	public BigDecimal getSaldo();

}
