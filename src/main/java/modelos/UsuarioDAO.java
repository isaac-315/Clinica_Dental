/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void insertar(Usuario usuario) throws SQLException {
        String sql = """
            INSERT INTO DEN_USUARIOS (usu_id, usu_usuario, usu_contrasena, usu_tipo, emp_id)
            VALUES (?, ?, ?, ?, ?)
            """;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getUsuId());
            stmt.setString(2, usuario.getUsuUsuario());
            stmt.setString(3, usuario.getUsuContrasena());
            stmt.setString(4, String.valueOf(usuario.getUsuTipo()));
            stmt.setInt(5, usuario.getEmpId());
            stmt.executeUpdate();
        }
    }

    public Usuario obtenerPorId(int usuId) throws SQLException {
        String sql = "SELECT * FROM DEN_USUARIOS WHERE usu_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_USUARIOS";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private Usuario mapearResultSet(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("usu_id"),
            rs.getString("usu_usuario"),
            rs.getString("usu_contrasena"),
            rs.getString("usu_tipo").charAt(0),
            rs.getInt("emp_id")
        );
    }
}