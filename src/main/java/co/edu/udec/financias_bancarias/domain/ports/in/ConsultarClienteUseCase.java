/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.in;

import co.edu.udec.financias_bancarias.domain.model.Cliente;
import java.util.List;

public interface ConsultarClienteUseCase {
    Cliente consultarClientePorId(String clienteId);
    List<Cliente> consultarTodosLosClientes();
}
