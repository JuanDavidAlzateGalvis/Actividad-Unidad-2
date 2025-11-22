/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package co.edu.udec.financias_bancarias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Financias_Bancarias {
    public static void main(String[] args) {
        SpringApplication.run(Financias_Bancarias.class, args);
        System.out.println("🚀 Aplicación de Finanzas Bancarias iniciada!");
        System.out.println("📝 Endpoint disponible: POST http://localhost:8080/api/cuentas/abrir");
    }
}
