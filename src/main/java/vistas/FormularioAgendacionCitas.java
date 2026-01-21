/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.CitaControl;
import controladores.ClienteControl;
import controladores.EmpleadoControl;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import modelos.Cita;
import modelos.Cliente;
import modelos.Empleado;

public class FormularioAgendacionCitas extends javax.swing.JDialog {

    private VentanaPrincipal ventanaAnterior;
    private Cita citaExistente = null;

    // ✅ Constructor por defecto que usa un propietario nulo (funciona, pero mejor con owner)
    // Constructor para crear nueva cita
    public FormularioAgendacionCitas(VentanaCitas owner) {
        super(owner);
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        postInit();
    }

// Constructor para editar cita existente
public FormularioAgendacionCitas(VentanaCitas owner, Cita cita) {
    this(owner); // Llama al constructor anterior (que ya selecciona "Pendiente")
    this.citaExistente = cita;
    
    if (cita != null) {
        try {
            // Fecha
            jDateChooserCitas.setDate(new Date(cita.getCitaFechaHora().getTime()));
            
            // Hora
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(cita.getCitaFechaHora().getTime()));
            jSpinnerHora.setValue(cal.getTime());
            
            // Estado
            if (cita.getCitaEstado() == 'C') {
                jRadioButtonCancelado.setSelected(true);
            }
            // Si es 'P', ya está seleccionado por postInit()
            
            // Cédulas (buscar los objetos completos)
            ClienteControl cliCtrl = new ClienteControl();
            EmpleadoControl empCtrl = new EmpleadoControl();
            
            Cliente cliente = cliCtrl.obtenerPorId(cita.getCliId());
            Empleado empleado = empCtrl.obtenerPorId(cita.getEmpId());
            
            if (cliente != null) {
                jTextFieldCedulaCliente.setText(cliente.getCliCedula());
            }
            if (empleado != null) {
                jTextFieldCedulaEmpleado.setText(empleado.getEmpCedula());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar los datos de la cita:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelFormularioClientes = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonAgendarCita = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jDateChooserCitas = new com.toedter.calendar.JDateChooser();
        jTextFieldCedulaCliente = new javax.swing.JTextField();
        jTextFieldCedulaEmpleado = new javax.swing.JTextField();
        jSpinnerHora = new javax.swing.JSpinner();
        jRadioButtonPendiente = new javax.swing.JRadioButton();
        jRadioButtonCancelado = new javax.swing.JRadioButton();

        jTextField4.setText("jTextField1");

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel20.setText("Teléfono");

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel22.setText("Teléfono");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabelFormularioClientes.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabelFormularioClientes.setText("Agendar Citas");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Fecha");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Hora");

        jButtonAgendarCita.setText("Agendar");
        jButtonAgendarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgendarCitaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel16.setText("Estado");

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel17.setText("Cliente");

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel18.setText("Empleado Asignado");

        jRadioButtonPendiente.setText("Pendiente");

        jRadioButtonCancelado.setText("Cancelado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonAgendarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooserCitas, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                        .addComponent(jTextFieldCedulaCliente)
                        .addComponent(jTextFieldCedulaEmpleado)
                        .addComponent(jSpinnerHora))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonCancelado)))
                .addGap(82, 82, 82))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jLabelFormularioClientes)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFormularioClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooserCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonPendiente)
                    .addComponent(jRadioButtonCancelado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCedulaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jButtonAgendarCita)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void postInit() {
        // Configurar el spinner de hora
        SpinnerDateModel horaModel = new SpinnerDateModel();
        horaModel.setCalendarField(Calendar.MINUTE);
        jSpinnerHora.setModel(horaModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jSpinnerHora, "HH:mm");
        jSpinnerHora.setEditor(editor);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);
        jSpinnerHora.setValue(cal.getTime());

        // ✅ Fondo de los radio buttons
        java.awt.Color colorPanel = new java.awt.Color(102, 153, 255);
        jRadioButtonPendiente.setBackground(colorPanel);
        jRadioButtonPendiente.setOpaque(true);
        jRadioButtonCancelado.setBackground(colorPanel);
        jRadioButtonCancelado.setOpaque(true);

        // ✅ Seleccionar "Pendiente" por defecto
        jRadioButtonPendiente.setSelected(true);
    }
    private void jButtonAgendarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgendarCitaActionPerformed
        try {
            // 1. Validar fecha
            Date fecha = jDateChooserCitas.getDate();
            if (fecha == null) {
                JOptionPane.showMessageDialog(this,
                        "Seleccione una fecha válida.",
                        "Fecha requerida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar fecha futura
            Calendar hoy = Calendar.getInstance();
            hoy.set(Calendar.HOUR_OF_DAY, 0);
            hoy.set(Calendar.MINUTE, 0);
            hoy.set(Calendar.SECOND, 0);
            hoy.set(Calendar.MILLISECOND, 0);

            Calendar fechaCita = Calendar.getInstance();
            fechaCita.setTime(fecha);
            fechaCita.set(Calendar.HOUR_OF_DAY, 0);
            fechaCita.set(Calendar.MINUTE, 0);
            fechaCita.set(Calendar.SECOND, 0);
            fechaCita.set(Calendar.MILLISECOND, 0);

            if (!fechaCita.after(hoy)) {
                JOptionPane.showMessageDialog(this,
                        "La cita debe ser en una fecha futura (no se permite hoy ni fechas pasadas).",
                        "Fecha inválida",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Validar hora (CORREGIDO)
            Object valorHora = jSpinnerHora.getValue();
            if (valorHora == null) {
                JOptionPane.showMessageDialog(this,
                        "Seleccione una hora válida.",
                        "Hora requerida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Calendar calHora = Calendar.getInstance();
            if (valorHora instanceof Date) {
                calHora.setTime((Date) valorHora);
            } else if (valorHora instanceof Calendar) {
                calHora = (Calendar) valorHora;
            } else {
                JOptionPane.showMessageDialog(this,
                        "Formato de hora no válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Combinar fecha + hora
            Calendar calFecha = Calendar.getInstance();
            calFecha.setTime(fecha);

            Calendar citaCompleta = Calendar.getInstance();
            citaCompleta.set(
                    calFecha.get(Calendar.YEAR),
                    calFecha.get(Calendar.MONTH),
                    calFecha.get(Calendar.DAY_OF_MONTH),
                    calHora.get(Calendar.HOUR_OF_DAY),
                    calHora.get(Calendar.MINUTE),
                    0
            );
            citaCompleta.set(Calendar.MILLISECOND, 0);

            Date fechaHoraCita = citaCompleta.getTime();

            // 4. Obtener cédulas
            String cedulaCliente = jTextFieldCedulaCliente.getText().trim();
            String cedulaEmpleado = jTextFieldCedulaEmpleado.getText().trim();

            if (cedulaCliente.isEmpty() || cedulaEmpleado.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese las cédulas del cliente y empleado.",
                        "Campos requeridos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 5. Obtener estado
            char estado;
            if (jRadioButtonPendiente.isSelected()) {
                estado = 'P';
            } else if (jRadioButtonCancelado.isSelected()) {
                estado = 'C';
            } else {
                JOptionPane.showMessageDialog(this,
                        "Seleccione un estado para la cita (Pendiente o Cancelada).",
                        "Estado requerido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 6. Buscar IDs por cédula
            ClienteControl clienteControl = new ClienteControl();
            EmpleadoControl empleadoControl = new EmpleadoControl();

            Cliente cliente = clienteControl.obtenerPorCedula(cedulaCliente);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this,
                        "Cliente no encontrado con cédula: " + cedulaCliente,
                        "Cliente no existe",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Empleado empleado = empleadoControl.obtenerPorCedula(cedulaEmpleado);
            if (empleado == null) {
                JOptionPane.showMessageDialog(this,
                        "Empleado no encontrado con cédula: " + cedulaEmpleado,
                        "Empleado no existe",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 7. Crear o actualizar cita
            CitaControl citaControl = new CitaControl();

            if (citaExistente != null) {
                // Modo EDICIÓN: actualizar cita existente
                citaExistente.setCitaFechaHora(new java.sql.Timestamp(fechaHoraCita.getTime()));
                citaExistente.setCitaEstado(estado);
                citaExistente.setCliId(cliente.getCliId());
                citaExistente.setEmpId(empleado.getEmpId());

                citaControl.actualizar(citaExistente);
            } else {
                // Modo CREACIÓN: guardar nueva cita
                Cita cita = new Cita(
                        0,
                        new java.sql.Timestamp(fechaHoraCita.getTime()),
                        estado,
                        cliente.getCliId(),
                        empleado.getEmpId()
                );
                citaControl.guardar(cita);
            }

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this,
                    citaExistente != null ? "Cita actualizada exitosamente." : "Cita agendada exitosamente.");

            // Notificar a la ventana padre para que se actualice
            if (getOwner() instanceof VentanaCitas) {
                ((VentanaCitas) getOwner()).refrescarTabla();
            }

            this.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error inesperado:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAgendarCitaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgendarCita;
    private com.toedter.calendar.JDateChooser jDateChooserCitas;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelFormularioClientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonCancelado;
    private javax.swing.JRadioButton jRadioButtonPendiente;
    private javax.swing.JSpinner jSpinnerHora;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldCedulaCliente;
    private javax.swing.JTextField jTextFieldCedulaEmpleado;
    // End of variables declaration//GEN-END:variables
}
