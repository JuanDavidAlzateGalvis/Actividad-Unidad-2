/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.ports.out.SucursalRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Sucursal;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.Direccion;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class PostgresSucursalRepository implements SucursalRepositoryPort {

    private final DataSource dataSource;

    public PostgresSucursalRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Sucursal buscarPorId(String sucursalId) {
        String sql = "SELECT * FROM sucursales WHERE numero = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, sucursalId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearASucursal(rs);
            } else {
                throw new RuntimeException("Sucursal no encontrada: " + sucursalId);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar sucursal en PostgreSQL: " + e.getMessage(), e);
        }
    }

    private Sucursal mapearASucursal(ResultSet rs) throws SQLException {
        String numero = rs.getString("numero");
        Direccion direccion = new Direccion(
            rs.getString("calle"),
            rs.getString("ciudad"),
            rs.getString("codigo_postal")
        );
        String codigoPostal = rs.getString("codigo_postal");
        String ciudad = rs.getString("ciudad");
        
        return new Sucursal(numero, direccion, codigoPostal, ciudad);
    }
}