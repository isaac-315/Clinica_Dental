/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.SQLException;
import java.util.List;
import modelos.Cliente;
import modelos.ClienteDAO;

/**
 *
 * @author USUARO_PC
 */
public class ClienteControl {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public List<Cliente> listarTodos() throws SQLException {
        return clienteDAO.listarTodos();
    }

    public Cliente obtenerPorId(int id) throws SQLException {
        return clienteDAO.obtenerPorId(id);
    }

    public void guardar(Cliente cliente) throws SQLException {
        clienteDAO.insertar(cliente);
    }

    public void eliminar(int cliId) throws SQLException {
        clienteDAO.eliminar(cliId);
    }

    public void actualizar(Cliente cliente) throws SQLException {
        clienteDAO.actualizar(cliente);
    }

    public Cliente obtenerPorCedula(String cedula) throws SQLException {
        return clienteDAO.obtenerPorCedula(cedula);
    }
}
