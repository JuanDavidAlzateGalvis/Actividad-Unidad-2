/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.application.usecases;

import co.edu.udec.financias_bancarias.domain.ports.in.ConsultarSaldoUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ConsultarSaldoUseCaseImpl implements ConsultarSaldoUseCase {

    private final CuentaRepositoryPort cuentaRepository;

    public ConsultarSaldoUseCaseImpl(CuentaRepositoryPort cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Cuenta consultarSaldo(String cuentaCCC) {
        return cuentaRepository.buscarPorCCC(CodigoCuentaCliente.fromString(cuentaCCC));
    }

    @Override
    public BigDecimal obtenerSaldoActual(String cuentaCCC) {
        Cuenta cuenta = consultarSaldo(cuentaCCC);
        return cuenta.getSaldoActual();
    }

    @Override
    public BigDecimal obtenerSaldoMedio(String cuentaCCC) {
        Cuenta cuenta = consultarSaldo(cuentaCCC);
        return cuenta.getSaldoMedio();
    }
}
