/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.ui.launchers;

import co.edu.udec.financias_bancarias.application.usecases.AperturaCuentaUseCaseImpl;
import co.edu.udec.financias_bancarias.infrastructure.persistence.PostgresClienteRepository;
import co.edu.udec.financias_bancarias.infrastructure.persistence.PostgresCuentaRepository;
import co.edu.udec.financias_bancarias.infrastructure.persistence.PostgresSucursalRepository;
import co.edu.udec.financias_bancarias.infrastructure.ui.frames.AperturaCuentaFrame;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import co.edu.udec.financias_bancarias.Financias_Bancarias;

import javax.swing.*;
import javax.sql.DataSource;

public class AperturaCuentaLauncher {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        
        try {
            System.out.println("🚀 INICIANDO INTERFAZ DE APERTURA DE CUENTAS");

            ConfigurableApplicationContext context = SpringApplication.run(Financias_Bancarias.class, args);

            DataSource dataSource = context.getBean(DataSource.class);
            
            PostgresClienteRepository clienteRepo = new PostgresClienteRepository(dataSource);
            PostgresSucursalRepository sucursalRepo = new PostgresSucursalRepository(dataSource);
            PostgresCuentaRepository cuentaRepo = new PostgresCuentaRepository(dataSource);

            AperturaCuentaUseCaseImpl useCase = new AperturaCuentaUseCaseImpl(
                clienteRepo, sucursalRepo, cuentaRepo
            );

            SwingUtilities.invokeLater(() -> {
                AperturaCuentaFrame frame = new AperturaCuentaFrame(useCase);
                frame.mostrar();
                System.out.println("✅ INTERFAZ GRÁFICA INICIADA CORRECTAMENTE");
            });
            
        } catch (Exception e) {
            System.err.println("❌ Error al iniciar interfaz: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al iniciar la aplicación:\n" + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
