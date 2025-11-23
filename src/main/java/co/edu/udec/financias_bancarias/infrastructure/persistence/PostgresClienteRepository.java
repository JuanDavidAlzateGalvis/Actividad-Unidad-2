/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.ports.out.ClienteRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Cliente;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.ClienteId;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.Direccion;
import co.edu.udec.financias_bancarias.domain.enums.ClienteTipo;
import co.edu.udec.financias_bancarias.domain.enums.Sexo;
import co.edu.udec.financias_bancarias.domain.enums.TipoOrganizacion;
import co.edu.udec.financias_bancarias.domain.exceptions.ClienteNoEncontradoException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostgresClienteRepository implements ClienteRepositoryPort {
    
    private final DataSource dataSource;
    
    public PostgresClienteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public Cliente buscarPorId(ClienteId clienteId) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, clienteId.value());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearACliente(rs);
            } else {
                throw new ClienteNoEncontradoException(clienteId.value());
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente en PostgreSQL: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Cliente> buscarTodos() {
        String sql = "SELECT * FROM clientes ORDER BY nombre";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(mapearACliente(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar todos los clientes: " + e.getMessage(), e);
        }

        return clientes;
    }
    
    private Cliente mapearACliente(ResultSet rs) throws SQLException {
        ClienteId id = new ClienteId(rs.getString("id"));
        String nombre = rs.getString("nombre");
        Direccion direccion = new Direccion(
            rs.getString("calle"),
            rs.getString("ciudad"),
            rs.getString("codigo_postal")
        );
        ClienteTipo tipo = ClienteTipo.valueOf(rs.getString("tipo"));
        
        Cliente cliente = new Cliente(id, nombre, direccion, tipo);
        
        if (tipo == ClienteTipo.PERSONA) {
            Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            String sexoStr = rs.getString("sexo");
            if (fechaNacimiento != null && sexoStr != null) {
                cliente.setDatosPersona(
                    fechaNacimiento.toLocalDate(),
                    Sexo.valueOf(sexoStr)
                );
            }
        } else {
            String tipoOrgStr = rs.getString("tipo_organizacion");
            String representante = rs.getString("representante");
            Integer numEmpleados = rs.getObject("num_empleados", Integer.class);
            if (tipoOrgStr != null) {
                cliente.setDatosOrganizacion(
                    TipoOrganizacion.valueOf(tipoOrgStr),
                    representante,
                    numEmpleados
                );
            }
        }
        
        return cliente;
    }


    

}





    

