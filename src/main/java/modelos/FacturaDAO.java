/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import modelos.FacturaCabecera;
import modelos.FacturaDetalle;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FacturaDAO {

    private final FacturaCabeceraDAO cabeceraDAO = new FacturaCabeceraDAO();
    private final FacturaDetalleDAO detalleDAO = new FacturaDetalleDAO();

    /**
     * Guarda una factura completa (cabecera + detalles) en una sola
     * transacción. Si algo falla, se revierte todo (rollback).
     */
    public void guardarFactura(FacturaCabecera cabecera, List<FacturaDetalle> detalles) throws SQLException {
        try (Connection conn = ConexionDB.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción

            try {
                // 1. Guardar cabecera
                cabeceraDAO.insertar(cabecera);

                // 2. Guardar cada detalle
                for (FacturaDetalle det : detalles) {
                    detalleDAO.insertar(det);
                }

                conn.commit(); // Confirmar transacción
            } catch (SQLException e) {
                conn.rollback(); // Deshacer si hay error
                throw new SQLException("Error al guardar la factura: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    /**
     * Obtiene una factura completa por su ID (cabecera + todos sus detalles).
     * Nota: Esto requiere una clase auxiliar o devolver ambos objetos por
     * separado. Aquí devolvemos solo la cabecera; los detalles se obtienen
     * aparte si se necesitan.
     */
    public FacturaCabecera obtenerCabeceraPorId(String facId) throws SQLException {
        return cabeceraDAO.obtenerPorId(facId);
    }

    /**
     * Obtiene todos los detalles asociados a una factura.
     */
    public List<FacturaDetalle> obtenerDetallesPorFactura(String facId) throws SQLException {
        return detalleDAO.obtenerPorFactura(facId);
    }

    /**
     * Lista todas las facturas (solo cabeceras, sin detalles).
     */
    public List<FacturaCabecera> listarTodasLasFacturas() throws SQLException {
        return cabeceraDAO.listarTodos();
    }
}
