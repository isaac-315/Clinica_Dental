package controladores;

import java.sql.SQLException;
import java.util.List;
import modelos.Usuario;
import modelos.UsuarioDAO;

public class UsuarioControl {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Usuario> listarTodos() throws SQLException {
        return usuarioDAO.listarTodos();
    }

    public Usuario obtenerPorId(int id) throws SQLException {
        return usuarioDAO.obtenerPorId(id);
    }

    // AQUÍ está la lógica correcta
    public void guardar(Usuario usuario) throws Exception {

        if (usuarioDAO.existeUsuario(usuario.getUsuUsuario())) {
            throw new Exception("El nombre de usuario ya existe.");
        }

        if (usuarioDAO.existeUsuarioPorEmpleado(usuario.getEmpId())) {
            throw new Exception("Este empleado ya tiene un usuario asignado.");
        }

        usuarioDAO.insertar(usuario);
    }

    public void actualizar(Usuario usuario) throws SQLException {
        usuarioDAO.actualizar(usuario);
    }
}
