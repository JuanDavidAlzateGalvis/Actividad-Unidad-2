/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.ports.out.TransaccionRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class PostgresTransaccionRepository implements TransaccionRepositoryPort {

    private final DataSource dataSource;

    public PostgresTransaccionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void guardar(Transaccion transaccion) {
        String sql = "INSERT INTO transacciones (cuenta_origen_banco_id, cuenta_origen_sucursal_id, cuenta_origen_numero_cuenta, " +
                "cuenta_destino_banco_id, cuenta_destino_sucursal_id, cuenta_destino_numero_cuenta, " +
                "monto, fecha, tipo, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, transaccion.getCuentaOrigen().bancoId());
            stmt.setString(2, transaccion.getCuentaOrigen().sucursalId());
            stmt.setString(3, transaccion.getCuentaOrigen().numeroCuenta());
            stmt.setString(4, transaccion.getCuentaDestino().bancoId());
            stmt.setString(5, transaccion.getCuentaDestino().sucursalId());
            stmt.setString(6, transaccion.getCuentaDestino().numeroCuenta());
            stmt.setBigDecimal(7, transaccion.getMonto());
            stmt.setTimestamp(8, Timestamp.valueOf(transaccion.getFecha()));
            stmt.setString(9, transaccion.getTipo());
            stmt.setString(10, transaccion.getDescripcion());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar la transacción, ninguna fila afectada.");
            }

            // Obtener el ID generado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    System.out.println("✅ Transacción guardada con ID: " + id);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la transacción guardada.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar transacción en PostgreSQL: " + e.getMessage(), e);
        }
    }
}
