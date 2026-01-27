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

public class FormularioRegistroServicios extends javax.swing.JDialog {

    private VentanaPrincipal ventanaAnterior;
    private VentanaServicios ventanaServicios;
    private Cita citaExistente = null;
    private javax.swing.ButtonGroup buttonGroupIVA;
    private javax.swing.ButtonGroup buttonGroupEstado;

    // ✅ Constructor por defecto que usa un propietario nulo (funciona, pero mejor con owner)
    // Constructor para crear nueva cita
    // En FormularioRegistroServicios.java
    // Reemplaza el constructor actual
    public FormularioRegistroServicios(VentanaServicios owner) {
        super(owner);
        this.ventanaServicios = owner; // ← Guardar la referencia
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        postInit();
    }

    // Constructor para editar servicio existente
    public FormularioRegistroServicios(VentanaServicios owner, modelos.Servicio servicioExistente) {
        super(owner);
        this.ventanaServicios = owner;
        this.servicioExistente = servicioExistente;
        initComponents();
        setModal(true);
        setLocationRelativeTo(null);
        postInit();

        if (servicioExistente != null) {
            jTextFieldCedulaCliente1.setText(servicioExistente.getSerNombre());
            jTextFieldCedulaCliente2.setText(String.format("%.2f", servicioExistente.getSerPrecio()));

            // Establecer IVA
            if (servicioExistente.getSerIva() == 'S') {
                jRadioButtonSi.setSelected(true);
            } else {
                jRadioButtonNo.setSelected(true);
            }

            // Establecer Estado
            if (servicioExistente.getSerEstado() == 'A') {
                jRadioButtonActivo.setSelected(true);
            } else {
                jRadioButtonInactivo.setSelected(true);
            }
        }
    }

// Declara esta variable en la clase
    private modelos.Servicio servicioExistente = null;

    private void postInit() {
        // Configurar grupos de radio buttons
        buttonGroupIVA = new javax.swing.ButtonGroup();
        buttonGroupIVA.add(jRadioButtonSi);
        buttonGroupIVA.add(jRadioButtonNo);

        buttonGroupEstado = new javax.swing.ButtonGroup();
        buttonGroupEstado.add(jRadioButtonActivo);
        buttonGroupEstado.add(jRadioButtonInactivo);

        // Valores por defecto
        jRadioButtonSi.setSelected(true);
        jRadioButtonActivo.setSelected(true);

        // ✅ Color de fondo igual al panel (azul #6699FF)
        java.awt.Color colorPanel = new java.awt.Color(102, 153, 255);

        // Grupo IVA
        jRadioButtonSi.setBackground(colorPanel);
        jRadioButtonSi.setOpaque(true);
        jRadioButtonNo.setBackground(colorPanel);
        jRadioButtonNo.setOpaque(true);

        // Grupo Estado
        jRadioButtonActivo.setBackground(colorPanel);
        jRadioButtonActivo.setOpaque(true);
        jRadioButtonInactivo.setBackground(colorPanel);
        jRadioButtonInactivo.setOpaque(true);
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
        jButtonAgregarServicio = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jRadioButtonSi = new javax.swing.JRadioButton();
        jRadioButtonNo = new javax.swing.JRadioButton();
        jTextFieldCedulaCliente1 = new javax.swing.JTextField();
        jTextFieldCedulaCliente2 = new javax.swing.JTextField();
        jRadioButtonActivo = new javax.swing.JRadioButton();
        jRadioButtonInactivo = new javax.swing.JRadioButton();

        jTextField4.setText("jTextField1");

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel20.setText("Teléfono");

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel22.setText("Teléfono");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabelFormularioClientes.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabelFormularioClientes.setText("Agregar Servicio");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Nombre de servicio");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Precio");

        jButtonAgregarServicio.setText("Agregar");
        jButtonAgregarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarServicioActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel16.setText("IVA");

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel17.setText("Estado");

        jRadioButtonSi.setText("Sí");

        jRadioButtonNo.setText("No");
        jRadioButtonNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNoActionPerformed(evt);
            }
        });

        jRadioButtonActivo.setText("Activo");

        jRadioButtonInactivo.setText("Inactivo");

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
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAgregarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCedulaCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCedulaCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButtonActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonSi, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonInactivo, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(90, Short.MAX_VALUE))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCedulaCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextFieldCedulaCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonSi)
                    .addComponent(jRadioButtonNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonActivo)
                    .addComponent(jRadioButtonInactivo))
                .addGap(39, 39, 39)
                .addComponent(jButtonAgregarServicio)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarServicioActionPerformed
        try {
            // 1. Validar campos obligatorios
            String nombreServicio = jTextFieldCedulaCliente1.getText().trim();
            String precioStr = jTextFieldCedulaCliente2.getText().trim();

            if (nombreServicio.isEmpty() || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "El nombre del servicio y el precio son obligatorios.",
                        "Campos requeridos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2. Validar formato del precio con expresión regular
            String patronPrecio = "^\\d{1,3}(\\.\\d{1,2})?$";

            if (!precioStr.matches(patronPrecio)) {
                JOptionPane.showMessageDialog(this,
                        "El precio debe tener el formato: hasta 3 dígitos enteros y hasta 2 decimales.\n"
                        + "Ejemplos válidos: 50, 125.5, 999.99",
                        "Formato de precio inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. Convertir a double y validar rango
            double precio = Double.parseDouble(precioStr);
            if (precio <= 0 || precio > 999.99) {
                JOptionPane.showMessageDialog(this,
                        "El precio debe estar entre 0.01 y 999.99.",
                        "Precio fuera de rango",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 4. Obtener valores de los radio buttons
            char iva = jRadioButtonSi.isSelected() ? 'S' : 'N';
            char estado = jRadioButtonActivo.isSelected() ? 'A' : 'I';

            // 5. Crear o actualizar el servicio
            if (servicioExistente != null) {
                // Modo EDICIÓN
                servicioExistente.setSerNombre(nombreServicio);
                servicioExistente.setSerPrecio(precio);
                servicioExistente.setSerIva(iva);
                servicioExistente.setSerEstado(estado);

                controladores.ServicioControl servicioControl = new controladores.ServicioControl();
                servicioControl.actualizar(servicioExistente);
                JOptionPane.showMessageDialog(this, "Servicio actualizado exitosamente.");

            } else {
                // Modo CREACIÓN
                modelos.Servicio servicio = new modelos.Servicio(
                        0, nombreServicio, precio, iva, estado
                );
                controladores.ServicioControl servicioControl = new controladores.ServicioControl();
                servicioControl.guardar(servicio);
                JOptionPane.showMessageDialog(this, "Servicio registrado exitosamente.");
            }

            // Refrescar y cerrar
            if (ventanaServicios != null) {
                ventanaServicios.refrescarTabla();
            }
            this.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Error en el formato del precio. Use números válidos.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al registrar el servicio:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAgregarServicioActionPerformed

    private void jRadioButtonNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonNoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarServicio;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelFormularioClientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonActivo;
    private javax.swing.JRadioButton jRadioButtonInactivo;
    private javax.swing.JRadioButton jRadioButtonNo;
    private javax.swing.JRadioButton jRadioButtonSi;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldCedulaCliente1;
    private javax.swing.JTextField jTextFieldCedulaCliente2;
    // End of variables declaration//GEN-END:variables
}
