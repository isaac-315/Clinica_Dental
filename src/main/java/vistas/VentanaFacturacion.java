/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.ClienteDAO;
import modelos.FacturaCabecera;
import modelos.FacturaCabeceraDAO;
import modelos.Usuario;
import modelos.UsuarioDAO;

/**
 *
 * @author USUARO_PC
 */
public class VentanaFacturacion extends javax.swing.JFrame {

    private VentanaPrincipal ventanaPrincipal;

    public VentanaFacturacion(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        initComponents();

        // 👇 SOBREESCRIBIR EL MODELO GENERADO POR NETBEANS
        jTableFacturacion.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Fecha de emision", "Subtotal", "IVA", "Total", "Cliente", "Usuario", "Estado"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        configurarTabla();
        configurarAnchoColumnas();
        configurarCierre();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // En VentanaFacturacion.java, agrega este método:
    public void refrescarTabla() {
        configurarTabla();
        configurarAnchoColumnas();
    }

    private void configurarTabla() {
        try {
            FacturaCabeceraDAO facturaDAO = new FacturaCabeceraDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            List<FacturaCabecera> facturas = facturaDAO.listarTodos();
            DefaultTableModel modelo = (DefaultTableModel) jTableFacturacion.getModel();
            modelo.setRowCount(0); // Limpiar filas existentes

            // Formato de fecha (solo fecha, sin hora)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (FacturaCabecera fac : facturas) {
                // Obtener cliente
                Cliente cliente = clienteDAO.obtenerPorId(fac.getCliId());
                String nombreCliente = (cliente != null)
                        ? cliente.getCliNombre() + " " + cliente.getCliApellido()
                        : "Cliente no encontrado";

                // Obtener usuario
                Usuario usuario = usuarioDAO.obtenerPorId(fac.getUsuId());
                String nombreUsuario = (usuario != null)
                        ? usuario.getUsuUsuario()
                        : "Usuario no encontrado";

                // Formatear fecha
                String fecha = (fac.getFacFechaEmision() != null)
                        ? sdf.format(fac.getFacFechaEmision())
                        : "Sin fecha";

                String estado = "Activo";

                Object[] fila = {
                    fac.getFacId(),
                    fecha,
                    String.format("%.2f", fac.getFacSubtotal()),
                    String.format("%.2f", fac.getFacIva()),
                    String.format("%.2f", fac.getFacTotal()),
                    nombreCliente,
                    nombreUsuario,
                    estado
                };

                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar las facturas desde la base de datos:\n" + e.getMessage(),
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void configurarAnchoColumnas() {
        jTableFacturacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        // Ajustar según el contenido real de la tabla de FACTURAS
        jTableFacturacion.getColumnModel().getColumn(0).setPreferredWidth(60);   // ID
        jTableFacturacion.getColumnModel().getColumn(1).setPreferredWidth(130);  // Fecha de emision
        jTableFacturacion.getColumnModel().getColumn(2).setPreferredWidth(90);   // Subtotal
        jTableFacturacion.getColumnModel().getColumn(3).setPreferredWidth(70);   // IVA
        jTableFacturacion.getColumnModel().getColumn(4).setPreferredWidth(90);   // Total
        jTableFacturacion.getColumnModel().getColumn(5).setPreferredWidth(160);  // Cliente
        jTableFacturacion.getColumnModel().getColumn(6).setPreferredWidth(120);  // Usuario
        jTableFacturacion.getColumnModel().getColumn(7).setPreferredWidth(90);   // Estado
    }

    private void configurarCierre() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelClientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFacturacion = new javax.swing.JTable();
        jButtonRestablecer = new javax.swing.JButton();
        jButtonBuscarFactura = new javax.swing.JButton();
        jButtonCrearFacturas = new javax.swing.JButton();
        jButtonVerFacturas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelClientes.setBackground(new java.awt.Color(51, 153, 255));

        jTableFacturacion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jTableFacturacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableFacturacion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTableFacturacion);

        jButtonRestablecer.setText("Restablecer");
        jButtonRestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestablecerActionPerformed(evt);
            }
        });

        jButtonBuscarFactura.setText("Buscar");
        jButtonBuscarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarFacturaActionPerformed(evt);
            }
        });

        jButtonCrearFacturas.setText("Crear factura");
        jButtonCrearFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearFacturasActionPerformed(evt);
            }
        });

        jButtonVerFacturas.setText("Ver factura");
        jButtonVerFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerFacturasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
        jPanelClientes.setLayout(jPanelClientesLayout);
        jPanelClientesLayout.setHorizontalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonBuscarFactura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonRestablecer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonCrearFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVerFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanelClientesLayout.setVerticalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButtonRestablecer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBuscarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCrearFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonVerFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestablecerActionPerformed
        configurarTabla();
        configurarAnchoColumnas();
    }//GEN-LAST:event_jButtonRestablecerActionPerformed

    private void jButtonBuscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarFacturaActionPerformed
        String criterio = javax.swing.JOptionPane.showInputDialog(
                this,
                "Ingrese el número de la factura, nombre del cliente o nombre del usuario:",
                "Buscar Factura",
                javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        // Si el usuario cancela o no ingresa nada, salir
        if (criterio == null || criterio.trim().isEmpty()) {
            return;
        }

        try {
            FacturaCabeceraDAO facturaDAO = new FacturaCabeceraDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Buscar facturas que coincidan con el criterio
            List<FacturaCabecera> facturas = facturaDAO.buscarPorCriterio(criterio.trim());

            // Configurar el modelo de la tabla
            DefaultTableModel modelo = (DefaultTableModel) jTableFacturacion.getModel();
            modelo.setRowCount(0); // Limpiar tabla

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (FacturaCabecera fac : facturas) {
                // Obtener cliente
                Cliente cliente = clienteDAO.obtenerPorId(fac.getCliId());
                String nombreCliente = (cliente != null)
                        ? cliente.getCliNombre() + " " + cliente.getCliApellido()
                        : "Cliente no encontrado";

                // Obtener usuario
                Usuario usuario = usuarioDAO.obtenerPorId(fac.getUsuId());
                String nombreUsuario = (usuario != null)
                        ? usuario.getUsuUsuario()
                        : "Usuario no encontrado";

                // Formatear fecha
                String fecha = (fac.getFacFechaEmision() != null)
                        ? sdf.format(fac.getFacFechaEmision())
                        : "Sin fecha";

                String estado = "Activo";

                Object[] fila = {
                    fac.getFacId(),
                    fecha,
                    String.format("%.2f", fac.getFacSubtotal()),
                    String.format("%.2f", fac.getFacIva()),
                    String.format("%.2f", fac.getFacTotal()),
                    nombreCliente,
                    nombreUsuario,
                    estado
                };

                modelo.addRow(fila);
            }

            // Mensaje si no se encontraron resultados
            if (facturas.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron facturas con el criterio: \"" + criterio + "\"",
                        "Búsqueda sin resultados",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar facturas: " + e.getMessage(),
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonBuscarFacturaActionPerformed

    private void jButtonCrearFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearFacturasActionPerformed
        CreacionFacturas creacion = new CreacionFacturas(this); // ← pasa "this"
        creacion.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCrearFacturasActionPerformed

    private void jButtonVerFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFacturasActionPerformed
        int filaSeleccionada = jTableFacturacion.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una factura de la tabla para visualizar.",
                    "Ninguna selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Object idObj = jTableFacturacion.getValueAt(filaSeleccionada, 0);
            if (idObj == null) {
                JOptionPane.showMessageDialog(this, "Factura seleccionada no válida.");
                return;
            }

            String facId = idObj.toString();

            // 👇 ABRIR EN MODO VISTA
            CreacionFacturas visualizacion = new CreacionFacturas(this, facId);
            visualizacion.setVisible(true);
            this.setVisible(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir la factura: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonVerFacturasActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarFactura;
    private javax.swing.JButton jButtonCrearFacturas;
    private javax.swing.JButton jButtonRestablecer;
    private javax.swing.JButton jButtonVerFacturas;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFacturacion;
    // End of variables declaration//GEN-END:variables
}
