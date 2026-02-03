// Archivo: SeleccionServicio.java
package vistas;

import javax.swing.table.DefaultTableModel;
import modelos.Servicio;
import modelos.ServicioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class SeleccionServicio extends javax.swing.JDialog {

    private CreacionFacturas ventanaFactura;
    private Servicio servicioSeleccionado = null;

    public SeleccionServicio(CreacionFacturas parent) {
        super(parent, true);
        this.ventanaFactura = parent;
        initComponents();
        cargarServicios();
        setLocationRelativeTo(parent);
    }

    private void cargarServicios() {
        try {
            ServicioDAO dao = new ServicioDAO();
            List<Servicio> servicios = dao.listarTodosActivos();

            System.out.println("Servicios cargados: " + servicios.size());

            DefaultTableModel modelo = (DefaultTableModel) tablaServicios.getModel();
            modelo.setRowCount(0);

            for (Servicio s : servicios) {
                System.out.println("Agregando servicio: ID=" + s.getSerId() + ", Nombre=" + s.getSerNombre());

                String precioFormateado = String.format("%.2f", s.getSerPrecio());
                String ivaTexto = (s.getSerIva() == 'S') ? "Sí" : "No";

                Object[] fila = {
                    s.getSerId(),
                    s.getSerNombre(),
                    precioFormateado,
                    ivaTexto
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar servicios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jButtonSeleccionar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar Servicio");

        // ✅ MODELO CORRECTO: Sin Object[][] inicial
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Servicio", "Precio", "IVA"}, 0 // ← 0 filas iniciales
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            // 👇 ESPECIFICAR TIPOS DE DATOS (OPCIONAL PERO RECOMENDADO)
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Integer.class;  // ID
                    case 1:
                        return String.class;   // Servicio  
                    case 2:
                        return String.class;   // Precio (formateado como String)
                    case 3:
                        return String.class;   // IVA
                    default:
                        return Object.class;
                }
            }
        };

        tablaServicios.setModel(modelo);
        tablaServicios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaServicios);

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
    }

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tablaServicios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un servicio de la lista");
            return;
        }

        // Obtener el ID (debe ser Integer, no null)
        Object idObj = tablaServicios.getValueAt(fila, 0);
        if (idObj == null) {
            JOptionPane.showMessageDialog(this, "Error: Servicio seleccionado no válido");
            return;
        }

        int serId;
        if (idObj instanceof Integer) {
            serId = (Integer) idObj;
        } else {
            serId = Integer.parseInt(idObj.toString());
        }

        // Obtener el precio (está como String formateado, convertir a double)
        String precioStr = tablaServicios.getValueAt(fila, 2).toString();
        double precio = Double.parseDouble(precioStr);

        // Obtener nombre
        String nombreServicio = tablaServicios.getValueAt(fila, 1).toString();

        // Agregar a la factura
        ventanaFactura.agregarServicioSeleccionado(serId, nombreServicio, 1, precio);
        dispose();
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    // Variables declaration
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaServicios;
}
