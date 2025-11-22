/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.exceptions;

import java.math.BigDecimal;

public class SaldoInsuficienteException extends CuentaException {
    public SaldoInsuficienteException(BigDecimal saldoActual, BigDecimal montoRequerido) {
        super(String.format("Saldo insuficiente. Actual: %s, Requerido: %s", saldoActual, montoRequerido));
    }
}