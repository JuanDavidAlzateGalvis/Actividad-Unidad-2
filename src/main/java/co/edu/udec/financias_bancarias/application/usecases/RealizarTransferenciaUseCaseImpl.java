/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.application.usecases;

import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import co.edu.udec.financias_bancarias.domain.ports.in.RealizarTransferenciaUseCase;
import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.domain.ports.out.TransaccionRepositoryPort;
import co.edu.udec.financias_bancarias.domain.services.RealizarTransferenciaService;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class RealizarTransferenciaUseCaseImpl implements RealizarTransferenciaUseCase {
    
    private final CuentaRepositoryPort cuentaRepository;
    private final TransaccionRepositoryPort transaccionRepository;
    private final RealizarTransferenciaService transferenciaService;
    
    public RealizarTransferenciaUseCaseImpl(CuentaRepositoryPort cuentaRepository,
                                           TransaccionRepositoryPort transaccionRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
        this.transferenciaService = new RealizarTransferenciaService();
    }
    
    @Override
    public Transaccion realizarTransferencia(String cuentaOrigenCCC, String cuentaDestinoCCC,
                                            BigDecimal monto, String descripcion) {
        if (cuentaOrigenCCC == null || cuentaOrigenCCC.isBlank() ||
            cuentaDestinoCCC == null || cuentaDestinoCCC.isBlank()) {
            throw new IllegalArgumentException("Los CCC de origen y destino son requeridos");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        var cuentaOrigen = cuentaRepository.buscarPorCCC(CodigoCuentaCliente.fromString(cuentaOrigenCCC));
        var cuentaDestino = cuentaRepository.buscarPorCCC(CodigoCuentaCliente.fromString(cuentaDestinoCCC));

        Transaccion transaccion = transferenciaService.realizarTransferencia(
            cuentaOrigen, cuentaDestino, monto, descripcion
        );

        cuentaRepository.actualizar(cuentaOrigen);
        cuentaRepository.actualizar(cuentaDestino);

        transaccionRepository.guardar(transaccion);

        return transaccion;
    }
}
