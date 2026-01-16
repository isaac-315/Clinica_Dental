/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public void insertar(Cita cita) throws SQLException {
        String sql = """
            INSERT INTO DEN_CITAS (cita_id, cita_fecha_hora, cita_estado, cli_id, emp_id)
            VALUES (?, ?, ?, ?, ?)
            """;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cita.getCitaId());
            stmt.setTimestamp(2, new Timestamp(cita.getCitaFechaHora().getTime()));
            stmt.setString(3, String.valueOf(cita.getCitaEstado()));
            stmt.setInt(4, cita.getCliId());
            stmt.setInt(5, cita.getEmpId());
            stmt.executeUpdate();
        }
    }

    public Cita obtenerPorId(int citaId) throws SQLException {
        String sql = "SELECT * FROM DEN_CITAS WHERE cita_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, citaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }

    public List<Cita> listarTodos() throws SQLException {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_CITAS";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private Cita mapearResultSet(ResultSet rs) throws SQLException {
        return new Cita(
            rs.getInt("cita_id"),
            rs.getTimestamp("cita_fecha_hora"),
            rs.getString("cita_estado").charAt(0),
            rs.getInt("cli_id"),
            rs.getInt("emp_id")
        );
    }
}