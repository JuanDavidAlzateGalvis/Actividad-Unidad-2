/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.ui.frames;

import co.edu.udec.financias_bancarias.domain.ports.in.AperturaCuentaUseCase;
import co.edu.udec.financias_bancarias.domain.enums.TipoArmotizacion;
import co.edu.udec.financias_bancarias.domain.enums.TipoCuenta;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class AperturaCuentaFrame extends JFrame {
    
    private final AperturaCuentaUseCase aperturaCuentaUseCase;
    
    private JTextField txtClienteId;
    private JComboBox<String> cmbTipoCuenta;
    private JTextField txtDepositoInicial;
    private JTextField txtSucursalId;
    private JComboBox<String> cmbTipoAmortizacion;
    private JTextArea txtResultado;
    
    public AperturaCuentaFrame(AperturaCuentaUseCase aperturaCuentaUseCase) {
        this.aperturaCuentaUseCase = aperturaCuentaUseCase;
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Apertura de Cuenta Bancaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Cliente ID:"), gbc);
        gbc.gridx = 1;
        txtClienteId = new JTextField(20);
        txtClienteId.setText("CLI-001"); 
        panel.add(txtClienteId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Tipo Cuenta:"), gbc);
        gbc.gridx = 1;
        cmbTipoCuenta = new JComboBox<>(new String[]{"AHORRO", "CORRIENTE"});
        panel.add(cmbTipoCuenta, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Depósito Inicial:"), gbc);
        gbc.gridx = 1;
        txtDepositoInicial = new JTextField(20);
        txtDepositoInicial.setText("500.00"); 
        panel.add(txtDepositoInicial, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Sucursal ID:"), gbc);
        gbc.gridx = 1;
        txtSucursalId = new JTextField(20);
        txtSucursalId.setText("SUC-001"); 
        panel.add(txtSucursalId, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Tipo Amortización:"), gbc);
        gbc.gridx = 1;
        cmbTipoAmortizacion = new JComboBox<>(new String[]{"MENSUAL", "TRIMESTRAL", "ANUAL"});
        panel.add(cmbTipoAmortizacion, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnAbrirCuenta = new JButton("Abrir Cuenta");
        btnAbrirCuenta.setBackground(new Color(0, 123, 255));
        btnAbrirCuenta.setForeground(Color.WHITE);
        btnAbrirCuenta.setFocusPainted(false);
        btnAbrirCuenta.addActionListener(e -> abrirCuenta());
        panel.add(btnAbrirCuenta, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        txtResultado = new JTextArea(10, 30);
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(248, 249, 250));
        txtResultado.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218)));
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        panel.add(scrollPane, gbc);
        
        add(panel);
    }
    
    private void abrirCuenta() {
        try {
            String clienteId = txtClienteId.getText().trim();
            TipoCuenta tipoCuenta = TipoCuenta.valueOf(cmbTipoCuenta.getSelectedItem().toString());
            BigDecimal depositoInicial = new BigDecimal(txtDepositoInicial.getText().trim());
            String sucursalId = txtSucursalId.getText().trim();
            TipoArmotizacion tipoAmortizacion = TipoArmotizacion.valueOf(
                cmbTipoAmortizacion.getSelectedItem().toString()
            );

            if (clienteId.isEmpty() || sucursalId.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son requeridos");
            }
            
            var cuenta = aperturaCuentaUseCase.abrirCuenta(
                clienteId, tipoCuenta, depositoInicial, sucursalId, tipoAmortizacion
            );

            txtResultado.setText("✅ CUENTA CREADA EXITOSAMENTE:\n\n" +
                "🔢 Código de Cuenta: " + cuenta.getCcc().toString() + "\n" +
                "👤 Titular: " + cuenta.getCliente().getNombre() + "\n" +
                "🏦 Tipo de Cuenta: " + cuenta.getTipo() + "\n" +
                "💰 Saldo Inicial: $" + cuenta.getSaldoActual() + "\n" +
                "📅 Fecha de Apertura: " + cuenta.getFechaApertura() + "\n" +
                "🏢 Sucursal: " + cuenta.getSucursal().getNumero() + "\n\n" +
                "La cuenta ha sido registrada exitosamente en el sistema.");
                
        } catch (Exception e) {
            txtResultado.setText("❌ ERROR AL CREAR LA CUENTA:\n\n" +
                "Mensaje: " + e.getMessage() + "\n\n" +
                "Por favor verifique:\n" +
                "• El Cliente ID existe en el sistema\n" +
                "• La Sucursal ID es válida\n" +
                "• El depósito cumple con los mínimos requeridos\n" +
                "• Todos los campos están completos");
        }
    }
    
    public void mostrar() {
        setVisible(true);
    }
}
