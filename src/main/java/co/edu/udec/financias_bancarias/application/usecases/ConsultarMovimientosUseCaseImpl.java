/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.application.usecases;

import co.edu.udec.financias_bancarias.domain.ports.in.ConsultarMovimientosUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.TransaccionRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class ConsultarMovimientosUseCaseImpl implements ConsultarMovimientosUseCase {

    private final TransaccionRepositoryPort transaccionRepository;

    public ConsultarMovimientosUseCaseImpl(TransaccionRepositoryPort transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public List<Transaccion> consultarMovimientosPorCuenta(String cuentaCCC) {
        return transaccionRepository.buscarPorCuenta(CodigoCuentaCliente.fromString(cuentaCCC));
    }

    @Override
    public List<Transaccion> consultarMovimientosPorCuentaYFecha(String cuentaCCC, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return transaccionRepository.buscarPorCuentaYFechas(CodigoCuentaCliente.fromString(cuentaCCC), fechaInicio, fechaFin);
    }

    @Override
    public List<Transaccion> consultarMovimientosPorCliente(String clienteId) {
        return transaccionRepository.buscarPorCliente(clienteId);
    }
}
