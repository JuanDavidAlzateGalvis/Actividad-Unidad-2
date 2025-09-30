/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.model;

import co.edu.udec.financias_bancarias.domain.enums.TipoArmotizacion;
import co.edu.udec.financias_bancarias.domain.enums.TipoCuenta;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author juana
 */
public class Cuenta {
    private final CodigoCuentaCliente ccc;
    private final TipoCuenta tipo;
    private BigDecimal saldoActual;
    private BigDecimal saldoMedio;
    private TipoArmotizacion tipoAmortizacion;
    private final LocalDate fechaApertura;
    private final Cliente cliente;
    private final Sucursal sucursal;

    public Cuenta(CodigoCuentaCliente ccc, TipoCuenta tipo, BigDecimal saldoInicial, Cliente cliente, Sucursal sucursal, TipoArmotizacion tipoAmortizacion) {
        if (ccc == null) throw new IllegalArgumentException("ccc inválido");
        if (tipo == null) throw new IllegalArgumentException("tipo inválido");
        if (saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("saldoInicial inválido");
        if (cliente == null) throw new IllegalArgumentException("cliente inválido");
        if (sucursal == null) throw new IllegalArgumentException("sucursal inválida");
        if (tipo == TipoCuenta.AHORRO && tipoAmortizacion == null) throw new IllegalArgumentException("Ahorro requiere tipoAmortizacion");
        this.ccc = ccc;
        this.tipo = tipo;
        this.saldoActual = saldoInicial;
        this.saldoMedio = saldoInicial;
        this.tipoAmortizacion = tipoAmortizacion;
        this.fechaApertura = LocalDate.now();
        this.cliente = cliente;
        this.sucursal = sucursal;
    }

    public void depositar(java.math.BigDecimal cantidad) {
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("cantidad inválida");
        this.saldoActual = this.saldoActual.add(cantidad);
        recalcularSaldoMedio();
    }
    
    public void retirar(java.math.BigDecimal cantidad) {
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("cantidad inválida");
        if (this.saldoActual.compareTo(cantidad) < 0) throw new IllegalStateException("Saldo insuficiente");
        this.saldoActual = this.saldoActual.subtract(cantidad);
        recalcularSaldoMedio();
    }


    private void recalcularSaldoMedio() {
        this.saldoMedio = (this.saldoMedio.add(this.saldoActual)).divide(new BigDecimal("2"));
    }

    public CodigoCuentaCliente getCcc() { return ccc; }
    public TipoCuenta getTipo() { return tipo; }
    public BigDecimal getSaldoActual() { return saldoActual; }
    public BigDecimal getSaldoMedio() { return saldoMedio; }
    public TipoArmotizacion getTipoAmortizacion() { return tipoAmortizacion; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public Cliente getCliente() { return cliente; }
    public Sucursal getSucursal() { return sucursal; }
}
