package modelos;

public class Empleado {

    private int empId;
    private String empCedula;
    private String empNombre;
    private String empApellido;
    private String empDireccion;
    private String empTelefonoConvencional;
    private String empTelefonoCelular;
    private String empCorreoElectronico;

    public Empleado() {
    }

    // Constructor SIN ID (para INSERT)
    public Empleado(String empCedula, String empNombre, String empApellido,
                    String empDireccion, String empTelefonoConvencional,
                    String empTelefonoCelular, String empCorreoElectronico) {
        this.empCedula = empCedula;
        this.empNombre = empNombre;
        this.empApellido = empApellido;
        this.empDireccion = empDireccion;
        this.empTelefonoConvencional = empTelefonoConvencional;
        this.empTelefonoCelular = empTelefonoCelular;
        this.empCorreoElectronico = empCorreoElectronico;
    }
    
    // Constructor CON ID (para SELECT)
    public Empleado(int id, String empCedula, String empNombre, String empApellido,
                    String empDireccion, String empTelefonoConvencional,
                    String empTelefonoCelular, String empCorreoElectronico) {
        this.empId = id; // ← CORREGIDO: era "emp_Id"
        this.empCedula = empCedula;
        this.empNombre = empNombre;
        this.empApellido = empApellido;
        this.empDireccion = empDireccion;
        this.empTelefonoConvencional = empTelefonoConvencional;
        this.empTelefonoCelular = empTelefonoCelular;
        this.empCorreoElectronico = empCorreoElectronico;
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
}