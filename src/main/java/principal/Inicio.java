package principal;

import vistas.VentanaLogin;

public class Inicio {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            VentanaLogin login = new VentanaLogin();
            login.setLocationRelativeTo(null); // centrar ventana
            login.setVisible(true);
        });
    }
}
