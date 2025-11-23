/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.in;

import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import java.util.List;
import java.time.LocalDateTime;

public interface ConsultarMovimientosUseCase {
    List<Transaccion> consultarMovimientosPorCuenta(String cuentaCCC);
    List<Transaccion> consultarMovimientosPorCuentaYFecha(String cuentaCCC, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<Transaccion> consultarMovimientosPorCliente(String clienteId);
}
