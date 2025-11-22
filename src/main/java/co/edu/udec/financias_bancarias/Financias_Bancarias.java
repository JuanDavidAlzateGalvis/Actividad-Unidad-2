/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package co.edu.udec.financias_bancarias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import co.edu.udec.financias_bancarias.domain.ports.in.AperturaCuentaUseCase;
import co.edu.udec.financias_bancarias.domain.enums.TipoArmotizacion;
import co.edu.udec.financias_bancarias.domain.enums.TipoCuenta;
import java.math.BigDecimal;

@SpringBootApplication
public class Financias_Bancarias {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Financias_Bancarias.class, args);
        
        // PRUEBA AUTOMÁTICA - Se ejecuta al iniciar
        probarAperturaCuenta(context);
    }
    
    private static void probarAperturaCuenta(ConfigurableApplicationContext context) {
        try {
            AperturaCuentaUseCase useCase = context.getBean(AperturaCuentaUseCase.class);
            
            System.out.println("🧪 PROBANDO APERTURA DE CUENTA...");
            
            var cuenta = useCase.abrirCuenta(
                "CLI-001",
                TipoCuenta.AHORRO,
                new BigDecimal("500.00"),
                "SUC-001", 
                TipoArmotizacion.MENSUAL
            );
            
            System.out.println("✅ CUENTA CREADA EXITOSAMENTE:");
            System.out.println("   CCC: " + cuenta.getCcc().toString());
            System.out.println("   Titular: " + cuenta.getCliente().getNombre());
            System.out.println("   Saldo: " + cuenta.getSaldoActual());
            System.out.println("   Tipo: " + cuenta.getTipo());
            
        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
        }
    }
}
