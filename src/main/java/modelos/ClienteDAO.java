package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void insertar(Cliente cliente) throws SQLException {
        String sql = """
            INSERT INTO DEN_CLIENTES (
                cli_cedula,
                cli_nombre,
                cli_apellido,
                cli_direccion,
                cli_telefono_convencional,
                cli_telefono_celular,
                cli_correo_electronico,
                cli_estado
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getCliCedula());
            stmt.setString(2, cliente.getCliNombre());
            stmt.setString(3, cliente.getCliApellido());
            stmt.setString(4, cliente.getCliDireccion());
            stmt.setString(5, cliente.getCliTelefonoConvencional());
            stmt.setString(6, cliente.getCliTelefonoCelular());
            stmt.setString(7, cliente.getCliCorreoElectronico());
            stmt.setString(8, String.valueOf(cliente.getCliEstado()));
            
            stmt.executeUpdate();
        }
    }

    // 👇 MÉTODO ACTUALIZAR AÑADIDO
    public void actualizar(Cliente cliente) throws SQLException {
        String sql = """
            UPDATE DEN_CLIENTES SET
                cli_cedula = ?,
                cli_nombre = ?,
                cli_apellido = ?,
                cli_direccion = ?,
                cli_telefono_convencional = ?,
                cli_telefono_celular = ?,
                cli_correo_electronico = ?,
                cli_estado = ?
            WHERE cli_id = ?
            """;
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getCliCedula());
            stmt.setString(2, cliente.getCliNombre());
            stmt.setString(3, cliente.getCliApellido());
            stmt.setString(4, cliente.getCliDireccion());
            stmt.setString(5, cliente.getCliTelefonoConvencional());
            stmt.setString(6, cliente.getCliTelefonoCelular());
            stmt.setString(7, cliente.getCliCorreoElectronico());
            stmt.setString(8, String.valueOf(cliente.getCliEstado()));
            stmt.setInt(9, cliente.getCliId()); // ← ID al final para WHERE
            
            stmt.executeUpdate();
        }
    }

    public void eliminar(int cliId) throws SQLException {
        String sql = "DELETE FROM DEN_CLIENTES WHERE cli_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliId);
            stmt.executeUpdate();
        }
    }

    public Cliente obtenerPorId(int cliId) throws SQLException {
        String sql = "SELECT * FROM DEN_CLIENTES WHERE cli_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    public Cliente obtenerPorCedula(String cedula) throws SQLException {
        String sql = "SELECT * FROM DEN_CLIENTES WHERE cli_cedula = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_CLIENTES ORDER BY cli_id";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private Cliente mapearResultSet(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("CLI_ID"),
            rs.getString("CLI_CEDULA"),
            rs.getString("CLI_NOMBRE"),
            rs.getString("CLI_APELLIDO"),
            rs.getString("CLI_DIRECCION"),
            rs.getString("CLI_TELEFONO_CONVENCIONAL"),
            rs.getString("CLI_TELEFONO_CELULAR"),
            rs.getString("CLI_CORREO_ELECTRONICO"),
            rs.getString("CLI_ESTADO").charAt(0)
        );
    }
}