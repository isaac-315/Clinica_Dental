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
            INSERT INTO DEN_SERVICIOS (ser_id, ser_nombre, ser_precio, ser_iva, ser_estado)
            VALUES (?, ?, ?, ?, ?)
            """;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicio.getSerId());
            stmt.setString(2, servicio.getSerNombre());
            stmt.setDouble(3, servicio.getSerPrecio());
            stmt.setString(4, String.valueOf(servicio.getSerIva()));
            stmt.setString(5, String.valueOf(servicio.getSerEstado()));
            stmt.executeUpdate();
        }
    }

    public Servicio obtenerPorId(int serId) throws SQLException {
        String sql = "SELECT * FROM DEN_SERVICIOS WHERE ser_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, serId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }

    public List<Servicio> listarTodos() throws SQLException {
        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_SERVICIOS";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private Servicio mapearResultSet(ResultSet rs) throws SQLException {
        return new Servicio(
            rs.getInt("ser_id"),
            rs.getString("ser_nombre"),
            rs.getDouble("ser_precio"),
            rs.getString("ser_iva").charAt(0),
            rs.getString("ser_estado").charAt(0)
        );
    }
}