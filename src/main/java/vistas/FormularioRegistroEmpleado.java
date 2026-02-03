/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import modelos.Empleado;
import modelos.EmpleadoDAO;

public class FormularioRegistroEmpleado extends javax.swing.JDialog {

    private Empleado empleadoEdicion = null;
    private boolean modoEdicion = false;

    // Constructor para REGISTRO (nuevo)
    public FormularioRegistroEmpleado(VentanaEmpleados owner) {
        super(owner);
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        this.modoEdicion = false;
        this.empleadoEdicion = null;
        jLabelFormularioClientes.setText("Registrar Nuevo Empleado");
    }

// Constructor para EDICIÓN
    public FormularioRegistroEmpleado(VentanaEmpleados owner, Empleado empleado) {
        super(owner);
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        this.modoEdicion = true;
        this.empleadoEdicion = empleado;
        cargarDatosEmpleado(empleado);
        jLabelFormularioClientes.setText("Editar Empleado");
        jButtonAgregarEmpleado.setText("Actualizar");
    }

    private void cargarDatosEmpleado(Empleado emp) {
        jTextFieldCedula.setText(emp.getEmpCedula());
        jTextFieldNombres.setText(emp.getEmpNombre());
        jTextFieldApellidos.setText(emp.getEmpApellido());
        jTextFieldDireccion.setText(emp.getEmpDireccion());
        jTextFieldTelefonoCo.setText(emp.getEmpTelefonoConvencional());
        jTextFieldTelefonoCe.setText(emp.getEmpTelefonoCelular());
        jTextFieldCorreo.setText(emp.getEmpCorreoElectronico());

        // Si la cédula no es editable en edición, deshabilita el campo
        jTextFieldCedula.setEditable(false);
    }

    // Dentro de FormularioRegistroEmpleado.java
    private boolean validarCampos() {
        // 1. Cédula
        String cedula = jTextFieldCedula.getText().trim();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La cédula es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarCedulaEcuatoriana(cedula)) {
            JOptionPane.showMessageDialog(this, "La cédula no es válida según el formato ecuatoriano.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 2. Nombres
        String nombres = jTextFieldNombres.getText().trim();
        if (nombres.isEmpty() || !nombres.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,50}$")) {
            JOptionPane.showMessageDialog(this, "Los nombres deben contener solo letras y espacios (mínimo 2 caracteres).", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 3. Apellidos
        String apellidos = jTextFieldApellidos.getText().trim();
        if (apellidos.isEmpty() || !apellidos.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,50}$")) {
            JOptionPane.showMessageDialog(this, "Los apellidos deben contener solo letras y espacios (mínimo 2 caracteres).", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 4. Dirección
        String direccion = jTextFieldDireccion.getText().trim();
        if (direccion.isEmpty() || direccion.length() < 5 || !direccion.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s.,#-]{5,150}$")) {
            JOptionPane.showMessageDialog(this, "La dirección debe tener al menos 5 caracteres y puede incluir letras, números y símbolos como . , # -", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 5. Teléfono Convencional (7 dígitos)
        String telConv = jTextFieldTelefonoCo.getText().trim();
        if (telConv.isEmpty() || !telConv.matches("\\d{7}")) {
            JOptionPane.showMessageDialog(this, "El teléfono convencional debe tener exactamente 7 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 6. Teléfono Celular (10 dígitos)
        String telCel = jTextFieldTelefonoCe.getText().trim();
        if (telCel.isEmpty() || !telCel.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "El teléfono celular debe tener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 7. Correo electrónico
        String correo = jTextFieldCorreo.getText().trim();
        if (correo.isEmpty() || !correo.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(this, "El correo electrónico no tiene un formato válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Todos los campos son válidos
    }

    private boolean validarCedulaEcuatoriana(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        for (char c : cedula.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || (provincia > 24 && provincia != 30)) {
            return false;
        }

        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int suma = 0;

        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            int producto = digito * coeficientes[i];
            if (producto >= 10) {
                producto -= 9;
            }
            suma += producto;
        }

        int digitoVerificadorCalculado = (suma % 10 == 0) ? 0 : (10 - (suma % 10));
        int digitoVerificadorReal = Character.getNumericValue(cedula.charAt(9));

        return digitoVerificadorCalculado == digitoVerificadorReal;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldCedula = new javax.swing.JTextField();
        jTextFieldNombres = new javax.swing.JTextField();
        jTextFieldApellidos = new javax.swing.JTextField();
        jLabelFormularioClientes = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonAgregarEmpleado = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldTelefonoCo = new javax.swing.JTextField();
        jTextFieldTelefonoCe = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();

        jTextField4.setText("jTextField1");

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel20.setText("Teléfono");

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel22.setText("Teléfono");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jTextFieldNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombresActionPerformed(evt);
            }
        });

        jLabelFormularioClientes.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabelFormularioClientes.setText("Formulario de Empleados");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Cédula");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Nombres");

        jButtonAgregarEmpleado.setText("Registrar");
        jButtonAgregarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarEmpleadoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel16.setText("Apellidos");

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel17.setText("Dirección");

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel18.setText("Teléfono Convencional");

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel19.setText("Teléfono Celular");

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel21.setText("Correo electrónico");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabelFormularioClientes)
                .addGap(0, 202, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jButtonAgregarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jTextFieldTelefonoCo, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jTextFieldTelefonoCe, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .addGap(94, 94, 94))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFormularioClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefonoCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefonoCe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jButtonAgregarEmpleado)
                .addContainerGap(56, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombresActionPerformed

    private void jButtonAgregarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarEmpleadoActionPerformed
        try {
            if (!validarCampos()) {
                return;
            }

            if (modoEdicion) {
                // MODO EDICIÓN: Actualizar empleado existente
                empleadoEdicion.setEmpCedula(jTextFieldCedula.getText().trim());
                empleadoEdicion.setEmpNombre(jTextFieldNombres.getText().trim());
                empleadoEdicion.setEmpApellido(jTextFieldApellidos.getText().trim());
                empleadoEdicion.setEmpDireccion(jTextFieldDireccion.getText().trim());
                empleadoEdicion.setEmpTelefonoConvencional(jTextFieldTelefonoCo.getText().trim());
                empleadoEdicion.setEmpTelefonoCelular(jTextFieldTelefonoCe.getText().trim());
                empleadoEdicion.setEmpCorreoElectronico(jTextFieldCorreo.getText().trim());

                EmpleadoDAO dao = new EmpleadoDAO();
                dao.actualizar(empleadoEdicion);

                JOptionPane.showMessageDialog(this, "Empleado actualizado exitosamente.");

            } else {
                // MODO REGISTRO: Crear nuevo empleado
                Empleado emp = new Empleado(
                        jTextFieldCedula.getText().trim(),
                        jTextFieldNombres.getText().trim(),
                        jTextFieldApellidos.getText().trim(),
                        jTextFieldDireccion.getText().trim(),
                        jTextFieldTelefonoCo.getText().trim(),
                        jTextFieldTelefonoCe.getText().trim(),
                        jTextFieldCorreo.getText().trim()
                );

                EmpleadoDAO dao = new EmpleadoDAO();
                dao.insertar(emp);

                JOptionPane.showMessageDialog(this, "Empleado registrado exitosamente.");
            }

            // Notificar a la ventana padre para refrescar
            if (getOwner() instanceof VentanaEmpleados) {
                ((VentanaEmpleados) getOwner()).refrescarTabla();
            }

            this.dispose();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error de base de datos:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error inesperado:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAgregarEmpleadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarEmpleado;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelFormularioClientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCedula;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldNombres;
    private javax.swing.JTextField jTextFieldTelefonoCe;
    private javax.swing.JTextField jTextFieldTelefonoCo;
    // End of variables declaration//GEN-END:variables
}
