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

public class Cita {
    private int citaId;
    private Date citaFechaHora;
    private char citaEstado; // 'P' = Pendiente, 'C' = Cancelada
    private int cliId;       // FK a Cliente
    private int empId;       // FK a Empleado

    public Cita() {}

    public Cita(int citaId, Date citaFechaHora, char citaEstado, int cliId, int empId) {
        this.citaId = citaId;
        this.citaFechaHora = citaFechaHora;
        this.citaEstado = citaEstado;
        this.cliId = cliId;
        this.empId = empId;
    }

    // Getters y Setters
    public int getCitaId() { return citaId; }
    public void setCitaId(int citaId) { this.citaId = citaId; }

    public Date getCitaFechaHora() { return citaFechaHora; }
    public void setCitaFechaHora(Date citaFechaHora) { this.citaFechaHora = citaFechaHora; }

    public char getCitaEstado() { return citaEstado; }
    public void setCitaEstado(char citaEstado) { this.citaEstado = citaEstado; }

    public int getCliId() { return cliId; }
    public void setCliId(int cliId) { this.cliId = cliId; }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getEstadoTexto() {
        return citaEstado == 'P' ? "Pendiente" : "Cancelada";
    }
}