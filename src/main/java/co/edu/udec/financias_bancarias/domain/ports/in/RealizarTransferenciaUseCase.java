/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.in;

import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import java.math.BigDecimal;

public interface RealizarTransferenciaUseCase {
    Transaccion realizarTransferencia(String cuentaOrigenCCC, String cuentaDestinoCCC, 
                                    BigDecimal monto, String descripcion);
}
