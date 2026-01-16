/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author USUARO_PC
 */
public class FacturaDetalle {
    private int detId;
    private int detCantidad;
    private double detSubtotal;
    private double detIva;
    private double detTotal;
    private String facId; // FK a FacturaCabecera
    private int serId;    // FK a Servicio

    public FacturaDetalle() {}

    public FacturaDetalle(int detId, int detCantidad, double detSubtotal,
                          double detIva, double detTotal, String facId, int serId) {
        this.detId = detId;
        this.detCantidad = detCantidad;
        this.detSubtotal = detSubtotal;
        this.detIva = detIva;
        this.detTotal = detTotal;
        this.facId = facId;
        this.serId = serId;
    }

    // Getters y Setters
    public int getDetId() { return detId; }
    public void setDetId(int detId) { this.detId = detId; }

    public int getDetCantidad() { return detCantidad; }
    public void setDetCantidad(int detCantidad) { this.detCantidad = detCantidad; }

    public double getDetSubtotal() { return detSubtotal; }
    public void setDetSubtotal(double detSubtotal) { this.detSubtotal = detSubtotal; }

    public double getDetIva() { return detIva; }
    public void setDetIva(double detIva) { this.detIva = detIva; }

    public double getDetTotal() { return detTotal; }
    public void setDetTotal(double detTotal) { this.detTotal = detTotal; }

    public String getFacId() { return facId; }
    public void setFacId(String facId) { this.facId = facId; }

    public int getSerId() { return serId; }
    public void setSerId(int serId) { this.serId = serId; }
}