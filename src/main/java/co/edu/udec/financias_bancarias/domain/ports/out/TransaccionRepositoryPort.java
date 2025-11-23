/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.ports.out;

import co.edu.udec.financias_bancarias.domain.model.Transaccion;

public interface TransaccionRepositoryPort {
    void guardar(Transaccion transaccion);
}
