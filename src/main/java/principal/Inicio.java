package principal;

import vistas.Login;

public class Inicio {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setLocationRelativeTo(null); // centrar ventana
            login.setVisible(true);
        });
    }
}
