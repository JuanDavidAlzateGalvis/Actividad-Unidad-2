/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.web;

import co.edu.udec.financias_bancarias.domain.ports.in.AperturaCuentaUseCase;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.enums.TipoArmotizacion;
import co.edu.udec.financias_bancarias.domain.enums.TipoCuenta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cuentas")
public class AperturaCuentaController {
    
    private final AperturaCuentaUseCase aperturaCuentaUseCase;
    
    public AperturaCuentaController(AperturaCuentaUseCase aperturaCuentaUseCase) {
        this.aperturaCuentaUseCase = aperturaCuentaUseCase;
    }
    
    @PostMapping("/abrir")
    public ResponseEntity<CuentaResponse> abrirCuenta(@RequestBody AperturaCuentaRequest request) {
        try {
            TipoCuenta tipoCuenta = TipoCuenta.valueOf(request.tipoCuenta().toUpperCase());
            TipoArmotizacion tipoAmortizacion = TipoArmotizacion.valueOf(request.tipoAmortizacion().toUpperCase());
            
            Cuenta cuentaCreada = aperturaCuentaUseCase.abrirCuenta(
                request.clienteId(),
                tipoCuenta,
                request.depositoInicial(),
                request.sucursalId(),
                tipoAmortizacion
            );
            
            CuentaResponse response = new CuentaResponse(
                cuentaCreada.getCcc().toString(),
                cuentaCreada.getTipo().name(),
                cuentaCreada.getSaldoActual(),
                cuentaCreada.getCliente().getNombre(),
                "Cuenta creada exitosamente"
            );
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                new CuentaResponse(null, null, null, null, "Error: " + e.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                new CuentaResponse(null, null, null, null, "Error interno: " + e.getMessage())
            );
        }
    }
    
    public record AperturaCuentaRequest(
        String clienteId,
        String tipoCuenta,
        BigDecimal depositoInicial,
        String sucursalId,
        String tipoAmortizacion
    ) {}
    
    public record CuentaResponse(
        String codigoCuenta,
        String tipoCuenta,
        BigDecimal saldo,
        String titular,
        String mensaje
    ) {}
}
