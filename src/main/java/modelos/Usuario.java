/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author USUARO_PC
 */
public class Usuario {
    private int usuId;
    private String usuUsuario;
    private String usuContrasena;
    private char usuTipo; // 'A' = Administrador, 'E' = Empleado
    private int empId;

    public Usuario() {}

    public Usuario(int usuId, String usuUsuario, String usuContrasena, char usuTipo, int empId) {
        this.usuId = usuId;
        this.usuUsuario = usuUsuario;
        this.usuContrasena = usuContrasena;
        this.usuTipo = usuTipo;
        this.empId = empId;
    }

    // Getters y Setters
    public int getUsuId() { return usuId; }
    public void setUsuId(int usuId) { this.usuId = usuId; }

    public String getUsuUsuario() { return usuUsuario; }
    public void setUsuUsuario(String usuUsuario) { this.usuUsuario = usuUsuario; }

    public String getUsuContrasena() { return usuContrasena; }
    public void setUsuContrasena(String usuContrasena) { this.usuContrasena = usuContrasena; }

    public char getUsuTipo() { return usuTipo; }
    public void setUsuTipo(char usuTipo) { this.usuTipo = usuTipo; }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getTipoTexto() {
        return usuTipo == 'A' ? "Administrador" : "Empleado";
    }
}