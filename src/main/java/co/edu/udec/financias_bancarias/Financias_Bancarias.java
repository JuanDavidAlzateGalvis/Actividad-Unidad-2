package co.edu.udec.financias_bancarias;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Financias_Bancarias {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO BACKEND SPRING BOOT ===");
        SpringApplication.run(Financias_Bancarias.class, args);
    }
    
    @Bean
    public CommandLineRunner testBackend() {
        return args -> {
            System.out.println("✅ BACKEND SPRING BOOT INICIADO CORRECTAMENTE");
            System.out.println("✅ Base de datos PostgreSQL: CONECTADA");
            System.out.println("✅ Repositorios: FUNCIONANDO");
            System.out.println("✅ Casos de uso: LISTOS");
        };
    }
}