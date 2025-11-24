/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.application.usecases;

import co.edu.udec.financias_bancarias.domain.ports.in.GenerarReporteSucursalUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class GenerarReporteSucursalUseCaseImpl implements GenerarReporteSucursalUseCase {

    private final CuentaRepositoryPort cuentaRepository;

    public GenerarReporteSucursalUseCaseImpl(CuentaRepositoryPort cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Map<String, BigDecimal> generarReporteSaldosPorSucursal() {
        List<Object[]> resultados = cuentaRepository.obtenerSaldosAgrupadosPorSucursal();
        Map<String, BigDecimal> reporte = new HashMap<>();
        
        for (Object[] resultado : resultados) {
            String sucursalNumero = (String) resultado[0];
            BigDecimal saldoTotal = (BigDecimal) resultado[2];
            reporte.put(sucursalNumero, saldoTotal);
        }
        
        return reporte;
    }

    @Override
    public Map<String, Integer> generarReporteCantidadCuentasPorSucursal() {
        List<Object[]> resultados = cuentaRepository.obtenerSaldosAgrupadosPorSucursal();
        Map<String, Integer> reporte = new HashMap<>();
        
        for (Object[] resultado : resultados) {
            String sucursalNumero = (String) resultado[0];
            Long cantidad = (Long) resultado[1];
            reporte.put(sucursalNumero, cantidad.intValue());
        }
        
        return reporte;
    }
}
