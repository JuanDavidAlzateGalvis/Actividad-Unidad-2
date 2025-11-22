/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.exceptions;

public class CuentaNoEncontradaException extends CuentaException {
    public CuentaNoEncontradaException(String cuentaId) {
        super("Cuenta no encontrada: " + cuentaId);
    }
}
