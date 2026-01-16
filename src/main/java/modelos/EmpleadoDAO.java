/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public void insertar(Empleado empleado) throws SQLException {
        String sql = """
            INSERT INTO DEN_EMPLEADOS (
                emp_id, emp_cedula, emp_nombre, emp_apellido, emp_direccion,
                emp_telefono_convencional, emp_telefono_celular, emp_correo_electronico,
                car_id, usu_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empleado.getEmpId());
            stmt.setString(2, empleado.getEmpCedula());
            stmt.setString(3, empleado.getEmpNombre());
            stmt.setString(4, empleado.getEmpApellido());
            stmt.setString(5, empleado.getEmpDireccion());
            stmt.setString(6, empleado.getEmpTelefonoConvencional());
            stmt.setString(7, empleado.getEmpTelefonoCelular());
            stmt.setString(8, empleado.getEmpCorreoElectronico());
            stmt.setString(9, empleado.getCarId());
            stmt.setInt(10, empleado.getUsuId());
            stmt.executeUpdate();
        }
    }

    public Empleado obtenerPorId(int empId) throws SQLException {
        String sql = "SELECT * FROM DEN_EMPLEADOS WHERE emp_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }

    public List<Empleado> listarTodos() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_EMPLEADOS";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private Empleado mapearResultSet(ResultSet rs) throws SQLException {
        return new Empleado(
            rs.getInt("emp_id"),
            rs.getString("emp_cedula"),
            rs.getString("emp_nombre"),
            rs.getString("emp_apellido"),
            rs.getString("emp_direccion"),
            rs.getString("emp_telefono_convencional"),
            rs.getString("emp_telefono_celular"),
            rs.getString("emp_correo_electronico"),
            rs.getString("car_id"),
            rs.getInt("usu_id")
        );
    }
}