/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.ui.launchers;

import javax.swing.*;
import java.awt.*;

public class MainLauncher {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Sistema Bancario - Menú Principal");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(400, 300);
            mainFrame.setLocationRelativeTo(null);
            
            JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JButton btnApertura = new JButton("Apertura de Cuentas");
            JButton btnTransferencia = new JButton("Transferencias");
            JButton btnConsulta = new JButton("Consultas");
            JButton btnReportes = new JButton("Reportes");
            
            btnApertura.addActionListener(e -> {
                mainFrame.dispose();
                AperturaCuentaLauncher.main(new String[]{});
            });
            
            btnTransferencia.addActionListener(e -> {
                mainFrame.dispose();
                RealizarTransferenciaLauncher.main(new String[]{});
            });
            
            btnConsulta.addActionListener(e -> {
                mainFrame.dispose();
                ConsultasLauncher.main(new String[]{});
            });
            
            btnReportes.addActionListener(e -> {
                JOptionPane.showMessageDialog(mainFrame, "Flujo en desarrollo");
            });
            
            panel.add(btnApertura);
            panel.add(btnTransferencia);
            panel.add(btnConsulta);
            panel.add(btnReportes);
            
            mainFrame.add(panel);
            mainFrame.setVisible(true);
        });
    }
}
