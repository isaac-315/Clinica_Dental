/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;


import modelos.Cliente;
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

    public Cliente obtenerPorId(int cliId) throws SQLException {
        String sql = "SELECT * FROM DEN_CLIENTES WHERE cli_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_CLIENTES";
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
            rs.getInt("cli_id"),
            rs.getString("cli_cedula"),
            rs.getString("cli_nombre"),
            rs.getString("cli_apellido"),
            rs.getString("cli_direccion"),
            rs.getString("cli_telefono_convencional"),
            rs.getString("cli_telefono_celular"),
            rs.getString("cli_correo_electronico"),
            rs.getString("cli_estado").charAt(0)
        );
    }
    
    // Eliminar
public void eliminar(int cliId) throws SQLException {
    String sql = "DELETE FROM DEN_CLIENTES WHERE cli_id = ?";
    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, cliId);
        stmt.executeUpdate();
    }
}

// Actualizar
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
        stmt.setInt(9, cliente.getCliId()); // ← ¡Esto es clave!
        
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas == 0) {
            throw new SQLException("No se encontró el cliente con ID " + cliente.getCliId());
        }
    }
}
}