package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public void insertar(Empleado empleado) throws SQLException {
        String sql = """
            INSERT INTO DEN_EMPLEADOS (
                emp_cedula,
                emp_nombre,
                emp_apellido,
                emp_direccion,
                emp_telefono_convencional,
                emp_telefono_celular,
                emp_correo_electronico
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getEmpCedula());
            stmt.setString(2, empleado.getEmpNombre());
            stmt.setString(3, empleado.getEmpApellido());
            stmt.setString(4, empleado.getEmpDireccion());
            stmt.setString(5, empleado.getEmpTelefonoConvencional());
            stmt.setString(6, empleado.getEmpTelefonoCelular());
            stmt.setString(7, empleado.getEmpCorreoElectronico());

            stmt.executeUpdate();
        }
    }

    public void eliminar(int empId) throws SQLException {
        String sql = "DELETE FROM DEN_EMPLEADOS WHERE emp_id = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            stmt.executeUpdate();
        }
    }

    public Empleado obtenerPorId(int empId) throws SQLException {
        String sql = "SELECT * FROM DEN_EMPLEADOS WHERE emp_id = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    public Empleado obtenerPorCedula(String cedula) throws SQLException {
        String sql = "SELECT * FROM DEN_EMPLEADOS WHERE emp_cedula = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Empleado> listarTodos() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_EMPLEADOS ORDER BY emp_id";
        try (Connection conn = ConexionDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private Empleado mapearResultSet(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getInt("EMP_ID"),
                rs.getString("EMP_CEDULA"),
                rs.getString("EMP_NOMBRE"),
                rs.getString("EMP_APELLIDO"),
                rs.getString("EMP_DIRECCION"),
                rs.getString("EMP_TELEFONO_CONVENCIONAL"),
                rs.getString("EMP_TELEFONO_CELULAR"),
                rs.getString("EMP_CORREO_ELECTRONICO")
        );
    }

    // En EmpleadoDAO.java
    public void actualizar(Empleado empleado) throws SQLException {
        String sql = """
        UPDATE DEN_EMPLEADOS SET
            emp_cedula = ?,
            emp_nombres = ?,
            emp_apellidos = ?,
            emp_direccion = ?,
            emp_telefono_convencional = ?,
            emp_telefono_celular = ?,
            emp_correo_electronico = ?
        WHERE emp_id = ?
        """;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getEmpCedula());
            stmt.setString(2, empleado.getEmpNombre());
            stmt.setString(3, empleado.getEmpApellido());
            stmt.setString(4, empleado.getEmpDireccion());
            stmt.setString(5, empleado.getEmpTelefonoConvencional());
            stmt.setString(6, empleado.getEmpTelefonoCelular());
            stmt.setString(7, empleado.getEmpCorreoElectronico());
            stmt.setInt(8, empleado.getEmpId());

            stmt.executeUpdate();
        }
    }
}
