/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.ui.launchers;

import co.edu.udec.financias_bancarias.application.usecases.ConsultarSaldoUseCaseImpl;
import co.edu.udec.financias_bancarias.application.usecases.ConsultarMovimientosUseCaseImpl;
import co.edu.udec.financias_bancarias.application.usecases.ConsultarClienteUseCaseImpl;
import co.edu.udec.financias_bancarias.infrastructure.persistence.PostgresCuentaRepository;
import co.edu.udec.financias_bancarias.infrastructure.persistence.PostgresTransaccionRepository;
import co.edu.udec.financias_bancarias.infrastructure.persistence.PostgresClienteRepository;
import co.edu.udec.financias_bancarias.infrastructure.ui.frames.ConsultasFrame;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import co.edu.udec.financias_bancarias.Financias_Bancarias;

import javax.swing.*;
import javax.sql.DataSource;

public class ConsultasLauncher {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        try {
            System.out.println("🚀 INICIANDO INTERFAZ DE CONSULTAS");

            ConfigurableApplicationContext context = SpringApplication.run(Financias_Bancarias.class, args);

            DataSource dataSource = context.getBean(DataSource.class);

            PostgresCuentaRepository cuentaRepo = new PostgresCuentaRepository(dataSource);
            PostgresTransaccionRepository transaccionRepo = new PostgresTransaccionRepository(dataSource);
            PostgresClienteRepository clienteRepo = new PostgresClienteRepository(dataSource);

            ConsultarSaldoUseCaseImpl consultarSaldoUseCase = new ConsultarSaldoUseCaseImpl(cuentaRepo);
            ConsultarMovimientosUseCaseImpl consultarMovimientosUseCase = new ConsultarMovimientosUseCaseImpl(transaccionRepo);
            ConsultarClienteUseCaseImpl consultarClienteUseCase = new ConsultarClienteUseCaseImpl(clienteRepo);

            SwingUtilities.invokeLater(() -> {
                ConsultasFrame frame = new ConsultasFrame(
                    consultarSaldoUseCase,
                    consultarMovimientosUseCase,
                    consultarClienteUseCase
                );
                frame.mostrar();
                System.out.println("✅ INTERFAZ DE CONSULTAS INICIADA CORRECTAMENTE");
            });

        } catch (Exception e) {
            System.err.println("❌ Error al iniciar interfaz de consultas: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error al iniciar la aplicación de consultas:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

