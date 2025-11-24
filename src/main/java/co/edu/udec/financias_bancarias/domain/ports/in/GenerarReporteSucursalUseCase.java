/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.in;

import java.math.BigDecimal;
import java.util.Map;

public interface GenerarReporteSucursalUseCase {
    Map<String, BigDecimal> generarReporteSaldosPorSucursal();
    Map<String, Integer> generarReporteCantidadCuentasPorSucursal();
}

