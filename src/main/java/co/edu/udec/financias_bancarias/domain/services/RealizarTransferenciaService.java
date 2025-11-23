/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.services;

import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import java.math.BigDecimal;

/**
 *
 * @author juana
 */
public class RealizarTransferenciaService {
    
    public Transaccion realizarTransferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, 
                                           BigDecimal monto, String descripcion) {
        if (cuentaOrigen == null || cuentaDestino == null) {
            throw new IllegalArgumentException("Las cuentas no pueden ser nulas");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        if (cuentaOrigen.getSaldoActual().compareTo(monto) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta origen");
        }
        if (cuentaOrigen.getCcc().equals(cuentaDestino.getCcc())) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }

        cuentaOrigen.retirar(monto);
        cuentaDestino.depositar(monto);

        return new Transaccion(
            cuentaOrigen.getCcc(),
            cuentaDestino.getCcc(),
            monto,
            "TRANSFERENCIA",
            descripcion != null ? descripcion : "Transferencia entre cuentas"
        );
    }
}
