/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author USUARO_PC
 */
import java.util.Date;

public class FacturaCabecera {
    private String facId;
    private Date facFechaEmision;
    private double facSubtotal;
    private double facIva;
    private double facTotal;
    private int cliId; // FK a Cliente
    private int usuId; // FK a Usuario

    public FacturaCabecera() {}

    public FacturaCabecera(String facId, Date facFechaEmision, double facSubtotal,
                           double facIva, double facTotal, int cliId, int usuId) {
        this.facId = facId;
        this.facFechaEmision = facFechaEmision;
        this.facSubtotal = facSubtotal;
        this.facIva = facIva;
        this.facTotal = facTotal;
        this.cliId = cliId;
        this.usuId = usuId;
    }

    // Getters y Setters
    public String getFacId() { return facId; }
    public void setFacId(String facId) { this.facId = facId; }

    public Date getFacFechaEmision() { return facFechaEmision; }
    public void setFacFechaEmision(Date facFechaEmision) { this.facFechaEmision = facFechaEmision; }

    public double getFacSubtotal() { return facSubtotal; }
    public void setFacSubtotal(double facSubtotal) { this.facSubtotal = facSubtotal; }

    public double getFacIva() { return facIva; }
    public void setFacIva(double facIva) { this.facIva = facIva; }

    public double getFacTotal() { return facTotal; }
    public void setFacTotal(double facTotal) { this.facTotal = facTotal; }

    public int getCliId() { return cliId; }
    public void setCliId(int cliId) { this.cliId = cliId; }

    public int getUsuId() { return usuId; }
    public void setUsuId(int usuId) { this.usuId = usuId; }
}