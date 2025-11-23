/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.persistence;

import co.edu.udec.financias_bancarias.domain.ports.out.CuentaRepositoryPort;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.model.Cliente;
import co.edu.udec.financias_bancarias.domain.model.Sucursal;
import co.edu.udec.financias_bancarias.domain.enums.ClienteTipo;
import co.edu.udec.financias_bancarias.domain.enums.TipoCuenta;
import co.edu.udec.financias_bancarias.domain.enums.TipoArmotizacion;
import co.edu.udec.financias_bancarias.domain.enums.Sexo;
import co.edu.udec.financias_bancarias.domain.enums.TipoOrganizacion;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.ClienteId;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.Direccion;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.math.BigDecimal;

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
        String sql = "SELECT c.*, cli.*, suc.calle as suc_calle, suc.ciudad as suc_ciudad, " +
                    "suc.codigo_postal as suc_codigo_postal, suc.director_dni " +
                    "FROM cuentas c " +
                    "JOIN clientes cli ON c.cliente_id = cli.id " +
                    "JOIN sucursales suc ON c.sucursal_numero = suc.numero " +
                    "WHERE c.banco_id = ? AND c.sucursal_id = ? AND c.numero_cuenta = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ccc.bancoId());
            stmt.setString(2, ccc.sucursalId());
            stmt.setString(3, ccc.numeroCuenta());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearACuenta(rs);
            } else {
                throw new RuntimeException("Cuenta no encontrada con CCC: " + ccc.toString());
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cuenta por CCC: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void actualizar(Cuenta cuenta) {
        String sql = "UPDATE cuentas SET saldo_actual = ?, saldo_medio = ? " +
                    "WHERE banco_id = ? AND sucursal_id = ? AND numero_cuenta = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBigDecimal(1, cuenta.getSaldoActual());
            stmt.setBigDecimal(2, cuenta.getSaldoMedio());
            stmt.setString(3, cuenta.getCcc().bancoId());
            stmt.setString(4, cuenta.getCcc().sucursalId());
            stmt.setString(5, cuenta.getCcc().numeroCuenta());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("No se pudo actualizar la cuenta: " + cuenta.getCcc().toString());
            }
            
            System.out.println("✅ Cuenta actualizada en PostgreSQL: " + cuenta.getCcc().toString());
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cuenta: " + e.getMessage(), e);
        }
    }
    
    private Cuenta mapearACuenta(ResultSet rs) throws SQLException {
        ClienteId clienteId = new ClienteId(rs.getString("cliente_id"));
        String nombreCliente = rs.getString("nombre");
        Direccion direccionCliente = new Direccion(
            rs.getString("calle"),
            rs.getString("ciudad"),
            rs.getString("codigo_postal")
        );
        ClienteTipo tipoCliente = ClienteTipo.valueOf(rs.getString("tipo"));

        Cliente cliente = new Cliente(clienteId, nombreCliente, direccionCliente, tipoCliente);

        if (tipoCliente == ClienteTipo.PERSONA) {
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

        String numeroSucursal = rs.getString("sucursal_numero");
        String sucursalCiudad = rs.getString("suc_ciudad");
        String sucursalCodigoPostal = rs.getString("suc_codigo_postal");
        Direccion direccionSucursal = new Direccion(
            rs.getString("suc_calle"),
            sucursalCiudad,
            sucursalCodigoPostal
        );

        Sucursal sucursal = new Sucursal(
            numeroSucursal, 
            direccionSucursal, 
            sucursalCodigoPostal, 
            sucursalCiudad
        );

        CodigoCuentaCliente ccc = new CodigoCuentaCliente(
            rs.getString("banco_id"),
            rs.getString("sucursal_id"),
            rs.getString("numero_cuenta")
        );
        TipoCuenta tipoCuenta = TipoCuenta.valueOf(rs.getString("tipo"));
        BigDecimal saldoActual = rs.getBigDecimal("saldo_actual");
        BigDecimal saldoMedio = rs.getBigDecimal("saldo_medio");

        String tipoAmortizacionStr = rs.getString("tipo_amortizacion");
        TipoArmotizacion tipoAmortizacion = null;
        if (tipoAmortizacionStr != null) {
            tipoAmortizacion = TipoArmotizacion.valueOf(tipoAmortizacionStr);
        }

        Cuenta cuenta = new Cuenta(ccc, tipoCuenta, saldoActual, cliente, sucursal, tipoAmortizacion);

        cuenta.setSaldoMedio(saldoMedio);

        return cuenta;
    }
}
