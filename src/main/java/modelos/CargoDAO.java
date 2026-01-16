/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import modelos.Cargo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    public void insertar(Cargo cargo) throws SQLException {
        String sql = "INSERT INTO DEN_CARGOS (car_id, car_nombre) VALUES (?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cargo.getCarId());
            stmt.setString(2, String.valueOf(cargo.getCarNombre()));
            stmt.executeUpdate();
        }
    }

    public Cargo obtenerPorId(String carId) throws SQLException {
        String sql = "SELECT * FROM DEN_CARGOS WHERE car_id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearResultSet(rs);
            }
        }
        return null;
    }

    public List<Cargo> listarTodos() throws SQLException {
        List<Cargo> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEN_CARGOS";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    private Cargo mapearResultSet(ResultSet rs) throws SQLException {
        return new Cargo(
            rs.getString("car_id"),
            rs.getString("car_nombre").charAt(0)
        );
    }
}