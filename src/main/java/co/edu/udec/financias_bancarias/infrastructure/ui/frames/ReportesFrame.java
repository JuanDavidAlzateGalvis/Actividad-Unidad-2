/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.infrastructure.ui.frames;

import co.edu.udec.financias_bancarias.domain.ports.in.GenerarReporteCuentasUseCase;
import co.edu.udec.financias_bancarias.domain.ports.in.GenerarReporteTransaccionesUseCase;
import co.edu.udec.financias_bancarias.domain.ports.in.GenerarReporteSucursalUseCase;
import co.edu.udec.financias_bancarias.domain.model.Cuenta;
import co.edu.udec.financias_bancarias.domain.model.Transaccion;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ReportesFrame extends JFrame {

    private final GenerarReporteCuentasUseCase reporteCuentasUseCase;
    private final GenerarReporteTransaccionesUseCase reporteTransaccionesUseCase;
    private final GenerarReporteSucursalUseCase reporteSucursalUseCase;

    private JTabbedPane tabbedPane;
    private JTextField txtClienteIdReporte;
    private JTextArea txtResultadoCuentas;
    private JTextField txtFechaInicioReporte;
    private JTextField txtFechaFinReporte;
    private JTextArea txtResultadoTransacciones;
    private JTextArea txtResultadoSucursal;

    public ReportesFrame(GenerarReporteCuentasUseCase reporteCuentasUseCase,
                        GenerarReporteTransaccionesUseCase reporteTransaccionesUseCase,
                        GenerarReporteSucursalUseCase reporteSucursalUseCase) {
        this.reporteCuentasUseCase = reporteCuentasUseCase;
        this.reporteTransaccionesUseCase = reporteTransaccionesUseCase;
        this.reporteSucursalUseCase = reporteSucursalUseCase;
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Reportes Bancarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Pestaña de Reporte de Cuentas
        tabbedPane.addTab("Reporte de Cuentas", crearPanelCuentas());
        // Pestaña de Reporte de Transacciones
        tabbedPane.addTab("Reporte de Transacciones", crearPanelTransacciones());
        // Pestaña de Reporte de Sucursal
        tabbedPane.addTab("Reporte de Sucursales", crearPanelSucursal());

        add(tabbedPane);
    }

    private JPanel crearPanelCuentas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("ID del Cliente:"));
        txtClienteIdReporte = new JTextField(15);
        txtClienteIdReporte.setText("CLI-001"); // Ejemplo
        panelSuperior.add(txtClienteIdReporte);

        JButton btnReporteCliente = new JButton("Reporte por Cliente");
        btnReporteCliente.setBackground(new Color(0, 123, 255));
        btnReporteCliente.setForeground(Color.WHITE);
        btnReporteCliente.addActionListener(e -> generarReporteCuentasPorCliente());
        panelSuperior.add(btnReporteCliente);

        JButton btnReporteTodas = new JButton("Reporte General");
        btnReporteTodas.setBackground(new Color(108, 117, 125));
        btnReporteTodas.setForeground(Color.WHITE);
        btnReporteTodas.addActionListener(e -> generarReporteTodasLasCuentas());
        panelSuperior.add(btnReporteTodas);

        panel.add(panelSuperior, BorderLayout.NORTH);

        txtResultadoCuentas = new JTextArea(20, 60);
        txtResultadoCuentas.setEditable(false);
        txtResultadoCuentas.setBackground(new Color(248, 249, 250));
        JScrollPane scroll = new JScrollPane(txtResultadoCuentas);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelTransacciones() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 5, 5));
        panelSuperior.add(new JLabel("Fecha Inicio (yyyy-MM-dd HH:mm):"));
        txtFechaInicioReporte = new JTextField();
        txtFechaInicioReporte.setText("2024-01-01 00:00");
        panelSuperior.add(txtFechaInicioReporte);

        panelSuperior.add(new JLabel("Fecha Fin (yyyy-MM-dd HH:mm):"));
        txtFechaFinReporte = new JTextField();
        txtFechaFinReporte.setText("2024-12-31 23:59");
        panelSuperior.add(txtFechaFinReporte);

        JButton btnReporteTransacciones = new JButton("Generar Reporte");
        btnReporteTransacciones.setBackground(new Color(40, 167, 69));
        btnReporteTransacciones.setForeground(Color.WHITE);
        btnReporteTransacciones.addActionListener(e -> generarReporteTransacciones());
        panelSuperior.add(btnReporteTransacciones);

        panel.add(panelSuperior, BorderLayout.NORTH);

        txtResultadoTransacciones = new JTextArea(20, 60);
        txtResultadoTransacciones.setEditable(false);
        txtResultadoTransacciones.setBackground(new Color(248, 249, 250));
        JScrollPane scroll = new JScrollPane(txtResultadoTransacciones);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelSucursal() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton btnReporteSaldos = new JButton("Reporte de Saldos");
        btnReporteSaldos.setBackground(new Color(111, 66, 193));
        btnReporteSaldos.setForeground(Color.WHITE);
        btnReporteSaldos.addActionListener(e -> generarReporteSaldos());
        panelSuperior.add(btnReporteSaldos);

        JButton btnReporteCantidad = new JButton("Reporte de Cuentas");
        btnReporteCantidad.setBackground(new Color(253, 126, 20));
        btnReporteCantidad.setForeground(Color.WHITE);
        btnReporteCantidad.addActionListener(e -> generarReporteCantidadCuentas());
        panelSuperior.add(btnReporteCantidad);

        panel.add(panelSuperior, BorderLayout.NORTH);

        txtResultadoSucursal = new JTextArea(20, 60);
        txtResultadoSucursal.setEditable(false);
        txtResultadoSucursal.setBackground(new Color(248, 249, 250));
        JScrollPane scroll = new JScrollPane(txtResultadoSucursal);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void generarReporteCuentasPorCliente() {
        try {
            String clienteId = txtClienteIdReporte.getText().trim();
            if (clienteId.isEmpty()) {
                throw new IllegalArgumentException("El ID del cliente es requerido");
            }

            List<Cuenta> cuentas = reporteCuentasUseCase.generarReporteCuentasPorCliente(clienteId);

            StringBuilder resultado = new StringBuilder();
            resultado.append("📊 REPORTE DE CUENTAS POR CLIENTE: ").append(clienteId).append("\n\n");

            if (cuentas.isEmpty()) {
                resultado.append("El cliente no tiene cuentas registradas.");
            } else {
                resultado.append(String.format("%-25s %-15s %-12s %-15s %-12s\n", 
                    "CCC", "Tipo", "Saldo Actual", "Saldo Medio", "Sucursal"));
                resultado.append("--------------------------------------------------------------------------------\n");
                for (Cuenta c : cuentas) {
                    resultado.append(String.format("%-25s %-15s $%-11.2f $%-11.2f %-12s\n",
                        c.getCcc().toString(),
                        c.getTipo(),
                        c.getSaldoActual(),
                        c.getSaldoMedio(),
                        c.getSucursal().getNumero()));
                }
                resultado.append("\nTotal de cuentas: ").append(cuentas.size());
            }

            txtResultadoCuentas.setText(resultado.toString());

        } catch (Exception e) {
            txtResultadoCuentas.setText("❌ ERROR EN REPORTE:\n\n" + e.getMessage());
        }
    }

    private void generarReporteTodasLasCuentas() {
        try {
            List<Cuenta> cuentas = reporteCuentasUseCase.generarReporteTodasLasCuentas();

            StringBuilder resultado = new StringBuilder();
            resultado.append("📊 REPORTE GENERAL DE TODAS LAS CUENTAS\n\n");

            if (cuentas.isEmpty()) {
                resultado.append("No hay cuentas registradas en el sistema.");
            } else {
                resultado.append(String.format("%-25s %-20s %-15s %-12s %-15s %-12s\n", 
                    "CCC", "Cliente", "Tipo", "Saldo Actual", "Saldo Medio", "Sucursal"));
                resultado.append("----------------------------------------------------------------------------------------------------\n");
                for (Cuenta c : cuentas) {
                    resultado.append(String.format("%-25s %-20s %-15s $%-11.2f $%-11.2f %-12s\n",
                        c.getCcc().toString(),
                        c.getCliente().getNombre(),
                        c.getTipo(),
                        c.getSaldoActual(),
                        c.getSaldoMedio(),
                        c.getSucursal().getNumero()));
                }
                resultado.append("\nTotal de cuentas: ").append(cuentas.size());
            }

            txtResultadoCuentas.setText(resultado.toString());

        } catch (Exception e) {
            txtResultadoCuentas.setText("❌ ERROR EN REPORTE:\n\n" + e.getMessage());
        }
    }

    private void generarReporteTransacciones() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaInicio = LocalDateTime.parse(txtFechaInicioReporte.getText().trim(), formatter);
            LocalDateTime fechaFin = LocalDateTime.parse(txtFechaFinReporte.getText().trim(), formatter);

            List<Transaccion> transacciones = reporteTransaccionesUseCase.generarReporteTransaccionesPorFechas(fechaInicio, fechaFin);

            StringBuilder resultado = new StringBuilder();
            resultado.append("📈 REPORTE DE TRANSACCIONES POR FECHAS\n\n");
            resultado.append("Período: ").append(fechaInicio).append(" hasta ").append(fechaFin).append("\n\n");

            if (transacciones.isEmpty()) {
                resultado.append("No se encontraron transacciones en el período seleccionado.");
            } else {
                resultado.append(String.format("%-5s %-20s %-25s %-25s %-10s %-30s\n", 
                    "ID", "Fecha", "Cuenta Origen", "Cuenta Destino", "Monto", "Descripción"));
                resultado.append("--------------------------------------------------------------------------------------------------------------------\n");
                
                BigDecimal totalMonto = BigDecimal.ZERO;
                for (Transaccion t : transacciones) {
                    resultado.append(String.format("%-5d %-20s %-25s %-25s $%-9.2f %-30s\n",
                        t.getId(),
                        t.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        t.getCuentaOrigen().toString(),
                        t.getCuentaDestino().toString(),
                        t.getMonto(),
                        t.getDescripcion()));
                    totalMonto = totalMonto.add(t.getMonto());
                }
                resultado.append("\nTotal de transacciones: ").append(transacciones.size());
                resultado.append("\nMonto total movido: $").append(totalMonto);
            }

            txtResultadoTransacciones.setText(resultado.toString());

        } catch (Exception e) {
            txtResultadoTransacciones.setText("❌ ERROR EN REPORTE:\n\n" + e.getMessage());
        }
    }

    private void generarReporteSaldos() {
        try {
            Map<String, BigDecimal> reporte = reporteSucursalUseCase.generarReporteSaldosPorSucursal();

            StringBuilder resultado = new StringBuilder();
            resultado.append("🏦 REPORTE DE SALDOS POR SUCURSAL\n\n");

            if (reporte.isEmpty()) {
                resultado.append("No hay datos disponibles para generar el reporte.");
            } else {
                resultado.append(String.format("%-15s %-15s\n", "Sucursal", "Saldo Total"));
                resultado.append("---------------------------\n");
                
                BigDecimal saldoTotalGeneral = BigDecimal.ZERO;
                for (Map.Entry<String, BigDecimal> entry : reporte.entrySet()) {
                    resultado.append(String.format("%-15s $%-14.2f\n",
                        entry.getKey(),
                        entry.getValue()));
                    saldoTotalGeneral = saldoTotalGeneral.add(entry.getValue());
                }
                resultado.append("\nSaldo total general: $").append(saldoTotalGeneral);
            }

            txtResultadoSucursal.setText(resultado.toString());

        } catch (Exception e) {
            txtResultadoSucursal.setText("❌ ERROR EN REPORTE:\n\n" + e.getMessage());
        }
    }

    private void generarReporteCantidadCuentas() {
        try {
            Map<String, Integer> reporte = reporteSucursalUseCase.generarReporteCantidadCuentasPorSucursal();

            StringBuilder resultado = new StringBuilder();
            resultado.append("🏦 REPORTE DE CANTIDAD DE CUENTAS POR SUCURSAL\n\n");

            if (reporte.isEmpty()) {
                resultado.append("No hay datos disponibles para generar el reporte.");
            } else {
                resultado.append(String.format("%-15s %-15s\n", "Sucursal", "Cantidad Cuentas"));
                resultado.append("---------------------------\n");
                
                int totalCuentas = 0;
                for (Map.Entry<String, Integer> entry : reporte.entrySet()) {
                    resultado.append(String.format("%-15s %-15d\n",
                        entry.getKey(),
                        entry.getValue()));
                    totalCuentas += entry.getValue();
                }
                resultado.append("\nTotal de cuentas en el sistema: ").append(totalCuentas);
            }

            txtResultadoSucursal.setText(resultado.toString());

        } catch (Exception e) {
            txtResultadoSucursal.setText("❌ ERROR EN REPORTE:\n\n" + e.getMessage());
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}