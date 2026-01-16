/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.ClienteControl;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import modelos.Cliente;

public class FormularioRegistroCliente extends javax.swing.JDialog {

    private VentanaClientes ventanaClientes; // ← Referencia para notificar
    private Cliente clienteAEeditar; // null si es nuevo, != null si es edición

    // Constructor que recibe la ventana padre
    // Constructor para REGISTRAR nuevo cliente
    public FormularioRegistroCliente(VentanaClientes ventanaClientes) {
        this.ventanaClientes = ventanaClientes;
        this.clienteAEeditar = null;
        initComponents();
        configurarRadioButtons();
        setLocationRelativeTo(null);
    }

// Constructor para EDITAR cliente existente
    public FormularioRegistroCliente(VentanaClientes ventanaClientes, Cliente cliente) {
        this.ventanaClientes = ventanaClientes;
        this.clienteAEeditar = cliente;
        initComponents();
        configurarRadioButtons();
        cargarDatosCliente(); // ← Llena los campos
        setLocationRelativeTo(null);
    }

    private void cargarDatosCliente() {
        if (clienteAEeditar != null) {
            jTextFieldCedula.setText(clienteAEeditar.getCliCedula());
            jTextFieldNombres.setText(clienteAEeditar.getCliNombre());
            jTextFieldApellidos.setText(clienteAEeditar.getCliApellido());
            jTextFieldDireccion.setText(clienteAEeditar.getCliDireccion());
            jTextFieldTelefonoCo.setText(clienteAEeditar.getCliTelefonoConvencional());
            jTextFieldTelefonoCe.setText(clienteAEeditar.getCliTelefonoCelular());
            jTextFieldCorreo.setText(clienteAEeditar.getCliCorreoElectronico());

            // Estado
            if (clienteAEeditar.getCliEstado() == 'A') {
                jRadioButtonActivo.setSelected(true);
            } else {
                jRadioButtonInactivo.setSelected(true);
            }
            jTextFieldCedula.setEditable(false);
            jTextFieldCedula.setFocusable(false);
            jTextFieldCedula.setEnabled(false);
            
            jTextFieldCedula.setDisabledTextColor(java.awt.Color.BLACK);

        }
    }

    // En FormularioRegistroCliente.java, dentro de la clase (por ejemplo, después de los constructores)
    private void configurarRadioButtons() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(jRadioButtonActivo);
        grupo.add(jRadioButtonInactivo);
        jRadioButtonActivo.setSelected(true); // Activo por defecto
    }

    // Dentro de FormularioRegistroCliente.java
    private boolean validarCedulaEcuatoriana(String cedula) {
        // 1. Verificar longitud
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        // 2. Verificar que todos los caracteres sean dígitos
        for (char c : cedula.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // 3. Verificar código de provincia (01-24 o 30)
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || (provincia > 24 && provincia != 30)) {
            return false;
        }

        // 4. Algoritmo de verificación (módulo 10)
        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int suma = 0;

        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            int producto = digito * coeficientes[i];
            if (producto >= 10) {
                producto = producto - 9;
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
        jButtonAgregarCliente = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldTelefonoCo = new javax.swing.JTextField();
        jTextFieldTelefonoCe = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        jRadioButtonActivo = new javax.swing.JRadioButton();
        jRadioButtonInactivo = new javax.swing.JRadioButton();

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

        jLabelFormularioClientes.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabelFormularioClientes.setText("Formulario de Clientes");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Cédula");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Nombres");

        jButtonAgregarCliente.setText("Registrar");
        jButtonAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarClienteActionPerformed(evt);
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

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel23.setText("Estado");

        jRadioButtonActivo.setText("Activo");
        jRadioButtonActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonActivoActionPerformed(evt);
            }
        });

        jRadioButtonInactivo.setText("Inactivo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabelFormularioClientes)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelefonoCo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelefonoCe, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonActivo)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonInactivo)))))
                .addGap(94, 94, 94))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFormularioClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonActivo)
                            .addComponent(jRadioButtonInactivo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButtonAgregarCliente)
                .addGap(26, 26, 26))
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

    private void jTextFieldNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombresActionPerformed

    private void jButtonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarClienteActionPerformed
        // Obtener datos de los campos
        String cedula = jTextFieldCedula.getText().trim();
        String nombres = jTextFieldNombres.getText().trim();
        String apellidos = jTextFieldApellidos.getText().trim();
        String direccion = jTextFieldDireccion.getText().trim();
        String telConv = jTextFieldTelefonoCo.getText().trim();
        String telCel = jTextFieldTelefonoCe.getText().trim();
        String correo = jTextFieldCorreo.getText().trim();

        // Validar campos obligatorios
        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty()
                || direccion.isEmpty() || telConv.isEmpty() || telCel.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos obligatorios.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar cédula ecuatoriana
        if (!validarCedulaEcuatoriana(cedula)) {
            JOptionPane.showMessageDialog(this,
                    "La cédula ingresada no es válida según el formato ecuatoriano.",
                    "Cédula inválida",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar correo electrónico (básico)
        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(this,
                    "El correo electrónico no es válido.",
                    "Correo inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar teléfonos
        if (telConv.length() != 7 || !telConv.matches("\\d{7}")) {
            JOptionPane.showMessageDialog(this,
                    "El teléfono convencional debe tener exactamente 7 dígitos numéricos.",
                    "Teléfono inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (telCel.length() != 10 || !telCel.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this,
                    "El teléfono celular debe tener exactamente 10 dígitos numéricos.",
                    "Teléfono inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener estado desde los radio buttons (solo una vez)
        char estadoChar = jRadioButtonActivo.isSelected() ? 'A' : 'I';

        try {
            ClienteControl control = new ClienteControl();

            if (clienteAEeditar == null) {
                // Modo CREAR
                Cliente nuevo = new Cliente(
                        0,
                        cedula,
                        nombres,
                        apellidos,
                        direccion,
                        telConv,
                        telCel,
                        correo,
                        estadoChar
                );
                control.guardar(nuevo);
                JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente.");
            } else {
                // Modo EDITAR
                Cliente actualizado = new Cliente(
                        clienteAEeditar.getCliId(),
                        cedula,
                        nombres,
                        apellidos,
                        direccion,
                        telConv,
                        telCel,
                        correo,
                        estadoChar
                );
                control.actualizar(actualizado);
                JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
            }

            // Actualizar la tabla en la ventana principal
            if (ventanaClientes != null) {
                ventanaClientes.refrescarTabla();
            }

            // Cerrar el formulario
            this.dispose();

        } catch (SQLException e) {
            // Manejo específico para cédula duplicada (ORA-00001)
            if (e.getErrorCode() == 1) {
                JOptionPane.showMessageDialog(this,
                        "La cédula ya está registrada en el sistema.",
                        "Cédula duplicada",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Otros errores de base de datos
                JOptionPane.showMessageDialog(this,
                        "Error al guardar el cliente en la base de datos:\n" + e.getMessage(),
                        "Error de base de datos",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonAgregarClienteActionPerformed

    private void jRadioButtonActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonActivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarCliente;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelFormularioClientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonActivo;
    private javax.swing.JRadioButton jRadioButtonInactivo;
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
