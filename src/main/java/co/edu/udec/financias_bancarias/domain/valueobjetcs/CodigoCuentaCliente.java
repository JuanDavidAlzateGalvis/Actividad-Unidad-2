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

    public static CodigoCuentaCliente fromString(String ccc) {
        if (ccc == null || ccc.isBlank()) {
            throw new IllegalArgumentException("CCC no puede ser nulo o vacío");
        }
        String[] partes = ccc.split("-");
        if (partes.length != 3) {
            throw new IllegalArgumentException("Formato de CCC inválido. Debe ser: bancoId-sucursalId-numeroCuenta");
        }
        return new CodigoCuentaCliente(partes[0], partes[1], partes[2]);
    }

    @Override
    public String toString() {
        return bancoId + "-" + sucursalId + "-" + numeroCuenta;
    }
}
