package utilidades;

import java.security.MessageDigest;
import java.math.BigInteger;

public class PasswordUtil {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return String.format("%064x", new BigInteger(1, hash));
        } catch (Exception e) {
            throw new RuntimeException("Error al hashear contraseña", e);
        }
    }
}