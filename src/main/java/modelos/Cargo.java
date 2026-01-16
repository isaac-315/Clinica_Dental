/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author USUARO_PC
 */
public class Cargo {
    private String carId;
    private char carNombre; // 'O' = Odontólogo, 'A' = Administrativo

    public Cargo() {}

    public Cargo(String carId, char carNombre) {
        this.carId = carId;
        this.carNombre = carNombre;
    }

    // Getters y Setters
    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }

    public char getCarNombre() { return carNombre; }
    public void setCarNombre(char carNombre) { this.carNombre = carNombre; }

    // Método útil para mostrar nombre legible
    public String getNombreCompleto() {
        return carNombre == 'O' ? "Odontólogo" : "Administrativo";
    }
}