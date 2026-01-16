/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.ClienteControl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.ClienteDAO;

/**
 *
 * @author USUARO_PC
 */
public class VentanaClientes extends javax.swing.JFrame {

    private VentanaPrincipal ventanaPrincipal;

    public VentanaClientes(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        initComponents();
        configurarTabla();
        configurarAnchoColumnas();
        configurarCierre();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void configurarTabla() {
        // Definir columnas
        String[] columnas = {
            "ID", "Cédula", "Nombre", "Apellido", "Dirección",
            "Teléfono Convencional", "Teléfono Celular", "Correo Electrónico", "Estado"
        };

        // Crear modelo vacío
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                null, columnas
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Cargar datos desde la base de datos
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            List<Cliente> clientes = clienteDAO.listarTodos();

            for (Cliente c : clientes) {
                Object[] fila = {
                    c.getCliId(),
                    c.getCliCedula(),
                    c.getCliNombre(),
                    c.getCliApellido(),
                    c.getCliDireccion(),
                    c.getCliTelefonoConvencional(),
                    c.getCliTelefonoCelular(),
                    c.getCliCorreoElectronico(),
                    c.getEstadoTexto() // "Activo" o "Inactivo"
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los clientes desde la base de datos:\n" + e.getMessage(),
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        jTableClientes.setModel(modelo);
        configurarAnchoColumnas();
    }

    private void configurarAnchoColumnas() {
        jTableClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(120);  // Cédula
        jTableClientes.getColumnModel().getColumn(2).setPreferredWidth(120);  // Nombre
        jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(120);  // Apellido
        jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(200);  // Dirección
        jTableClientes.getColumnModel().getColumn(5).setPreferredWidth(150);  // Tel Conv.
        jTableClientes.getColumnModel().getColumn(6).setPreferredWidth(150);  // Celular
        jTableClientes.getColumnModel().getColumn(7).setPreferredWidth(200);  // Correo
        jTableClientes.getColumnModel().getColumn(8).setPreferredWidth(80);   // Estado
    }

    public void refrescarTabla() {
        configurarTabla();
    }

    private void actualizarTablaConClientes(List<Cliente> clientes) {
        String[] columnas = {
            "ID", "Cédula", "Nombre", "Apellido", "Dirección",
            "Teléfono Convencional", "Teléfono Celular", "Correo Electrónico", "Estado"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Cliente c : clientes) {
            Object[] fila = {
                c.getCliId(),
                c.getCliCedula(),
                c.getCliNombre(),
                c.getCliApellido(),
                c.getCliDireccion(),
                c.getCliTelefonoConvencional(),
                c.getCliTelefonoCelular(),
                c.getCliCorreoElectronico(),
                c.getEstadoTexto()
            };
            modelo.addRow(fila);
        }

        jTableClientes.setModel(modelo);
        configurarAnchoColumnas();
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
        jTableClientes = new javax.swing.JTable();
        jButtonRestablecer = new javax.swing.JButton();
        jButtonBuscarCliente = new javax.swing.JButton();
        jButtonInsertarCliente = new javax.swing.JButton();
        jButtonEliminarCliente = new javax.swing.JButton();
        jButtonEditarCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelClientes.setBackground(new java.awt.Color(51, 153, 255));

        jTableClientes.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTableClientes);

        jButtonRestablecer.setText("Restablecer");
        jButtonRestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestablecerActionPerformed(evt);
            }
        });

        jButtonBuscarCliente.setText("Buscar");
        jButtonBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarClienteActionPerformed(evt);
            }
        });

        jButtonInsertarCliente.setText("Insertar");
        jButtonInsertarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarClienteActionPerformed(evt);
            }
        });

        jButtonEliminarCliente.setText("Eliminar");
        jButtonEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarClienteActionPerformed(evt);
            }
        });

        jButtonEditarCliente.setText("Editar");
        jButtonEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
        jPanelClientes.setLayout(jPanelClientesLayout);
        jPanelClientesLayout.setHorizontalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelClientesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonBuscarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRestablecer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInsertarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        jPanelClientesLayout.setVerticalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButtonRestablecer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonInsertarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jButtonEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void jButtonBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarClienteActionPerformed
        String criterio = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre, apellido o cédula del cliente a buscar:",
                "Buscar Cliente",
                JOptionPane.QUESTION_MESSAGE
        );

        if (criterio == null || criterio.trim().isEmpty()) {
            return; // Canceló o dejó vacío
        }

        try {
            // Cargar todos los clientes una sola vez
            ClienteControl clienteControl = new ClienteControl();
            List<Cliente> todosClientes = clienteControl.listarTodos();
            List<Cliente> coincidencias = new ArrayList<>();

            String busqueda = criterio.trim().toLowerCase();

            for (Cliente c : todosClientes) {
                if (c.getCliCedula().toLowerCase().contains(busqueda)
                        || c.getCliNombre().toLowerCase().contains(busqueda)
                        || c.getCliApellido().toLowerCase().contains(busqueda)) {
                    coincidencias.add(c);
                }
            }

            if (coincidencias.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron clientes con el criterio: \"" + criterio.trim() + "\"",
                        "Sin resultados",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Actualizar la tabla SOLO con los resultados
                actualizarTablaConClientes(coincidencias);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar clientes:\n" + e.getMessage(),
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButtonBuscarClienteActionPerformed

    private void jButtonInsertarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarClienteActionPerformed
        FormularioRegistroCliente formulario = new FormularioRegistroCliente(this); // ← pasa "this"
        formulario.setVisible(true);
    }//GEN-LAST:event_jButtonInsertarClienteActionPerformed

    private void jButtonEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarClienteActionPerformed
        int filaSeleccionada = jTableClientes.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, seleccione una fila de la tabla para eliminar.",
                    "Ninguna selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el ID del cliente desde la primera columna (asumiendo que es cli_id)
        int idCliente = (int) jTableClientes.getValueAt(filaSeleccionada, 0);

        // Confirmación
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar al cliente con ID " + idCliente + "?\n"
                + "Esta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                ClienteControl control = new ClienteControl();
                // Aquí asumimos que tienes un método 'eliminar(int id)' en ClienteControl
                // Si no, lo creamos más abajo.
                control.eliminar(idCliente);

                JOptionPane.showMessageDialog(this,
                        "Cliente eliminado exitosamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Actualizar la tabla
                configurarTabla();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Error al eliminar el cliente:\n" + e.getMessage(),
                        "Error de base de datos",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonEliminarClienteActionPerformed

    private void jButtonEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarClienteActionPerformed
        int filaSeleccionada = jTableClientes.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, seleccione una fila de la tabla para editar.",
                    "Ninguna selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el ID desde la primera columna (asumiendo que es cli_id)
        int idCliente = (int) jTableClientes.getValueAt(filaSeleccionada, 0);
        String cedula = (String) jTableClientes.getValueAt(filaSeleccionada, 1);
        String nombre = (String) jTableClientes.getValueAt(filaSeleccionada, 2);
        String apellido = (String) jTableClientes.getValueAt(filaSeleccionada, 3);
        String direccion = (String) jTableClientes.getValueAt(filaSeleccionada, 4);
        String telConv = (String) jTableClientes.getValueAt(filaSeleccionada, 5);
        String telCel = (String) jTableClientes.getValueAt(filaSeleccionada, 6);
        String correo = (String) jTableClientes.getValueAt(filaSeleccionada, 7);
        String estadoTexto = (String) jTableClientes.getValueAt(filaSeleccionada, 8);

        // Convertir estado a char
        char estadoChar = estadoTexto.equalsIgnoreCase("Activo") ? 'A' : 'I';

        // Crear objeto Cliente con los datos reales
        Cliente clienteSeleccionado = new Cliente(
                idCliente, cedula, nombre, apellido, direccion,
                telConv, telCel, correo, estadoChar
        );

        // Abrir el formulario EN MODO EDICIÓN
        FormularioRegistroCliente formulario = new FormularioRegistroCliente(this, clienteSeleccionado);
        formulario.setVisible(true);
    }//GEN-LAST:event_jButtonEditarClienteActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarCliente;
    private javax.swing.JButton jButtonEditarCliente;
    private javax.swing.JButton jButtonEliminarCliente;
    private javax.swing.JButton jButtonInsertarCliente;
    private javax.swing.JButton jButtonRestablecer;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables
}
