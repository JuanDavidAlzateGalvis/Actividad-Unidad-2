/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.ui.frames;

import co.edu.udec.financias_bancarias.domain.ports.in.ConsultarSaldoUseCase;
import co.edu.udec.financias_bancarias.domain.ports.in.ConsultarMovimientosUseCase;
import co.edu.udec.financias_bancarias.domain.ports.in.ConsultarClienteUseCase;
import co.edu.udec.financias_bancarias.domain.model.Transaccion;
import co.edu.udec.financias_bancarias.domain.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsultasFrame extends JFrame {

    private final ConsultarSaldoUseCase consultarSaldoUseCase;
    private final ConsultarMovimientosUseCase consultarMovimientosUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;

    private JTabbedPane tabbedPane;
    private JTextField txtConsultaCCC;
    private JTextArea txtResultadoSaldo;
    private JTextField txtMovimientosCCC;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextArea txtResultadoMovimientos;
    private JTextField txtClienteId;
    private JTextArea txtResultadoCliente;

    public ConsultasFrame(ConsultarSaldoUseCase consultarSaldoUseCase,
                         ConsultarMovimientosUseCase consultarMovimientosUseCase,
                         ConsultarClienteUseCase consultarClienteUseCase) {
        this.consultarSaldoUseCase = consultarSaldoUseCase;
        this.consultarMovimientosUseCase = consultarMovimientosUseCase;
        this.consultarClienteUseCase = consultarClienteUseCase;
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Consultas Bancarias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Pestaña de Consulta de Saldo
        tabbedPane.addTab("Consulta de Saldo", crearPanelSaldo());
        // Pestaña de Consulta de Movimientos
        tabbedPane.addTab("Consulta de Movimientos", crearPanelMovimientos());
        // Pestaña de Consulta de Cliente
        tabbedPane.addTab("Consulta de Cliente", crearPanelCliente());

        add(tabbedPane);
    }

    private JPanel crearPanelSaldo() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("CCC de la Cuenta:"));
        txtConsultaCCC = new JTextField(20);
        txtConsultaCCC.setText("B001-SUC-001-1001"); // Ejemplo
        panelSuperior.add(txtConsultaCCC);

        JButton btnConsultarSaldo = new JButton("Consultar Saldo");
        btnConsultarSaldo.setBackground(new Color(0, 123, 255));
        btnConsultarSaldo.setForeground(Color.WHITE);
        btnConsultarSaldo.addActionListener(e -> consultarSaldo());
        panelSuperior.add(btnConsultarSaldo);

        panel.add(panelSuperior, BorderLayout.NORTH);

        txtResultadoSaldo = new JTextArea(20, 50);
        txtResultadoSaldo.setEditable(false);
        txtResultadoSaldo.setBackground(new Color(248, 249, 250));
        JScrollPane scroll = new JScrollPane(txtResultadoSaldo);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelMovimientos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSuperior.add(new JLabel("CCC de la Cuenta:"));
        txtMovimientosCCC = new JTextField();
        txtMovimientosCCC.setText("B001-SUC-001-1001"); // Ejemplo
        panelSuperior.add(txtMovimientosCCC);

        panelSuperior.add(new JLabel("Fecha Inicio (yyyy-MM-dd HH:mm):"));
        txtFechaInicio = new JTextField();
        txtFechaInicio.setText("2024-01-01 00:00");
        panelSuperior.add(txtFechaInicio);

        panelSuperior.add(new JLabel("Fecha Fin (yyyy-MM-dd HH:mm):"));
        txtFechaFin = new JTextField();
        txtFechaFin.setText("2024-12-31 23:59");
        panelSuperior.add(txtFechaFin);

        JButton btnConsultarMovimientos = new JButton("Consultar Movimientos");
        btnConsultarMovimientos.setBackground(new Color(40, 167, 69));
        btnConsultarMovimientos.setForeground(Color.WHITE);
        btnConsultarMovimientos.addActionListener(e -> consultarMovimientos());
        panelSuperior.add(btnConsultarMovimientos);

        panel.add(panelSuperior, BorderLayout.NORTH);

        txtResultadoMovimientos = new JTextArea(20, 50);
        txtResultadoMovimientos.setEditable(false);
        txtResultadoMovimientos.setBackground(new Color(248, 249, 250));
        JScrollPane scroll = new JScrollPane(txtResultadoMovimientos);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelCliente() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("ID del Cliente:"));
        txtClienteId = new JTextField(20);
        txtClienteId.setText("CLI-001"); // Ejemplo
        panelSuperior.add(txtClienteId);

        JButton btnConsultarCliente = new JButton("Consultar Cliente");
        btnConsultarCliente.setBackground(new Color(111, 66, 193));
        btnConsultarCliente.setForeground(Color.WHITE);
        btnConsultarCliente.addActionListener(e -> consultarCliente());
        panelSuperior.add(btnConsultarCliente);

        JButton btnConsultarTodos = new JButton("Consultar Todos");
        btnConsultarTodos.setBackground(new Color(253, 126, 20));
        btnConsultarTodos.setForeground(Color.WHITE);
        btnConsultarTodos.addActionListener(e -> consultarTodosClientes());
        panelSuperior.add(btnConsultarTodos);

        panel.add(panelSuperior, BorderLayout.NORTH);

        txtResultadoCliente = new JTextArea(20, 50);
        txtResultadoCliente.setEditable(false);
        txtResultadoCliente.setBackground(new Color(248, 249, 250));
        JScrollPane scroll = new JScrollPane(txtResultadoCliente);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void consultarSaldo() {
        try {
            String ccc = txtConsultaCCC.getText().trim();
            if (ccc.isEmpty()) {
                throw new IllegalArgumentException("El CCC es requerido");
            }

            var cuenta = consultarSaldoUseCase.consultarSaldo(ccc);
            txtResultadoSaldo.setText("✅ INFORMACIÓN DE LA CUENTA:\n\n" +
                "🔢 CCC: " + cuenta.getCcc().toString() + "\n" +
                "👤 Titular: " + cuenta.getCliente().getNombre() + "\n" +
                "🏦 Tipo: " + cuenta.getTipo() + "\n" +
                "💰 Saldo Actual: $" + cuenta.getSaldoActual() + "\n" +
                "📊 Saldo Medio: $" + cuenta.getSaldoMedio() + "\n" +
                "📅 Fecha Apertura: " + cuenta.getFechaApertura() + "\n" +
                "🏢 Sucursal: " + cuenta.getSucursal().getNumero());

        } catch (Exception e) {
            txtResultadoSaldo.setText("❌ ERROR EN CONSULTA:\n\n" + e.getMessage());
        }
    }

    private void consultarMovimientos() {
        try {
            String ccc = txtMovimientosCCC.getText().trim();
            if (ccc.isEmpty()) {
                throw new IllegalArgumentException("El CCC es requerido");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaInicio = LocalDateTime.parse(txtFechaInicio.getText().trim(), formatter);
            LocalDateTime fechaFin = LocalDateTime.parse(txtFechaFin.getText().trim(), formatter);

            List<Transaccion> movimientos = consultarMovimientosUseCase.consultarMovimientosPorCuentaYFecha(ccc, fechaInicio, fechaFin);

            StringBuilder resultado = new StringBuilder();
            resultado.append("✅ MOVIMIENTOS DE LA CUENTA: ").append(ccc).append("\n\n");
            resultado.append("Período: ").append(fechaInicio).append(" hasta ").append(fechaFin).append("\n\n");

            if (movimientos.isEmpty()) {
                resultado.append("No se encontraron movimientos en el período seleccionado.");
            } else {
                resultado.append(String.format("%-5s %-20s %-25s %-25s %-10s %-30s\n", 
                    "ID", "Fecha", "Cuenta Origen", "Cuenta Destino", "Monto", "Descripción"));
                resultado.append("--------------------------------------------------------------------------------------------------------------------\n");
                for (Transaccion t : movimientos) {
                    resultado.append(String.format("%-5d %-20s %-25s %-25s $%-9.2f %-30s\n",
                        t.getId(),
                        t.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        t.getCuentaOrigen().toString(),
                        t.getCuentaDestino().toString(),
                        t.getMonto(),
                        t.getDescripcion()));
                }
            }

            txtResultadoMovimientos.setText(resultado.toString());

        } catch (Exception e) {
            txtResultadoMovimientos.setText("❌ ERROR EN CONSULTA:\n\n" + e.getMessage());
        }
    }

    private void consultarCliente() {
        try {
            String clienteId = txtClienteId.getText().trim();
            if (clienteId.isEmpty()) {
                throw new IllegalArgumentException("El ID del cliente es requerido");
            }

            Cliente cliente = consultarClienteUseCase.consultarClientePorId(clienteId);

            txtResultadoCliente.setText("✅ INFORMACIÓN DEL CLIENTE:\n\n" +
                "🔢 ID: " + cliente.getId().value() + "\n" +
                "👤 Nombre: " + cliente.getNombre() + "\n" +
                "🏠 Dirección: " + cliente.getDireccion().toString() + "\n" +
                "📋 Tipo: " + cliente.getTipo() + "\n" +
                (cliente.getTipo().toString().equals("PERSONA") ? 
                 "🎂 Fecha Nacimiento: " + cliente.getFechaNacimiento() + "\n" +
                 "⚤ Sexo: " + cliente.getSexo() : 
                 "🏢 Tipo Organización: " + cliente.getTipoOrganizacion() + "\n" +
                 "👔 Representante: " + cliente.getRepresentante() + "\n" +
                 "👥 Núm. Empleados: " + cliente.getNumEmpleados()));

        } catch (Exception e) {
            txtResultadoCliente.setText("❌ ERROR EN CONSULTA:\n\n" + e.getMessage());
        }
    }

    private void consultarTodosClientes() {
        try {
            List<Cliente> clientes = consultarClienteUseCase.consultarTodosLosClientes();

            StringBuilder resultado = new StringBuilder();
            resultado.append("✅ LISTA DE TODOS LOS CLIENTES\n\n");

            if (clientes.isEmpty()) {
                resultado.append("No hay clientes registrados.");
            } else {
                resultado.append(String.format("%-15s %-30s %-20s %-10s\n", 
                    "ID", "Nombre", "Dirección", "Tipo"));
                resultado.append("-----------------------------------------------------------------\n");
                for (Cliente c : clientes) {
                    resultado.append(String.format("%-15s %-30s %-20s %-10s\n",
                        c.getId().value(),
                        c.getNombre(),
                        c.getDireccion().toString(),
                        c.getTipo()));
                }
            }

            txtResultadoCliente.setText(resultado.toString());

        } catch (Exception e) {
            txtResultadoCliente.setText("❌ ERROR EN CONSULTA:\n\n" + e.getMessage());
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}