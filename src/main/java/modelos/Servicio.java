/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author USUARO_PC
 */
public class Servicio {
    private int serId;
    private String serNombre;
    private double serPrecio;
    private char serIva;      // 'S' = Sí tiene IVA, 'N' = No tiene
    private char serEstado;   // 'A' = Activo, 'I' = Inactivo

    public Servicio() {}

    public Servicio(int serId, String serNombre, double serPrecio, char serIva, char serEstado) {
        this.serId = serId;
        this.serNombre = serNombre;
        this.serPrecio = serPrecio;
        this.serIva = serIva;
        this.serEstado = serEstado;
    }

    // Getters y Setters
    public int getSerId() { return serId; }
    public void setSerId(int serId) { this.serId = serId; }

    public String getSerNombre() { return serNombre; }
    public void setSerNombre(String serNombre) { this.serNombre = serNombre; }

    public double getSerPrecio() { return serPrecio; }
    public void setSerPrecio(double serPrecio) { this.serPrecio = serPrecio; }

    public char getSerIva() { return serIva; }
    public void setSerIva(char serIva) { this.serIva = serIva; }

    public char getSerEstado() { return serEstado; }
    public void setSerEstado(char serEstado) { this.serEstado = serEstado; }

    public boolean tieneIva() { return serIva == 'S'; }
    public String getEstadoTexto() { return serEstado == 'A' ? "Activo" : "Inactivo"; }
}