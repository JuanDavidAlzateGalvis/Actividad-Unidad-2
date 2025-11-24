/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.application.usecases;

import co.edu.udec.financias_bancarias.domain.ports.in.GenerarReporteCuentasUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GenerarReporteCuentasUseCaseImpl implements GenerarReporteCuentasUseCase {

    private final CuentaRepositoryPort cuentaRepository;

    public GenerarReporteCuentasUseCaseImpl(CuentaRepositoryPort cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public List<Cuenta> generarReporteCuentasPorCliente(String clienteId) {
        return cuentaRepository.buscarPorCliente(clienteId);
    }

    @Override
    public List<Cuenta> generarReporteTodasLasCuentas() {
        return cuentaRepository.buscarTodas();
    }
}
