/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.model;

import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaccion {
    private final Long id;
    private final CodigoCuentaCliente cuentaOrigen;
    private final CodigoCuentaCliente cuentaDestino;
    private final BigDecimal monto;
    private final LocalDateTime fecha;
    private final String tipo;
    private final String descripcion;

    public Transaccion(Long id, CodigoCuentaCliente cuentaOrigen, CodigoCuentaCliente cuentaDestino, 
                      BigDecimal monto, LocalDateTime fecha, String tipo, String descripcion) {
        this.id = id;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Transaccion(CodigoCuentaCliente cuentaOrigen, CodigoCuentaCliente cuentaDestino,
                      BigDecimal monto, String tipo, String descripcion) {
        this(null, cuentaOrigen, cuentaDestino, monto, LocalDateTime.now(), tipo, descripcion);
    }

    public Long getId() { return id; }
    public CodigoCuentaCliente getCuentaOrigen() { return cuentaOrigen; }
    public CodigoCuentaCliente getCuentaDestino() { return cuentaDestino; }
    public BigDecimal getMonto() { return monto; }
    public LocalDateTime getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
}
