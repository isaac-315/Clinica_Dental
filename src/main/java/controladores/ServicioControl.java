/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.SQLException;
import java.util.List;
import modelos.Servicio;
import modelos.ServicioDAO;

/**
 *
 * @author USUARO_PC
 */
public class ServicioControl {

    private final ServicioDAO servicioDAO = new ServicioDAO();

    public List<Servicio> listarTodos() throws SQLException {
        return servicioDAO.listarTodos();
    }

    public Servicio obtenerPorId(int id) throws SQLException {
        return servicioDAO.obtenerPorId(id);
    }

    public void guardar(Servicio servicio) throws SQLException {
        servicioDAO.insertar(servicio);
    }
    
    public void actualizar(Servicio servicio) throws SQLException {
    servicioDAO.actualizar(servicio);
}
}
