/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.config;

import co.edu.udec.financias_bancarias.application.usecases.AperturaCuentaUseCaseImpl;
import co.edu.udec.financias_bancarias.application.usecases.RealizarTransferenciaUseCaseImpl;
import co.edu.udec.financias_bancarias.domain.ports.in.AperturaCuentaUseCase;
import co.edu.udec.financias_bancarias.domain.ports.in.RealizarTransferenciaUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.ClienteRepositoryPort;
import co.edu.udec.financias_bancarias.domain.ports.out.SucursalRepositoryPort;
import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.domain.ports.out.TransaccionRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

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
    
    @Bean
    public RealizarTransferenciaUseCase realizarTransferenciaUseCase(
            CuentaRepositoryPort cuentaRepositoryPort,
            TransaccionRepositoryPort transaccionRepositoryPort) {
        return new RealizarTransferenciaUseCaseImpl(
            cuentaRepositoryPort,
            transaccionRepositoryPort
        );
    }
}
