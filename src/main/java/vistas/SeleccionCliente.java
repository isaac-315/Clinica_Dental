// Archivo: SeleccionCliente.java
package vistas;

import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.ClienteDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class SeleccionCliente extends javax.swing.JDialog {
    
    private CreacionFacturas ventanaFactura;
    private Cliente clienteSeleccionado = null;

    public SeleccionCliente(CreacionFacturas parent) {
        super(parent, true);
        this.ventanaFactura = parent;
        initComponents();
        cargarClientes();
        setLocationRelativeTo(parent);
    }
    
    private void cargarClientes() {
        try {
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> clientes = dao.listarTodos();
            
            DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();
            modelo.setRowCount(0);
            
            for (Cliente c : clientes) {
                Object[] fila = {
                    c.getCliId(),
                    c.getCliCedula(),
                    c.getCliNombre() + " " + c.getCliApellido()
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jButtonSeleccionar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar Cliente");

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Cédula", "Nombre Completo"}
        ));
        tablaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaClientes);

        jButtonSeleccionar.setText("Seleccionar");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSeleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSeleccionar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tablaClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la lista");
            return;
        }
        
        int cliId = (Integer) tablaClientes.getValueAt(fila, 0);
        String nombreCompleto = (String) tablaClientes.getValueAt(fila, 2);
        
        try {
            ClienteDAO dao = new ClienteDAO();
            clienteSeleccionado = dao.obtenerPorId(cliId);
            
            if (clienteSeleccionado != null) {
                ventanaFactura.setClienteSeleccionado(clienteSeleccionado.getCliId(), nombreCompleto);
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar cliente: " + e.getMessage());
        }
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    // Variables declaration
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
}