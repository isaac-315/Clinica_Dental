// src/main/java/controladores/SesionUsuario.java
package controladores;

import modelos.Usuario;

public class SesionUsuario {

    private static Usuario usuarioActual = null;

    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static boolean esAdministrador() {
        return usuarioActual != null && usuarioActual.getUsuTipo() == 'A';
    }

    public static boolean esEmpleadoGeneral() {
        return usuarioActual != null && usuarioActual.getUsuTipo() == 'E';
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
