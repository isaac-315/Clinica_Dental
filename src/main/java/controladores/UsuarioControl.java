/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.SQLException;
import java.util.List;
import modelos.Usuario;
import modelos.UsuarioDAO;

/**
 *
 * @author USUARO_PC
 */
public class UsuarioControl {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Usuario> listarTodos() throws SQLException {
        return usuarioDAO.listarTodos();
    }

    public Usuario obtenerPorId(int id) throws SQLException {
        return usuarioDAO.obtenerPorId(id);
    }

    public void guardar(Usuario usuario) throws SQLException {
        usuarioDAO.insertar(usuario);
    }

    // En controladores.UsuarioControl.java
    public boolean existeUsuario(String usuario) throws SQLException {
        return usuarioDAO.existeUsuario(usuario);
    }

    // En controladores.UsuarioControl.java
    public void actualizar(Usuario usuario) throws SQLException {
        usuarioDAO.actualizar(usuario);
    }
    
  
}
