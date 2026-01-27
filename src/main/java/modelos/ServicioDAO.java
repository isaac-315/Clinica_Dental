/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    public void insertar(Servicio servicio) throws SQLException {
        String sql = """
            INSERT INTO DEN_SERVICIOS (
                ser_nombre,
                ser_precio,
                ser_iva,
                ser_estado
            ) VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servicio.getSerNombre());
            stmt.setDouble(2, servicio.getSerPrecio());
            stmt.setString(3, String.valueOf(servicio.getSerIva()));
            stmt.setString(4, String.valueOf(servicio.getSerEstado()));
            stmt.executeUpdate();
        }
    }

    public Servicio obtenerPorId(int serId) throws SQLException {
        String sql = "SELECT * FROM DEN_SERVICIOS WHERE ser_id = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, serId);
            try (ResultSet rs = stmt.executeQuery()) { // ← Agregado try-with-resources
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Servicio> listarTodos() throws SQLException {
        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_SERVICIOS ORDER BY ser_id";
        try (Connection conn = ConexionDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private Servicio mapearResultSet(ResultSet rs) throws SQLException {
        // Manejo seguro de caracteres
        String ivaStr = rs.getString("ser_iva");
        String estadoStr = rs.getString("ser_estado");

        char iva = (ivaStr != null && !ivaStr.isEmpty()) ? ivaStr.charAt(0) : 'N';
        char estado = (estadoStr != null && !estadoStr.isEmpty()) ? estadoStr.charAt(0) : 'I';

        return new Servicio(
                rs.getInt("ser_id"),
                rs.getString("ser_nombre"),
                rs.getDouble("ser_precio"),
                iva,
                estado
        );
    }

    // En modelos.ServicioDAO.java
    public void eliminar(int serId) throws SQLException {
        String sql = "DELETE FROM DEN_SERVICIOS WHERE ser_id = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, serId);
            stmt.executeUpdate();
        }
    }

    public void actualizar(Servicio servicio) throws SQLException {
        String sql = """
        UPDATE DEN_SERVICIOS SET
            ser_nombre = ?,
            ser_precio = ?,
            ser_iva = ?,
            ser_estado = ?
        WHERE ser_id = ?
        """;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servicio.getSerNombre());
            stmt.setDouble(2, servicio.getSerPrecio());
            stmt.setString(3, String.valueOf(servicio.getSerIva()));
            stmt.setString(4, String.valueOf(servicio.getSerEstado()));
            stmt.setInt(5, servicio.getSerId());
            stmt.executeUpdate();
        }
    }
}
