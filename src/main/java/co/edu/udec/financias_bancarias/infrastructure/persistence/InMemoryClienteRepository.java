/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.ports.out.ClienteRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Cliente;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.ClienteId;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.Direccion;
import co.edu.udec.financias_bancarias.domain.enums.ClienteTipo;
import co.edu.udec.financias_bancarias.domain.exceptions.ClienteNoEncontradoException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryClienteRepository implements ClienteRepositoryPort {
    
    private final Map<ClienteId, Cliente> clientes = new HashMap<>();
    
    public InMemoryClienteRepository() {
        ClienteId clienteId1 = new ClienteId("CLI-001");
        Direccion direccion1 = new Direccion("Calle 123", "Bogotá", "11001");
        Cliente cliente1 = new Cliente(clienteId1, "Juan Pérez", direccion1, ClienteTipo.PERSONA);
        cliente1.setDatosPersona(java.time.LocalDate.of(1990, 5, 15), 
                                co.edu.udec.financias_bancarias.domain.enums.Sexo.MASCULINO);
        
        clientes.put(clienteId1, cliente1);
    }
    
    @Override
    public Cliente buscarPorId(ClienteId clienteId) {
        return clientes.values().stream()
            .filter(c -> c.getId().equals(clienteId))
            .findFirst()
            .orElseThrow(() -> new ClienteNoEncontradoException(clienteId.value()));
    }
}
