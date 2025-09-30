/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.finanzas_bancarias.domain.model;

import co.edu.udec.financias_bancarias.domain.enums.*;
import co.edu.udec.financias_bancarias.domain.model.*;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.*;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author juana
 */
class CuentaTest {

    @Test
    void abrirCuentaAhorroSinAmortizacionDebeFallar() {
        var cliente = new Cliente(new ClienteId("C001"), "ACME S.A.", 
                new Direccion("Calle 1", "Cartagena", "130001"), ClienteTipo.ORGANIZACION);
        cliente.setDatosOrganizacion(TipoOrganizacion.EMPRESA, "Juan Rep", 600);

        var sucursal = new Sucursal("S01", new Direccion("Calle 2", "Cartagena", "130002"), "130002", "Cartagena");

        assertThatThrownBy(() -> new Cuenta(
                new CodigoCuentaCliente("B001","S01","2001"),
                TipoCuenta.AHORRO,
                new BigDecimal("1000"),
                cliente,
                sucursal,
                null
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("Ahorro requiere tipoAmortizacion");
    }
}