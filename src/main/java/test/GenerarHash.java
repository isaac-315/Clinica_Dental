// Archivo: GenerarHash.java
package test;

import utilidades.PasswordUtil;

public class GenerarHash {
    public static void main(String[] args) {
        String password = "IaMc0103";
        String hash = PasswordUtil.hashPassword(password);
        System.out.println("Contraseña: " + password);
        System.out.println("Hash SHA-256: " + hash);
        System.out.println("Longitud: " + hash.length());
    }
}