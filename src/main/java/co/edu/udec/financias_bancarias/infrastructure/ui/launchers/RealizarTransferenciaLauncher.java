/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.ui.launchers;

import co.edu.udec.financias_bancarias.application.usecases.RealizarTransferenciaUseCaseImpl;
import co.edu.udec.financias_bancarias.infrastructure.persistence.PostgresCuentaRepository;
import co.edu.udec.financias_bancarias.infrastructure.persistence.PostgresTransaccionRepository;
import co.edu.udec.financias_bancarias.infrastructure.ui.frames.RealizarTransferenciaFrame;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import co.edu.udec.financias_bancarias.Financias_Bancarias;

import javax.swing.*;
import javax.sql.DataSource;

public class RealizarTransferenciaLauncher {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        try {
            System.out.println("🚀 INICIANDO INTERFAZ DE TRANSFERENCIAS");

            ConfigurableApplicationContext context = SpringApplication.run(Financias_Bancarias.class, args);

            DataSource dataSource = context.getBean(DataSource.class);

            PostgresCuentaRepository cuentaRepo = new PostgresCuentaRepository(dataSource);
            PostgresTransaccionRepository transaccionRepo = new PostgresTransaccionRepository(dataSource);

            RealizarTransferenciaUseCaseImpl useCase = new RealizarTransferenciaUseCaseImpl(
                cuentaRepo, transaccionRepo
            );

            SwingUtilities.invokeLater(() -> {
                RealizarTransferenciaFrame frame = new RealizarTransferenciaFrame(useCase);
                frame.mostrar();
                System.out.println("✅ INTERFAZ DE TRANSFERENCIAS INICIADA CORRECTAMENTE");
            });

        } catch (Exception e) {
            System.err.println("❌ Error al iniciar interfaz de transferencias: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error al iniciar la aplicación de transferencias:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
