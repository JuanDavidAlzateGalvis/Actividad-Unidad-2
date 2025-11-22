/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.out;

import co.edu.udec.financias_bancarias.domain.model.Cliente;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.ClienteId;

public interface ClienteRepositoryPort {
    Cliente buscarPorId(ClienteId clienteId);
}
