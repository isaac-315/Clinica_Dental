/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import utilidades.PasswordUtil;

public class FormularioCreacionUsuarios extends javax.swing.JDialog {

    private VentanaPrincipal ventanaAnterior;
    private javax.swing.ButtonGroup buttonGroupTipoUsuario;
    private VentanaUsuarios ventanaUsuarios;

    public FormularioCreacionUsuarios(VentanaUsuarios owner) {
        super(owner);
        this.ventanaUsuarios = owner;
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        postInit();
    }

    // Constructor para editar usuario existente
    public FormularioCreacionUsuarios(VentanaUsuarios owner, modelos.Usuario usuarioExistente) {
        super(owner);
        this.ventanaUsuarios = owner;
        this.usuarioExistente = usuarioExistente;
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        postInit();

        if (usuarioExistente != null) {
            // Llenar los campos con los datos del usuario
            jTextFieldCedulaEmpleado.setText(obtenerCedulaEmpleado(usuarioExistente.getEmpId()));
            jTextFieldCedulaEmpleado.setEditable(false);
            jTextFieldUsuario.setText(usuarioExistente.getUsuUsuario());
            jPasswordFieldContrasena.setText(""); // Dejar vacío por seguridad

            // Establecer tipo de usuario
            if (usuarioExistente.getUsuTipo() == 'A') {
                jRadioButtonAdministrador.setSelected(true);
            } else {
                jRadioButtonEmpleadoGeneral.setSelected(true);
            }
        }
    }

// Método auxiliar para obtener la cédula del empleado
    private String obtenerCedulaEmpleado(int empId) {
        try {
            controladores.EmpleadoControl empleadoControl = new controladores.EmpleadoControl();
            modelos.Empleado empleado = empleadoControl.obtenerPorId(empId);
            return (empleado != null) ? empleado.getEmpCedula() : "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

// Declara esta variable en la clase
    private modelos.Usuario usuarioExistente = null;

    private void postInit() {
        // Crear el grupo de botones
        buttonGroupTipoUsuario = new javax.swing.ButtonGroup();
        buttonGroupTipoUsuario.add(jRadioButtonEmpleadoGeneral);
        buttonGroupTipoUsuario.add(jRadioButtonAdministrador);

        // Seleccionar "Empleado general" por defecto
        jRadioButtonEmpleadoGeneral.setSelected(true);

        // Color de fondo igual al panel
        java.awt.Color colorPanel = new java.awt.Color(102, 153, 255);
        jRadioButtonEmpleadoGeneral.setBackground(colorPanel);
        jRadioButtonEmpleadoGeneral.setOpaque(true);
        jRadioButtonAdministrador.setBackground(colorPanel);
        jRadioButtonAdministrador.setOpaque(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabelFormularioClientes = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonAgregarEmpleados = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldCedulaEmpleado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButtonEmpleadoGeneral = new javax.swing.JRadioButton();
        jRadioButtonAdministrador = new javax.swing.JRadioButton();
        jPasswordFieldContrasena = new javax.swing.JPasswordField();

        jTextField4.setText("jTextField1");

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel20.setText("Teléfono");

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel22.setText("Teléfono");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });

        jLabelFormularioClientes.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabelFormularioClientes.setText("Formulario de Usuarios");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Contraseña");

        jButtonAgregarEmpleados.setText("Agregar");
        jButtonAgregarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarEmpleadosActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel16.setText("Usuario");

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel18.setText("Empleado ");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Tipo de usuario");

        jRadioButtonEmpleadoGeneral.setText("Empleado General");

        jRadioButtonAdministrador.setText("Administrador");
        jRadioButtonAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAdministradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(149, 149, 149)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCedulaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAgregarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonEmpleadoGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonAdministrador))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPasswordFieldContrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(jLabelFormularioClientes)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFormularioClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCedulaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonEmpleadoGeneral)
                    .addComponent(jRadioButtonAdministrador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButtonAgregarEmpleados)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

    private void jButtonAgregarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarEmpleadosActionPerformed
        try {
            // 1. Validar campos obligatorios
            String cedulaEmpleado = jTextFieldCedulaEmpleado.getText().trim();
            String usuario = jTextFieldUsuario.getText().trim();
            String contraseña = new String(jPasswordFieldContrasena.getPassword()).trim();

            if (cedulaEmpleado.isEmpty() || usuario.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "La cédula del empleado y el nombre de usuario son obligatorios.",
                        "Campos requeridos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // La contraseña es opcional en edición
            if (usuarioExistente == null && contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "La contraseña es obligatoria para nuevos usuarios.",
                        "Contraseña requerida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2. Validar tipo de usuario
            char tipoUsuario;
            if (jRadioButtonEmpleadoGeneral.isSelected()) {
                tipoUsuario = 'E';
            } else {
                tipoUsuario = 'A';
            }

            // 3. Buscar el empleado por cédula
            controladores.EmpleadoControl empleadoControl = new controladores.EmpleadoControl();
            modelos.Empleado empleado = empleadoControl.obtenerPorCedula(cedulaEmpleado);

            if (empleado == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró ningún empleado con cédula: " + cedulaEmpleado,
                        "Empleado no encontrado",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4. Verificar duplicados (solo si es nuevo usuario o cambia el nombre)
            controladores.UsuarioControl usuarioControl = new controladores.UsuarioControl();
            if (usuarioExistente == null || !usuarioExistente.getUsuUsuario().equals(usuario)) {
                if (usuarioControl.existeUsuario(usuario)) {
                    JOptionPane.showMessageDialog(this,
                            "El nombre de usuario '" + usuario + "' ya existe. Elija otro.",
                            "Usuario duplicado",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // 5. Crear o actualizar usuario
            if (usuarioExistente != null) {
                // Modo EDICIÓN
                usuarioExistente.setUsuUsuario(usuario);
                usuarioExistente.setUsuTipo(tipoUsuario);
                usuarioExistente.setEmpId(empleado.getEmpId());

                // Solo actualizar contraseña si se proporcionó una nueva
                if (!contraseña.isEmpty()) {
                    String hash = PasswordUtil.hashPassword(contraseña);
                    usuarioExistente.setUsuContrasena(hash);

                }

                usuarioControl.actualizar(usuarioExistente);
                JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente.");

            } else {
                // Modo CREACIÓN
                String hash = PasswordUtil.hashPassword(contraseña);

                modelos.Usuario usuarioObj = new modelos.Usuario(
                        0,
                        usuario,
                        hash,
                        tipoUsuario,
                        empleado.getEmpId()
                );

                usuarioControl.guardar(usuarioObj);
                JOptionPane.showMessageDialog(this, "Usuario creado exitosamente.");
            }
            if (ventanaUsuarios != null) {
                ventanaUsuarios.refrescarTabla();
            }

            this.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al guardar el usuario:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAgregarEmpleadosActionPerformed

    private void jRadioButtonAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonAdministradorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarEmpleados;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelFormularioClientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldContrasena;
    private javax.swing.JRadioButton jRadioButtonAdministrador;
    private javax.swing.JRadioButton jRadioButtonEmpleadoGeneral;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldCedulaEmpleado;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
