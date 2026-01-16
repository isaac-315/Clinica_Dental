/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.SQLException;
import java.util.List;
import modelos.FacturaCabecera;
import modelos.FacturaDAO;
import modelos.FacturaDetalle;

/**
 *
 * @author USUARO_PC
 */
public class FacturaControl {

    private final FacturaDAO facturaDAO = new FacturaDAO(); // El coordinador

    public void guardarFactura(FacturaCabecera cabecera, List<FacturaDetalle> detalles) throws SQLException {
        facturaDAO.guardarFactura(cabecera, detalles);
    }
}
