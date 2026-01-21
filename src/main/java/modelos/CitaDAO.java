package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public void insertar(Cita cita) throws SQLException {
        String sql = """
            INSERT INTO DEN_CITAS (
                cita_fecha_hora,
                cita_estado,
                cli_id,
                emp_id
            ) VALUES (?, ?, ?, ?)
            """;
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setTimestamp(1, cita.getCitaFechaHora());
            stmt.setString(2, String.valueOf(cita.getCitaEstado()));
            stmt.setInt(3, cita.getCliId());
            stmt.setInt(4, cita.getEmpId());
            
            stmt.executeUpdate();
        }
    }

    // 👇 MÉTODO ACTUALIZAR (opcional, pero recomendado)
    public void actualizar(Cita cita) throws SQLException {
        String sql = """
            UPDATE DEN_CITAS SET
                cita_fecha_hora = ?,
                cita_estado = ?,
                cli_id = ?,
                emp_id = ?
            WHERE cita_id = ?
            """;
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setTimestamp(1, cita.getCitaFechaHora());
            stmt.setString(2, String.valueOf(cita.getCitaEstado()));
            stmt.setInt(3, cita.getCliId());
            stmt.setInt(4, cita.getEmpId());
            stmt.setInt(5, cita.getCitaId());
            
            stmt.executeUpdate();
        }
    }

    public void eliminar(int citaId) throws SQLException {
        String sql = "DELETE FROM DEN_CITAS WHERE cita_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, citaId);
            stmt.executeUpdate();
        }
    }

    public Cita obtenerPorId(int citaId) throws SQLException {
        String sql = "SELECT * FROM DEN_CITAS WHERE cita_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, citaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    // 👇 MÉTODO CORREGIDO: listarTodos() en lugar de listarTodas()
    public List<Cita> listarTodos() throws SQLException {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_CITAS ORDER BY cita_fecha_hora";
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
            rs.getInt("CITA_ID"),
            rs.getTimestamp("CITA_FECHA_HORA"),
            rs.getString("CITA_ESTADO").charAt(0),
            rs.getInt("CLI_ID"),
            rs.getInt("EMP_ID")
        );
    }
}