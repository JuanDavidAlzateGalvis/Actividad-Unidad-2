/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.exceptions.SucursalNoEncontradaException;
import co.edu.udec.financias_bancarias.domain.ports.out.SucursalRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Sucursal;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.Direccion;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemorySucursalRepository implements SucursalRepositoryPort {
    
    private final Map<String, Sucursal> sucursales = new HashMap<>();
    
    public InMemorySucursalRepository() {
        Direccion direccionSucursal = new Direccion("Avenida Principal 456", "Bogotá", "11002");
        Sucursal sucursal1 = new Sucursal("SUC-001", direccionSucursal, "11002", "Bogotá");
        
        sucursales.put("SUC-001", sucursal1);
    }
    
    @Override
    public Sucursal buscarPorId(String sucursalId) {
        Sucursal sucursal = sucursales.get(sucursalId);
        if (sucursal == null) {
            throw new SucursalNoEncontradaException(sucursalId);
        }
        return sucursal;
    }
}