/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.services;

import co.edu.udec.financias_bancarias.domain.enums.TipoArmotizacion;
import co.edu.udec.financias_bancarias.domain.enums.TipoCuenta;
import co.edu.udec.financias_bancarias.domain.model.Cliente;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.model.Sucursal;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import java.math.BigDecimal;

/**
 *
 * @author juana
 */
public class AperturaCuentaService {
    private final String bancoId;
    private int contadorLocal = 1000;

    public AperturaCuentaService(String bancoId) {
        this.bancoId = bancoId == null || bancoId.isBlank() ? "B001" : bancoId;
    }

    public Cuenta abrirCuenta(Cliente cliente, TipoCuenta tipoCuenta, BigDecimal depositoInicial, Sucursal sucursal, TipoArmotizacion tipoAmortizacion) {
        if (cliente == null) throw new IllegalArgumentException("cliente nulo");
        if (tipoCuenta == null) throw new IllegalArgumentException("tipoCuenta nulo");
        if (depositoInicial == null || depositoInicial.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("depositoInicial inválido");
        if (sucursal == null) throw new IllegalArgumentException("sucursal nula");

        if (tipoCuenta == TipoCuenta.CORRIENTE && depositoInicial.compareTo(new BigDecimal("100")) < 0) {
        throw new IllegalArgumentException("Depósito mínimo para cuenta corriente: 100");
    }

    String numeroCuenta = String.valueOf(++contadorLocal);
    CodigoCuentaCliente ccc = new CodigoCuentaCliente(bancoId, sucursal.getNumero(), numeroCuenta);

    return new Cuenta(ccc, tipoCuenta, depositoInicial, cliente, sucursal, tipoAmortizacion);
    }
}
