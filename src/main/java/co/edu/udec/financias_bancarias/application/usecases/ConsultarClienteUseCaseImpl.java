/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.application.usecases;

import co.edu.udec.financias_bancarias.domain.ports.in.ConsultarClienteUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.ClienteRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Cliente;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.ClienteId;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ConsultarClienteUseCaseImpl implements ConsultarClienteUseCase {

    private final ClienteRepositoryPort clienteRepository;

    public ConsultarClienteUseCaseImpl(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente consultarClientePorId(String clienteId) {
        return clienteRepository.buscarPorId(new ClienteId(clienteId));
    }

    @Override
    public List<Cliente> consultarTodosLosClientes() {
        return clienteRepository.buscarTodos();
    }
}