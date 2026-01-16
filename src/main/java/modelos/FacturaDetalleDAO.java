/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDetalleDAO {

    public void insertar(FacturaDetalle detalle) throws SQLException {
        String sql = """
            INSERT INTO DEN_FACTURAS_DETALLES (
                det_id, det_cantidad, det_subtotal, det_iva, det_total, fac_id, ser_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getDetId());
            stmt.setInt(2, detalle.getDetCantidad());
            stmt.setDouble(3, detalle.getDetSubtotal());
            stmt.setDouble(4, detalle.getDetIva());
            stmt.setDouble(5, detalle.getDetTotal());
            stmt.setString(6, detalle.getFacId());
            stmt.setInt(7, detalle.getSerId());
            stmt.executeUpdate();
        }
    }

    public List<FacturaDetalle> obtenerPorFactura(String facId) throws SQLException {
        List<FacturaDetalle> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_FACTURAS_DETALLES WHERE fac_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, facId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private FacturaDetalle mapearResultSet(ResultSet rs) throws SQLException {
        return new FacturaDetalle(
            rs.getInt("det_id"),
            rs.getInt("det_cantidad"),
            rs.getDouble("det_subtotal"),
            rs.getDouble("det_iva"),
            rs.getDouble("det_total"),
            rs.getString("fac_id"),
            rs.getInt("ser_id")
        );
    }
}