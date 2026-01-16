/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaCabeceraDAO {

    public void insertar(FacturaCabecera cabecera) throws SQLException {
        String sql = """
            INSERT INTO DEN_FACTURAS_CABECERAS (
                fac_id, fac_fecha_emision, fac_subtotal, fac_iva, fac_total, cli_id, usu_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cabecera.getFacId());
            stmt.setDate(2, new Date(cabecera.getFacFechaEmision().getTime()));
            stmt.setDouble(3, cabecera.getFacSubtotal());
            stmt.setDouble(4, cabecera.getFacIva());
            stmt.setDouble(5, cabecera.getFacTotal());
            stmt.setInt(6, cabecera.getCliId());
            stmt.setInt(7, cabecera.getUsuId());
            stmt.executeUpdate();
        }
    }

    public FacturaCabecera obtenerPorId(String facId) throws SQLException {
        String sql = "SELECT * FROM DEN_FACTURAS_CABECERAS WHERE fac_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, facId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }

    public List<FacturaCabecera> listarTodos() throws SQLException {
        List<FacturaCabecera> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_FACTURAS_CABECERAS";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private FacturaCabecera mapearResultSet(ResultSet rs) throws SQLException {
        return new FacturaCabecera(
            rs.getString("fac_id"),
            rs.getDate("fac_fecha_emision"),
            rs.getDouble("fac_subtotal"),
            rs.getDouble("fac_iva"),
            rs.getDouble("fac_total"),
            rs.getInt("cli_id"),
            rs.getInt("usu_id")
        );
    }
}