/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.in;

import co.edu.udec.financias_bancarias.domain.enums.TipoArmotizacion;
import co.edu.udec.financias_bancarias.domain.enums.TipoCuenta;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import java.math.BigDecimal;

public interface AperturaCuentaUseCase {
    Cuenta abrirCuenta(String clienteId, TipoCuenta tipoCuenta, 
                      BigDecimal depositoInicial, String sucursalId,
                      TipoArmotizacion tipoAmortizacion);
} 
