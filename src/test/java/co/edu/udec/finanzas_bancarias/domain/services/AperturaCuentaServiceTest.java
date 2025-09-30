/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.finanzas_bancarias.domain.services;

import co.edu.udec.financias_bancarias.domain.enums.*;
import co.edu.udec.financias_bancarias.domain.model.*;
import co.edu.udec.financias_bancarias.domain.services.AperturaCuentaService;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.*;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author juana
 */
class AperturaCuentaServiceTest {

    @Test
    void debeAbrirCuentaCorrienteConDepositoValido() {
        var cliente = new Cliente(new ClienteId("C002"), "Pedro Perez", 
                new Direccion("Calle 3", "Cartagena", "130003"), ClienteTipo.PERSONA);
        cliente.setDatosPersona(java.time.LocalDate.of(1990,5,20), Sexo.MASCULINO);

        var sucursal = new Sucursal("S02", new Direccion("Av 1", "Cartagena", "130004"), "130004", "Cartagena");

        var service = new AperturaCuentaService("B001");

        var cuenta = service.abrirCuenta(cliente, TipoCuenta.CORRIENTE, new BigDecimal("500"), sucursal, null);

        assertThat(cuenta.getSaldoActual()).isEqualTo(new BigDecimal("500"));
        assertThat(cuenta.getTipo()).isEqualTo(TipoCuenta.CORRIENTE);
    }

    @Test
    void debeFallarSiDepositoInicialEsMenorAlRequeridoEnCuentaCorriente() {
        var cliente = new Cliente(new ClienteId("C003"), "Maria Lopez", 
                new Direccion("Calle 4", "Cartagena", "130005"), ClienteTipo.PERSONA);
        cliente.setDatosPersona(java.time.LocalDate.of(1985,7,10), Sexo.FEMENINO);

        var sucursal = new Sucursal("S03", new Direccion("Av 2", "Cartagena", "130006"), "130006", "Cartagena");

        var service = new AperturaCuentaService("B001");

        assertThatThrownBy(() -> service.abrirCuenta(cliente, TipoCuenta.CORRIENTE, new BigDecimal("50"), sucursal, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Depósito mínimo para cuenta corriente");
    }
}