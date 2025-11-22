/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.exceptions;

public class ClienteNoEncontradoException extends CuentaException {
    public ClienteNoEncontradoException(String clienteId) {
        super("Cliente no encontrado con ID: " + clienteId);
    }
}
