/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.ports.out.TransaccionRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostgresTransaccionRepository implements TransaccionRepositoryPort {

    private final DataSource dataSource;

    public PostgresTransaccionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void guardar(Transaccion transaccion) {
    }

    @Override
    public List<Transaccion> buscarPorCuenta(CodigoCuentaCliente ccc) {
        String sql = "SELECT * FROM transacciones WHERE (cuenta_origen_banco_id = ? AND cuenta_origen_sucursal_id = ? AND cuenta_origen_numero_cuenta = ?) " +
                     "OR (cuenta_destino_banco_id = ? AND cuenta_destino_sucursal_id = ? AND cuenta_destino_numero_cuenta = ?) " +
                     "ORDER BY fecha DESC";
        
        List<Transaccion> transacciones = new ArrayList<>();
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ccc.bancoId());
            stmt.setString(2, ccc.sucursalId());
            stmt.setString(3, ccc.numeroCuenta());
            stmt.setString(4, ccc.bancoId());
            stmt.setString(5, ccc.sucursalId());
            stmt.setString(6, ccc.numeroCuenta());
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacciones.add(mapearATransaccion(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar transacciones por cuenta: " + e.getMessage(), e);
        }
        
        return transacciones;
    }

    @Override
    public List<Transaccion> buscarPorCuentaYFechas(CodigoCuentaCliente ccc, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        String sql = "SELECT * FROM transacciones WHERE ((cuenta_origen_banco_id = ? AND cuenta_origen_sucursal_id = ? AND cuenta_origen_numero_cuenta = ?) " +
                     "OR (cuenta_destino_banco_id = ? AND cuenta_destino_sucursal_id = ? AND cuenta_destino_numero_cuenta = ?)) " +
                     "AND fecha BETWEEN ? AND ? ORDER BY fecha DESC";
        
        List<Transaccion> transacciones = new ArrayList<>();
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ccc.bancoId());
            stmt.setString(2, ccc.sucursalId());
            stmt.setString(3, ccc.numeroCuenta());
            stmt.setString(4, ccc.bancoId());
            stmt.setString(5, ccc.sucursalId());
            stmt.setString(6, ccc.numeroCuenta());
            stmt.setTimestamp(7, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(8, Timestamp.valueOf(fechaFin));
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacciones.add(mapearATransaccion(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar transacciones por cuenta y fechas: " + e.getMessage(), e);
        }
        
        return transacciones;
    }

    @Override
    public List<Transaccion> buscarPorCliente(String clienteId) {
        String sql = "SELECT t.* FROM transacciones t " +
                     "JOIN cuentas c_origen ON t.cuenta_origen_banco_id = c_origen.banco_id AND t.cuenta_origen_sucursal_id = c_origen.sucursal_id AND t.cuenta_origen_numero_cuenta = c_origen.numero_cuenta " +
                     "JOIN cuentas c_destino ON t.cuenta_destino_banco_id = c_destino.banco_id AND t.cuenta_destino_sucursal_id = c_destino.sucursal_id AND t.cuenta_destino_numero_cuenta = c_destino.numero_cuenta " +
                     "WHERE c_origen.cliente_id = ? OR c_destino.cliente_id = ? " +
                     "ORDER BY t.fecha DESC";
        
        List<Transaccion> transacciones = new ArrayList<>();
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, clienteId);
            stmt.setString(2, clienteId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacciones.add(mapearATransaccion(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar transacciones por cliente: " + e.getMessage(), e);
        }
        
        return transacciones;
    }

    private Transaccion mapearATransaccion(ResultSet rs) throws SQLException {
        CodigoCuentaCliente cuentaOrigen = new CodigoCuentaCliente(
            rs.getString("cuenta_origen_banco_id"),
            rs.getString("cuenta_origen_sucursal_id"),
            rs.getString("cuenta_origen_numero_cuenta")
        );
        
        CodigoCuentaCliente cuentaDestino = new CodigoCuentaCliente(
            rs.getString("cuenta_destino_banco_id"),
            rs.getString("cuenta_destino_sucursal_id"),
            rs.getString("cuenta_destino_numero_cuenta")
        );
        
        return new Transaccion(
            rs.getLong("id"),
            cuentaOrigen,
            cuentaDestino,
            rs.getBigDecimal("monto"),
            rs.getTimestamp("fecha").toLocalDateTime(),
            rs.getString("tipo"),
            rs.getString("descripcion")
        );
    }
}
