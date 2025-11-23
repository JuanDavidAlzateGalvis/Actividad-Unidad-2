/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.ui.frames;

import co.edu.udec.financias_bancarias.domain.ports.in.RealizarTransferenciaUseCase;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class RealizarTransferenciaFrame extends JFrame {

    private final RealizarTransferenciaUseCase realizarTransferenciaUseCase;

    private JTextField txtCuentaOrigen;
    private JTextField txtCuentaDestino;
    private JTextField txtMonto;
    private JTextArea txtDescripcion;
    private JTextArea txtResultado;

    public RealizarTransferenciaFrame(RealizarTransferenciaUseCase realizarTransferenciaUseCase) {
        this.realizarTransferenciaUseCase = realizarTransferenciaUseCase;
        initComponents();
    }

    private void initComponents() {
        setTitle("Realizar Transferencia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cuenta Origen
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Cuenta Origen (CCC):"), gbc);
        gbc.gridx = 1;
        txtCuentaOrigen = new JTextField(20);
        txtCuentaOrigen.setText("B001-SUC-001-1001"); // Ejemplo
        panel.add(txtCuentaOrigen, gbc);

        // Cuenta Destino
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Cuenta Destino (CCC):"), gbc);
        gbc.gridx = 1;
        txtCuentaDestino = new JTextField(20);
        txtCuentaDestino.setText("B001-SUC-001-1002"); // Ejemplo
        panel.add(txtCuentaDestino, gbc);

        // Monto
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Monto:"), gbc);
        gbc.gridx = 1;
        txtMonto = new JTextField(20);
        txtMonto.setText("100.00");
        panel.add(txtMonto, gbc);

        // Descripción
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panel.add(scrollDescripcion, gbc);

        // Botón
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnTransferir = new JButton("Realizar Transferencia");
        btnTransferir.setBackground(new Color(40, 167, 69));
        btnTransferir.setForeground(Color.WHITE);
        btnTransferir.setFocusPainted(false);
        btnTransferir.addActionListener(e -> realizarTransferencia());
        panel.add(btnTransferir, gbc);

        // Área de resultado
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        txtResultado = new JTextArea(10, 30);
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(248, 249, 250));
        txtResultado.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218)));
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        panel.add(scrollResultado, gbc);

        add(panel);
    }

    private void realizarTransferencia() {
        try {
            String cuentaOrigen = txtCuentaOrigen.getText().trim();
            String cuentaDestino = txtCuentaDestino.getText().trim();
            BigDecimal monto = new BigDecimal(txtMonto.getText().trim());
            String descripcion = txtDescripcion.getText().trim();

            if (cuentaOrigen.isEmpty() || cuentaDestino.isEmpty()) {
                throw new IllegalArgumentException("Las cuentas origen y destino son requeridas");
            }

            var transaccion = realizarTransferenciaUseCase.realizarTransferencia(
                cuentaOrigen, cuentaDestino, monto, descripcion
            );

            txtResultado.setText("✅ TRANSFERENCIA REALIZADA EXITOSAMENTE:\n\n" +
                "🔢 Transacción ID: " + transaccion.getId() + "\n" +
                "📤 Cuenta Origen: " + transaccion.getCuentaOrigen().toString() + "\n" +
                "📥 Cuenta Destino: " + transaccion.getCuentaDestino().toString() + "\n" +
                "💰 Monto: $" + transaccion.getMonto() + "\n" +
                "📅 Fecha: " + transaccion.getFecha() + "\n" +
                "📝 Descripción: " + transaccion.getDescripcion() + "\n\n" +
                "La transferencia se ha completado exitosamente.");

        } catch (Exception e) {
            txtResultado.setText("❌ ERROR AL REALIZAR LA TRANSFERENCIA:\n\n" +
                "Mensaje: " + e.getMessage() + "\n\n" +
                "Por favor verifique:\n" +
                "• Los CCC de las cuentas existen\n" +
                "• La cuenta origen tiene saldo suficiente\n" +
                "• El monto es mayor a cero\n" +
                "• Los campos están completos");
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
