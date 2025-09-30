/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.finanzas_bancarias.domain.valueobjects;

import co.edu.udec.financias_bancarias.domain.valueobjetcs.CodigoCuentaCliente;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author juana
 */
class CodigoCuentaClienteTest {

    @Test
    void debeCrearCCCValido() {
        var ccc = new CodigoCuentaCliente("B001", "S01", "1001");
        assertThat(ccc.toString()).isEqualTo("B001-S01-1001");
    }

    @Test
    void debeFallarSiBancoIdEsNulo() {
        assertThatThrownBy(() -> new CodigoCuentaCliente(null, "S01", "1001"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("bancoId inválido");
    }
}
