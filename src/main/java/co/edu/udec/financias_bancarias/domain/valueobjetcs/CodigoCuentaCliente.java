/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.valueobjetcs;

/**
 *
 * @author juana
 */
public record CodigoCuentaCliente(String bancoId, String sucursalId, String numeroCuenta) {
    public CodigoCuentaCliente {
        if (bancoId == null || bancoId.isBlank()) throw new IllegalArgumentException("bancoId inválido");
        if (sucursalId == null || sucursalId.isBlank()) throw new IllegalArgumentException("sucursalId inválido");
        if (numeroCuenta == null || numeroCuenta.isBlank()) throw new IllegalArgumentException("numeroCuenta inválido");
    }

    @Override
        public String toString() {
        return bancoId + "-" + sucursalId + "-" + numeroCuenta;
    }
}
