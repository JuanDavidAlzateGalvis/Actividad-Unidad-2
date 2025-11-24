/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.out;

import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import java.util.List;

public interface CuentaRepositoryPort {
    void guardar(Cuenta cuenta);
    Cuenta buscarPorCCC(CodigoCuentaCliente ccc);
    void actualizar(Cuenta cuenta);
    
    // NUEVOS MÉTODOS PARA REPORTES
    List<Cuenta> buscarPorCliente(String clienteId);
    List<Cuenta> buscarTodas();
    List<Object[]> obtenerSaldosAgrupadosPorSucursal();
}
