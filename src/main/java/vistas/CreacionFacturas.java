/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  // ✅ Agregado
import java.sql.SQLException;
import java.sql.Statement;  // ✅ Agregado
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.ConexionDB;

/**
 *
 * @author USUARO_PC
 */
public class CreacionFacturas extends javax.swing.JFrame {

    private VentanaFacturacion ventanaAnterior;
    private Integer idClienteSeleccionado = null;
    private Integer idServicioSeleccionado = null;

    /**
     * Creates new form CreacionFacturas
     */
    public CreacionFacturas(VentanaFacturacion ventanaAnterior) {
        this.ventanaAnterior = ventanaAnterior;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);

        // Inicializar fecha y número de factura
        lblFechaActual.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        lblNumeroFactura.setText(generarNumeroFactura());

        // ✅ Reemplazar el modelo generado por NetBeans
        DefaultTableModel modeloDetalles = new DefaultTableModel(
                new String[]{"ID Servicio", "Servicio", "Cantidad", "Precio Unitario", "Subtotal", "IVA", "Total"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaDetalles.setModel(modeloDetalles);

        // 👇 CONFIGURAR CIERRE PARA VOLVER A LA VENTANA ANTERIOR
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (ventanaAnterior != null) {
                    ventanaAnterior.setVisible(true);
                }
            }
        });
    }

    private String generarNumeroFactura() {
        try {
            Connection conn = ConexionDB.getConnection();
            String sql = "SELECT NVL(MAX(TO_NUMBER(SUBSTR(fac_id, 2))), 0) + 1 FROM DEN_FACTURAS_CABECERAS";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int siguienteNumero = rs.getInt(1);
                conn.close();
                return "F" + String.format("%04d", siguienteNumero);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "F0001";
    }

    private void recalcularTotales() {
        double subtotal = 0;
        double ivaTotal = 0;

        DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            double precioUnit = Double.parseDouble(modelo.getValueAt(i, 3).toString());
            int cantidad = Integer.parseInt(modelo.getValueAt(i, 2).toString());
            double subtotalDetalle = precioUnit * cantidad;
            double ivaDetalle = subtotalDetalle * 0.12; // IVA 12% Ecuador

            modelo.setValueAt(String.format("%.2f", subtotalDetalle), i, 4);
            modelo.setValueAt(String.format("%.2f", ivaDetalle), i, 5);
            modelo.setValueAt(String.format("%.2f", subtotalDetalle + ivaDetalle), i, 6);

            subtotal += subtotalDetalle;
            ivaTotal += ivaDetalle;
        }

        double total = subtotal + ivaTotal;

        lblSubtotal.setText(String.format("$ %.2f", subtotal));
        lblIva.setText(String.format("$ %.2f", ivaTotal));
        lblTotal.setText(String.format("$ %.2f", total));
    }

    private double obtenerSubtotal() {
        double subtotal = 0;
        DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            subtotal += Double.parseDouble(modelo.getValueAt(i, 4).toString());
        }
        return subtotal;
    }

    private double obtenerIvaTotal() {
        double iva = 0;
        DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            iva += Double.parseDouble(modelo.getValueAt(i, 5).toString());
        }
        return iva;
    }

    private double obtenerTotal() {
        return obtenerSubtotal() + obtenerIvaTotal();
    }

    private boolean validarFactura() {
        if (idClienteSeleccionado == null || idClienteSeleccionado <= 0) {
            JOptionPane.showMessageDialog(this, "❌ Debe seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (tablaDetalles.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "❌ Debe agregar al menos un servicio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int cantidad = Integer.parseInt(modelo.getValueAt(i, 2).toString());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "❌ La cantidad del servicio '"
                        + modelo.getValueAt(i, 1) + "' debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    private void registrarFactura() {
        if (!validarFactura()) {
            return;
        }

        Connection conn = null;
        try {
            conn = ConexionDB.getConnection();
            conn.setAutoCommit(false);

            String numeroFactura = lblNumeroFactura.getText();

            // Guardar encabezado
            String sqlEncabezado = "INSERT INTO DEN_FACTURAS_CABECERAS (fac_id, fac_fecha_emision, cli_id, fac_subtotal, fac_iva, fac_total) "
                    + "VALUES (?, SYSDATE, ?, ?, ?, ?)";
            PreparedStatement psEnc = conn.prepareStatement(sqlEncabezado);
            psEnc.setString(1, numeroFactura);
            psEnc.setInt(2, idClienteSeleccionado);
            psEnc.setDouble(3, obtenerSubtotal());
            psEnc.setDouble(4, obtenerIvaTotal());
            psEnc.setDouble(5, obtenerTotal());
            psEnc.executeUpdate();

            // Guardar detalles
            String sqlDetalle = "INSERT INTO DEN_FACTURAS_DETALLES (det_id, det_cantidad, det_subtotal, det_iva, det_total, fac_id, ser_id) "
                    + "VALUES (seq_det.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psDet = conn.prepareStatement(sqlDetalle);

            DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                psDet.setInt(1, Integer.parseInt(modelo.getValueAt(i, 2).toString())); // cantidad
                psDet.setDouble(2, Double.parseDouble(modelo.getValueAt(i, 4).toString())); // subtotal
                psDet.setDouble(3, Double.parseDouble(modelo.getValueAt(i, 5).toString())); // iva
                psDet.setDouble(4, Double.parseDouble(modelo.getValueAt(i, 6).toString())); // total
                psDet.setString(5, numeroFactura); // fac_id
                psDet.setInt(6, (Integer) modelo.getValueAt(i, 0)); // ser_id
                psDet.addBatch();
            }
            psDet.executeBatch();

            conn.commit();

            JOptionPane.showMessageDialog(this, "✅ Factura registrada correctamente\nN°: " + numeroFactura,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // 👇 OCULTAR ESTA VENTANA Y VOLVER A LA ANTERIOR
            this.dispose(); // Esto activará el windowClosed y mostrará ventanaAnterior

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(this, "❌ Error al guardar factura: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void limpiarFormulario() {
        // Implementa la lógica para limpiar el formulario
        idClienteSeleccionado = null;
        lblNombreCliente.setText("");
        // Limpiar tabla
        DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();
        modelo.setRowCount(0);
        // Resetear totales
        lblSubtotal.setText("$ 0.00");
        lblIva.setText("$ 0.00");
        lblTotal.setText("$ 0.00");
        // Nuevo número de factura
        lblNumeroFactura.setText(generarNumeroFactura());
    }

    // En CreacionFacturas.java, agrega estos métodos:
    public void setClienteSeleccionado(int cliId, String nombreCompleto) {
        this.idClienteSeleccionado = cliId;
        this.lblNombreCliente.setText(nombreCompleto);
    }

    // En CreacionFacturas.java
    public void agregarServicioSeleccionado(int serId, String nombreServicio, int cantidad, double precioUnitario) {
        DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();

        // Verificar si el servicio ya existe en la tabla
        boolean existe = false;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object idObj = modelo.getValueAt(i, 0);
            if (idObj != null) {
                int idExistente = (idObj instanceof Integer) ? (Integer) idObj : Integer.parseInt(idObj.toString());
                if (idExistente == serId) {
                    // Actualizar cantidad
                    int cantidadActual = (Integer) modelo.getValueAt(i, 2);
                    modelo.setValueAt(cantidadActual + cantidad, i, 2);
                    existe = true;
                    break;
                }
            }
        }

        if (!existe) {
            // Agregar nueva fila - Asegúrate de que todos los valores sean del tipo correcto
            Object[] fila = {
                Integer.valueOf(serId), // Integer
                String.valueOf(nombreServicio), // String
                Integer.valueOf(cantidad), // Integer
                Double.valueOf(precioUnitario), // Double
                Double.valueOf(0.0), // Double
                Double.valueOf(0.0), // Double
                Double.valueOf(0.0) // Double
            };
            modelo.addRow(fila);
        }

        // Recalcular totales
        recalcularTotales();

        // 👇 Forzar actualización de la tabla
        tablaDetalles.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFechaActual = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblNumeroFactura = new javax.swing.JLabel();
        btnAnadirServicio = new javax.swing.JButton();
        btnSeleccionarCliente = new javax.swing.JButton();
        lblNombreServicio = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnRegistrarVenta = new javax.swing.JButton();
        btnQuitarServicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("CREACIÓN DE FACTURA");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Servicios:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Encabezado");

        lblFechaActual.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel9.setText("Fecha:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel10.setText("Nro. de Factura:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel11.setText("Clientes: ");

        lblNumeroFactura.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        btnAnadirServicio.setBackground(new java.awt.Color(31, 96, 178));
        btnAnadirServicio.setForeground(new java.awt.Color(255, 255, 255));
        btnAnadirServicio.setText("Seleccionar Servicio");
        btnAnadirServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirServicioActionPerformed(evt);
            }
        });

        btnSeleccionarCliente.setBackground(new java.awt.Color(31, 96, 178));
        btnSeleccionarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionarCliente.setText("Seleccionar Cliente");
        btnSeleccionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lblNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnadirServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeleccionarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(lblFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(231, 231, 231))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumeroFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(btnSeleccionarCliente))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(btnAnadirServicio))
                            .addComponent(lblNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Servicio", "Cantidad", "Precio Unitario", "Subtotal", "IVA", "Total"
            }
        ));
        jScrollPane2.setViewportView(tablaDetalles);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel1.setText("Subtotal: ");

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel12.setText("Total con IVA: ");

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel13.setText("Total:");

        lblIva.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        lblSubtotal.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        lblTotal.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        btnRegistrarVenta.setBackground(new java.awt.Color(51, 51, 255));
        btnRegistrarVenta.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        btnRegistrarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarVenta.setText("Registrar venta");
        btnRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblIva, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        btnQuitarServicio.setBackground(new java.awt.Color(31, 96, 178));
        btnQuitarServicio.setForeground(new java.awt.Color(255, 255, 255));
        btnQuitarServicio.setText("Quitar Registro");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuitarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQuitarServicio)
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(346, 346, 346))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 125, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        registrarFactura();
    }//GEN-LAST:event_btnRegistrarVentaActionPerformed

    private void btnSeleccionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarClienteActionPerformed
        SeleccionCliente seleccionCliente = new SeleccionCliente(this);
        seleccionCliente.setModal(true);
        seleccionCliente.setVisible(true);
    }//GEN-LAST:event_btnSeleccionarClienteActionPerformed

    private void btnAnadirServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirServicioActionPerformed
        SeleccionServicio seleccionServicio = new SeleccionServicio(this);
        seleccionServicio.setModal(true);
        seleccionServicio.setVisible(true);
    }//GEN-LAST:event_btnAnadirServicioActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadirServicio;
    private javax.swing.JButton btnQuitarServicio;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JButton btnSeleccionarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFechaActual;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNombreServicio;
    private javax.swing.JLabel lblNumeroFactura;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaDetalles;
    // End of variables declaration//GEN-END:variables
}
