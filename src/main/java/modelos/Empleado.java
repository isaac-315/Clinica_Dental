/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author USUARO_PC
 */
public class Empleado {
    private int empId;
    private String empCedula;
    private String empNombre;
    private String empApellido;
    private String empDireccion;
    private String empTelefonoConvencional;
    private String empTelefonoCelular;
    private String empCorreoElectronico;
    private String carId; // FK a Cargo
    private int usuId;    // FK a Usuario

    public Empleado() {}

    public Empleado(int empId, String empCedula, String empNombre, String empApellido,
                    String empDireccion, String empTelefonoConvencional,
                    String empTelefonoCelular, String empCorreoElectronico,
                    String carId, int usuId) {
        this.empId = empId;
        this.empCedula = empCedula;
        this.empNombre = empNombre;
        this.empApellido = empApellido;
        this.empDireccion = empDireccion;
        this.empTelefonoConvencional = empTelefonoConvencional;
        this.empTelefonoCelular = empTelefonoCelular;
        this.empCorreoElectronico = empCorreoElectronico;
        this.carId = carId;
        this.usuId = usuId;
    }

    // Getters y Setters
    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getEmpCedula() { return empCedula; }
    public void setEmpCedula(String empCedula) { this.empCedula = empCedula; }

    public String getEmpNombre() { return empNombre; }
    public void setEmpNombre(String empNombre) { this.empNombre = empNombre; }

    public String getEmpApellido() { return empApellido; }
    public void setEmpApellido(String empApellido) { this.empApellido = empApellido; }

    public String getEmpDireccion() { return empDireccion; }
    public void setEmpDireccion(String empDireccion) { this.empDireccion = empDireccion; }

    public String getEmpTelefonoConvencional() { return empTelefonoConvencional; }
    public void setEmpTelefonoConvencional(String empTelefonoConvencional) { this.empTelefonoConvencional = empTelefonoConvencional; }

    public String getEmpTelefonoCelular() { return empTelefonoCelular; }
    public void setEmpTelefonoCelular(String empTelefonoCelular) { this.empTelefonoCelular = empTelefonoCelular; }

    public String getEmpCorreoElectronico() { return empCorreoElectronico; }
    public void setEmpCorreoElectronico(String empCorreoElectronico) { this.empCorreoElectronico = empCorreoElectronico; }

    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }

    public int getUsuId() { return usuId; }
    public void setUsuId(int usuId) { this.usuId = usuId; }
}