/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.out;

import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import java.util.List;
import java.time.LocalDateTime;

public interface TransaccionRepositoryPort {
    void guardar(Transaccion transaccion);
    List<Transaccion> buscarPorCuenta(CodigoCuentaCliente ccc);
    List<Transaccion> buscarPorCuentaYFechas(CodigoCuentaCliente ccc, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<Transaccion> buscarPorCliente(String clienteId);
    
    // NUEVO MÉTODO PARA REPORTES
    List<Transaccion> buscarPorRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
