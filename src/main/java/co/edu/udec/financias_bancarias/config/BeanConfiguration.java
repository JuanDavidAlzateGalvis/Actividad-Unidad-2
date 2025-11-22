/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.config;

import co.edu.udec.financias_bancarias.application.usecases.AperturaCuentaUseCaseImpl;
import co.edu.udec.financias_bancarias.domain.ports.in.AperturaCuentaUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.ClienteRepositoryPort;
import co.edu.udec.financias_bancarias.domain.ports.out.SucursalRepositoryPort;
import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.infrastructure.persistence.InMemoryClienteRepository;
import co.edu.udec.financias_bancarias.infrastructure.persistence.InMemorySucursalRepository;
import co.edu.udec.financias_bancarias.infrastructure.persistence.InMemoryCuentaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteRepositoryPort clienteRepositoryPort() {
        return new InMemoryClienteRepository();
    }
    
    @Bean
    public SucursalRepositoryPort sucursalRepositoryPort() {
        return new InMemorySucursalRepository();
    }
    
    @Bean
    public CuentaRepositoryPort cuentaRepositoryPort() {
        return new InMemoryCuentaRepository();
    }
    
    @Bean
    public AperturaCuentaUseCase aperturaCuentaUseCase(
            ClienteRepositoryPort clienteRepositoryPort,
            SucursalRepositoryPort sucursalRepositoryPort,
            CuentaRepositoryPort cuentaRepositoryPort) {
        return new AperturaCuentaUseCaseImpl(
            clienteRepositoryPort,
            sucursalRepositoryPort,
            cuentaRepositoryPort
        );
    }
}
