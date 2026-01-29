package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    /* =========================
       INSERTAR
       ========================= */
    public void insertar(Usuario usuario) throws SQLException {
        String sql = """
            INSERT INTO DEN_USUARIOS (
                usu_usuario,
                usu_contrasena,
                usu_tipo,
                emp_id
            ) VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuUsuario());
            stmt.setString(2, usuario.getUsuContrasena());
            stmt.setString(3, String.valueOf(usuario.getUsuTipo()));
            stmt.setInt(4, usuario.getEmpId());
            stmt.executeUpdate();
        }
    }

    /* =========================
       OBTENER POR ID
       ========================= */
    public Usuario obtenerPorId(int usuId) throws SQLException {
        String sql = "SELECT * FROM DEN_USUARIOS WHERE usu_id = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }

    /* =========================
       LISTAR TODOS
       ========================= */
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_USUARIOS ORDER BY usu_id ASC";

        try (Connection conn = ConexionDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    /* =========================
       MAPEO
       ========================= */
    private Usuario mapearResultSet(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("usu_id"),
                rs.getString("usu_usuario"),
                rs.getString("usu_contrasena"),
                rs.getString("usu_tipo").charAt(0),
                rs.getInt("emp_id")
        );
    }

    /* =========================
       VALIDAR USUARIO POR NOMBRE
       ========================= */
    public boolean existeUsuario(String usuario) throws SQLException {
        String sql = "SELECT 1 FROM DEN_USUARIOS WHERE usu_usuario = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    /* =========================
       VALIDAR USUARIO POR EMPLEADO
       ========================= */
    public boolean existeUsuarioPorEmpleado(int empId) throws SQLException {
        String sql = "SELECT 1 FROM DEN_USUARIOS WHERE emp_id = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    /* =========================
       ACTUALIZAR
       ========================= */
    public void actualizar(Usuario usuario) throws SQLException {
        String sql = """
            UPDATE DEN_USUARIOS SET
                usu_usuario = ?,
                usu_contrasena = ?,
                usu_tipo = ?,
                emp_id = ?
            WHERE usu_id = ?
        """;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuUsuario());
            stmt.setString(2, usuario.getUsuContrasena());
            stmt.setString(3, String.valueOf(usuario.getUsuTipo()));
            stmt.setInt(4, usuario.getEmpId());
            stmt.setInt(5, usuario.getUsuId());
            stmt.executeUpdate();
        }
    }

    /* =========================
       ELIMINAR
       ========================= */
    public void eliminar(int usuId) throws SQLException {
        String sql = "DELETE FROM DEN_USUARIOS WHERE usu_id = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuId);
            stmt.executeUpdate();
        }
    }

    /* =========================
       AUTENTICAR LOGIN
       ========================= */
    public Usuario autenticar(String usuario, String contrasenaHash) throws SQLException {
        String sql = """
            SELECT * FROM DEN_USUARIOS
            WHERE usu_usuario = ? AND usu_contrasena = ?
        """;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasenaHash);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }
}
