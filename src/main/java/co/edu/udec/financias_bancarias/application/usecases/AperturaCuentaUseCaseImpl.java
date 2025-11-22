/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.application.usecases;

import co.edu.udec.financias_bancarias.domain.ports.in.AperturaCuentaUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.ClienteRepositoryPort;
import co.edu.udec.financias_bancarias.domain.ports.out.SucursalRepositoryPort;
import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.domain.services.AperturaCuentaService;
import co.edu.udec.financias_bancarias.domain.enums.TipoArmotizacion;
import co.edu.udec.financias_bancarias.domain.enums.TipoCuenta;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.ClienteId;

import java.math.BigDecimal;

public class AperturaCuentaUseCaseImpl implements AperturaCuentaUseCase {
    
    private final ClienteRepositoryPort clienteRepository;
    private final SucursalRepositoryPort sucursalRepository;
    private final CuentaRepositoryPort cuentaRepository;
    private final AperturaCuentaService aperturaService;
    
    public AperturaCuentaUseCaseImpl(ClienteRepositoryPort clienteRepository,
                                    SucursalRepositoryPort sucursalRepository,
                                    CuentaRepositoryPort cuentaRepository) {
        this.clienteRepository = clienteRepository;
        this.sucursalRepository = sucursalRepository;
        this.cuentaRepository = cuentaRepository;
        this.aperturaService = new AperturaCuentaService("B001");
    }
    
    @Override
    public Cuenta abrirCuenta(String clienteId, TipoCuenta tipoCuenta,
                             BigDecimal depositoInicial, String sucursalId,
                             TipoArmotizacion tipoAmortizacion) {
        
        // Validaciones básicas
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("ID de cliente es requerido");
        }
        if (sucursalId == null || sucursalId.isBlank()) {
            throw new IllegalArgumentException("ID de sucursal es requerido");
        }
        
        // 1. Buscar entidades necesarias usando los puertos de salida
        var cliente = clienteRepository.buscarPorId(new ClienteId(clienteId));
        var sucursal = sucursalRepository.buscarPorId(sucursalId);
        
        // 2. Usar el servicio de dominio existente para crear la cuenta
        Cuenta nuevaCuenta = aperturaService.abrirCuenta(
            cliente, tipoCuenta, depositoInicial, sucursal, tipoAmortizacion
        );
        
        // 3. Persistir la cuenta usando el puerto de salida
        cuentaRepository.guardar(nuevaCuenta);
        
        return nuevaCuenta;
    }
} 
