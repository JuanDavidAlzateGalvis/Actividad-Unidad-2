/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class PostgresCuentaRepository implements CuentaRepositoryPort {
    
    private final DataSource dataSource;
    
    public PostgresCuentaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public void guardar(Cuenta cuenta) {
        String sql = "INSERT INTO cuentas (banco_id, sucursal_id, numero_cuenta, tipo, " +
                    "saldo_actual, saldo_medio, tipo_amortizacion, fecha_apertura, " +
                    "cliente_id, sucursal_numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cuenta.getCcc().bancoId());
            stmt.setString(2, cuenta.getCcc().sucursalId());
            stmt.setString(3, cuenta.getCcc().numeroCuenta());
            stmt.setString(4, cuenta.getTipo().name());
            stmt.setBigDecimal(5, cuenta.getSaldoActual());
            stmt.setBigDecimal(6, cuenta.getSaldoMedio());
            
            if (cuenta.getTipoAmortizacion() != null) {
                stmt.setString(7, cuenta.getTipoAmortizacion().name());
            } else {
                stmt.setNull(7, Types.VARCHAR);
            }
            
            stmt.setDate(8, Date.valueOf(cuenta.getFechaApertura()));
            stmt.setString(9, cuenta.getCliente().getId().value());
            stmt.setString(10, cuenta.getSucursal().getNumero());
            
            stmt.executeUpdate();
            System.out.println("✅ Cuenta guardada en PostgreSQL: " + cuenta.getCcc().toString());
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar cuenta en PostgreSQL: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Cuenta buscarPorCCC(CodigoCuentaCliente ccc) {
        throw new UnsupportedOperationException("Búsqueda por CCC no implementada aún");
    }
}
