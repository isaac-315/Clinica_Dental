/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.SQLException;
import java.util.List;
import modelos.Empleado;
import modelos.EmpleadoDAO;

/**
 *
 * @author USUARO_PC
 */
public class EmpleadoControl {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public List<Empleado> listarTodos() throws SQLException {
        return empleadoDAO.listarTodos();
    }

    public Empleado obtenerPorId(int id) throws SQLException {
        return empleadoDAO.obtenerPorId(id);
    }

    public void guardar(Empleado empleado) throws SQLException {
        empleadoDAO.insertar(empleado);
    }
}
