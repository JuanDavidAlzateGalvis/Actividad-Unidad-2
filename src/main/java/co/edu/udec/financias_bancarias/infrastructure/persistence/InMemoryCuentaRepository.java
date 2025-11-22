/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.exceptions.CuentaNoEncontradaException;
import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCuentaRepository implements CuentaRepositoryPort {
    
    private final List<Cuenta> cuentas = new ArrayList<>();
    
    @Override
    public void guardar(Cuenta cuenta) {
        cuentas.add(cuenta);
        System.out.println("✅ Cuenta guardada: " + cuenta.getCcc().toString());
        System.out.println("📊 Total cuentas en sistema: " + cuentas.size());
    }
    
    @Override
    public Cuenta buscarPorCCC(CodigoCuentaCliente ccc) {
        return cuentas.stream()
            .filter(c -> c.getCcc().equals(ccc))
            .findFirst()
            .orElseThrow(() -> new CuentaNoEncontradaException(ccc.toString()));
    }
} 
