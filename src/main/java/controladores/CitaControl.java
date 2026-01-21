package controladores;

import modelos.Cita;
import modelos.CitaDAO;
import java.sql.SQLException;
import java.util.List;

public class CitaControl {
    private CitaDAO citaDAO = new CitaDAO();

    public void guardar(Cita cita) throws SQLException {
        citaDAO.insertar(cita);
    }

    public void actualizar(Cita cita) throws SQLException {
        citaDAO.actualizar(cita);
    }

    public void eliminar(int citaId) throws SQLException {
        citaDAO.eliminar(citaId);
    }

    public Cita obtenerPorId(int citaId) throws SQLException {
        return citaDAO.obtenerPorId(citaId);
    }

    public List<Cita> listarTodos() throws SQLException {
        return citaDAO.listarTodos();
    }
}