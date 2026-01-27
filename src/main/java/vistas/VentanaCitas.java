/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.CitaControl;
import controladores.ClienteControl;
import controladores.EmpleadoControl;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.CitaDAO;
import modelos.ClienteDAO;
import modelos.Empleado;
import modelos.EmpleadoDAO;
import modelos.Cita;
import modelos.Cliente;

/**
 *
 * @author USUARO_PC
 */
public class VentanaCitas extends javax.swing.JFrame {

    private VentanaPrincipal ventanaPrincipal;

    public VentanaCitas(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        initComponents();
        configurarTabla();
        configurarAnchoColumnas();
        configurarCierre();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void refrescarTabla() {
        configurarTabla();
        configurarAnchoColumnas();
    }

    private void configurarTabla() {
        String[] columnas = {
            "ID",
            "Fecha de cita",
            "Estado",
            "Cliente",
            "Empleado asignado"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            // 👇 USAR CONTROLADORES (no DAOs directos)
            CitaControl citaControl = new CitaControl();
            ClienteControl clienteControl = new ClienteControl();
            EmpleadoControl empleadoControl = new EmpleadoControl();

            List<Cita> citas = citaControl.listarTodos();

            for (Cita cita : citas) {
                // Obtener cliente
                Cliente cliente = clienteControl.obtenerPorId(cita.getCliId());
                String nombreCliente = (cliente != null)
                        ? cliente.getCliNombre() + " " + cliente.getCliApellido()
                        : "Cliente no encontrado";

                // Obtener empleado
                Empleado empleado = empleadoControl.obtenerPorId(cita.getEmpId());
                String nombreEmpleado = (empleado != null)
                        ? empleado.getEmpNombre() + " " + empleado.getEmpApellido()
                        : "Empleado no encontrado";

                // Formatear fecha
                String fecha = (cita.getCitaFechaHora() != null)
                        ? new SimpleDateFormat("yyyy-MM-dd - HH:mm").format(cita.getCitaFechaHora())
                        : "Sin fecha";

                Object[] fila = {
                    cita.getCitaId(),
                    fecha,
                    cita.getEstadoTexto(),
                    nombreCliente,
                    nombreEmpleado
                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar las citas:\n" + e.getMessage(),
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        jTableCitas.setModel(modelo);
    }

    private void configurarAnchoColumnas() {
        jTableCitas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        jTableCitas.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        jTableCitas.getColumnModel().getColumn(1).setPreferredWidth(180);  // Fecha de cita
        jTableCitas.getColumnModel().getColumn(2).setPreferredWidth(100);  // Estado
        jTableCitas.getColumnModel().getColumn(3).setPreferredWidth(180);  // Cliente
        jTableCitas.getColumnModel().getColumn(4).setPreferredWidth(180);  // Empleado asignado
    }

    private void mostrarCitasEnTabla(List<Cita> citas) {
        String[] columnas = {
            "ID", "Fecha de cita", "Estado", "Cliente", "Empleado asignado"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            ClienteControl clienteControl = new ClienteControl();
            EmpleadoControl empleadoControl = new EmpleadoControl();

            for (Cita cita : citas) {
                // Obtener cliente
                Cliente cliente = clienteControl.obtenerPorId(cita.getCliId());
                String nombreCliente = (cliente != null)
                        ? cliente.getCliNombre() + " " + cliente.getCliApellido()
                        : "Cliente no encontrado";

                // Obtener empleado
                Empleado empleado = empleadoControl.obtenerPorId(cita.getEmpId());
                String nombreEmpleado = (empleado != null)
                        ? empleado.getEmpNombre() + " " + empleado.getEmpApellido()
                        : "Empleado no encontrado";

                // Formatear fecha
                String fecha = (cita.getCitaFechaHora() != null)
                        ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cita.getCitaFechaHora())
                        : "Sin fecha";

                Object[] fila = {
                    cita.getCitaId(),
                    fecha,
                    cita.getEstadoTexto(),
                    nombreCliente,
                    nombreEmpleado
                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos relacionados:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        jTableCitas.setModel(modelo);
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
        jTableCitas = new javax.swing.JTable();
        jButtonRestablecer = new javax.swing.JButton();
        jButtonBuscarCita = new javax.swing.JButton();
        jButtonAgendarCita = new javax.swing.JButton();
        jButtonCancelarCita = new javax.swing.JButton();
        jButtonEditarCita = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelClientes.setBackground(new java.awt.Color(51, 153, 255));

        jTableCitas.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jTableCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCitas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTableCitas);

        jButtonRestablecer.setText("Restablecer");
        jButtonRestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestablecerActionPerformed(evt);
            }
        });

        jButtonBuscarCita.setText("Buscar");
        jButtonBuscarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarCitaActionPerformed(evt);
            }
        });

        jButtonAgendarCita.setText("Agendar cita");
        jButtonAgendarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgendarCitaActionPerformed(evt);
            }
        });

        jButtonCancelarCita.setText("Cancelar Cita");
        jButtonCancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarCitaActionPerformed(evt);
            }
        });

        jButtonEditarCita.setText("Editar");
        jButtonEditarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarCitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
        jPanelClientes.setLayout(jPanelClientesLayout);
        jPanelClientesLayout.setHorizontalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonBuscarCita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRestablecer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAgendarCita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelarCita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditarCita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanelClientesLayout.setVerticalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButtonRestablecer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEditarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestablecerActionPerformed
        configurarTabla();
        configurarAnchoColumnas();
    }//GEN-LAST:event_jButtonRestablecerActionPerformed

    private void jButtonBuscarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarCitaActionPerformed
        String criterio = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre, apellido o ID del cliente a buscar:",
                "Buscar Cita",
                JOptionPane.QUESTION_MESSAGE
        );

        if (criterio == null || criterio.trim().isEmpty()) {
            return; // Canceló o dejó vacío
        }

        try {
            CitaControl citaControl = new CitaControl();
            ClienteControl clienteControl = new ClienteControl();

            List<Cita> todasCitas = citaControl.listarTodos();
            List<Cita> resultados = new ArrayList<>();

            String busqueda = criterio.trim().toLowerCase();

            for (Cita cita : todasCitas) {
                // Opción 1: si ya tienes nombre/apellido en Cita
                // (no es tu caso, así que usamos Opción 2)

                // Opción 2: obtener cliente por cliId
                Cliente cliente = clienteControl.obtenerPorId(cita.getCliId());
                if (cliente != null) {
                    boolean coincide = false;

                    // Buscar por ID (si el criterio es numérico)
                    if (busqueda.matches("\\d+")) {
                        int idBuscado = Integer.parseInt(busqueda);
                        if (cliente.getCliId() == idBuscado) {
                            coincide = true;
                        }
                    }

                    // Buscar por nombre o apellido
                    if (!coincide) {
                        if (cliente.getCliNombre().toLowerCase().contains(busqueda)
                                || cliente.getCliApellido().toLowerCase().contains(busqueda)) {
                            coincide = true;
                        }
                    }

                    if (coincide) {
                        resultados.add(cita);
                    }
                }
            }

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron citas para el cliente: \"" + criterio.trim() + "\"",
                        "Sin resultados",
                        JOptionPane.INFORMATION_MESSAGE);
                // Volver a mostrar todas las citas
                configurarTabla();
            } else {
                // Mostrar SOLO los resultados de la búsqueda
                mostrarCitasEnTabla(resultados);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar citas:\n" + e.getMessage(),
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonBuscarCitaActionPerformed

    private void jButtonAgendarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgendarCitaActionPerformed
        FormularioAgendacionCitas formulario = new FormularioAgendacionCitas(this);
        formulario.setVisible(true);
    }//GEN-LAST:event_jButtonAgendarCitaActionPerformed

    private void jButtonCancelarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarCitaActionPerformed
        // Verificar selección
        int filaSeleccionada = jTableCitas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una cita de la tabla para cancelar.",
                    "Ninguna selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener datos de la fila seleccionada
        int citaId = (int) jTableCitas.getValueAt(filaSeleccionada, 0);
        String estadoActual = (String) jTableCitas.getValueAt(filaSeleccionada, 2);

        // Verificar si ya está cancelada
        if ("Cancelada".equals(estadoActual)) {
            JOptionPane.showMessageDialog(this,
                    "La cita ya está cancelada.",
                    "Acción no permitida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmar cancelación
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de cancelar la cita con ID " + citaId + "?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Obtener la cita completa
            CitaControl citaControl = new CitaControl();
            Cita cita = citaControl.obtenerPorId(citaId);

            if (cita == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró la cita con ID " + citaId,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cambiar solo el estado a 'C' (Cancelada)
            cita.setCitaEstado('C');
            citaControl.actualizar(cita);

            // Mostrar mensaje y recargar tabla
            JOptionPane.showMessageDialog(this,
                    "Cita cancelada exitosamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            configurarTabla(); // Actualiza los datos
            configurarAnchoColumnas(); // ← AÑADE ESTA LÍNEA

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cancelar la cita:\n" + e.getMessage(),
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCancelarCitaActionPerformed

    private void jButtonEditarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarCitaActionPerformed
        int filaSeleccionada = jTableCitas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una cita de la tabla para editar.",
                    "Ninguna selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int citaId = (int) jTableCitas.getValueAt(filaSeleccionada, 0);
            CitaControl citaControl = new CitaControl();
            Cita cita = citaControl.obtenerPorId(citaId);

            if (cita == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró la cita seleccionada.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            FormularioAgendacionCitas formulario = new FormularioAgendacionCitas(this, cita);
            formulario.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la cita para edición:\n" + e.getMessage(),
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEditarCitaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgendarCita;
    private javax.swing.JButton jButtonBuscarCita;
    private javax.swing.JButton jButtonCancelarCita;
    private javax.swing.JButton jButtonEditarCita;
    private javax.swing.JButton jButtonRestablecer;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCitas;
    // End of variables declaration//GEN-END:variables
}
