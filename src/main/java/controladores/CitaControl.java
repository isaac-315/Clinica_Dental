/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.SQLException;
import java.util.List;
import modelos.Cita;
import modelos.CitaDAO;

/**
 *
 * @author USUARO_PC
 */
public class CitaControl {

    private final CitaDAO citaDAO = new CitaDAO();

    public List<Cita> listarTodos() throws SQLException {
        return citaDAO.listarTodos();
    }

    public Cita obtenerPorId(int id) throws SQLException {
        return citaDAO.obtenerPorId(id);
    }

    public void guardar(Cita cita) throws SQLException {
        citaDAO.insertar(cita);
    }
}
