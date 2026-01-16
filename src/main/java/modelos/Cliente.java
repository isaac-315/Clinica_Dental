/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author USUARO_PC
 */
public class Cliente {
    private int cliId;
    private String cliCedula;
    private String cliNombre;
    private String cliApellido;
    private String cliDireccion;
    private String cliTelefonoConvencional;
    private String cliTelefonoCelular;
    private String cliCorreoElectronico;
    private char cliEstado; // 'A' = Activo, 'I' = Inactivo

    public Cliente() {}

    public Cliente(int cliId, String cliCedula, String cliNombre, String cliApellido,
                   String cliDireccion, String cliTelefonoConvencional,
                   String cliTelefonoCelular, String cliCorreoElectronico, char cliEstado) {
        this.cliId = cliId;
        this.cliCedula = cliCedula;
        this.cliNombre = cliNombre;
        this.cliApellido = cliApellido;
        this.cliDireccion = cliDireccion;
        this.cliTelefonoConvencional = cliTelefonoConvencional;
        this.cliTelefonoCelular = cliTelefonoCelular;
        this.cliCorreoElectronico = cliCorreoElectronico;
        this.cliEstado = cliEstado;
    }

    // Getters y Setters
    public int getCliId() { return cliId; }
    public void setCliId(int cliId) { this.cliId = cliId; }

    public String getCliCedula() { return cliCedula; }
    public void setCliCedula(String cliCedula) { this.cliCedula = cliCedula; }

    public String getCliNombre() { return cliNombre; }
    public void setCliNombre(String cliNombre) { this.cliNombre = cliNombre; }

    public String getCliApellido() { return cliApellido; }
    public void setCliApellido(String cliApellido) { this.cliApellido = cliApellido; }

    public String getCliDireccion() { return cliDireccion; }
    public void setCliDireccion(String cliDireccion) { this.cliDireccion = cliDireccion; }

    public String getCliTelefonoConvencional() { return cliTelefonoConvencional; }
    public void setCliTelefonoConvencional(String cliTelefonoConvencional) { this.cliTelefonoConvencional = cliTelefonoConvencional; }

    public String getCliTelefonoCelular() { return cliTelefonoCelular; }
    public void setCliTelefonoCelular(String cliTelefonoCelular) { this.cliTelefonoCelular = cliTelefonoCelular; }

    public String getCliCorreoElectronico() { return cliCorreoElectronico; }
    public void setCliCorreoElectronico(String cliCorreoElectronico) { this.cliCorreoElectronico = cliCorreoElectronico; }

    public char getCliEstado() { return cliEstado; }
    public void setCliEstado(char cliEstado) { this.cliEstado = cliEstado; }

    public String getEstadoTexto() {
        return cliEstado == 'A' ? "Activo" : "Inactivo";
    }
}