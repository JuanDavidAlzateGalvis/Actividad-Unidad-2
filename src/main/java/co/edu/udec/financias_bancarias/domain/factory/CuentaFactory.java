/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.factory;

import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;

/**
 *
 * @author juana
 */
public class CuentaFactory {
    public static CodigoCuentaCliente crearCCC(String bancoId, String sucursalId, String numeroCuenta) {
        return new CodigoCuentaCliente(bancoId, sucursalId, numeroCuenta);
    }
}
