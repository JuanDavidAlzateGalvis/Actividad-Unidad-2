/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.application.usecases;

import co.edu.udec.financias_bancarias.domain.ports.in.GenerarReporteTransaccionesUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.TransaccionRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class GenerarReporteTransaccionesUseCaseImpl implements GenerarReporteTransaccionesUseCase {

    private final TransaccionRepositoryPort transaccionRepository;

    public GenerarReporteTransaccionesUseCaseImpl(TransaccionRepositoryPort transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public List<Transaccion> generarReporteTransaccionesPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return transaccionRepository.buscarPorRangoDeFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Transaccion> generarReporteTransaccionesPorSucursal(String sucursalId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        // Este reporte es más complejo, requeriría unir con la tabla de cuentas para filtrar por sucursal.
        // Por ahora, vamos a devolver una lista vacía o lanzar una excepción.
        throw new UnsupportedOperationException("Reporte por sucursal no implementado aún");
    }
}
