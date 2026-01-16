/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "clinica_dental";
    private static final String PASSWORD = "aSdF_315";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("✅ Driver de Oracle cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: No se pudo cargar el driver de Oracle.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("🔌 Intentando conectar a la base de datos...");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("✅ ¡Conexión exitosa a Oracle!");
        return conn;
    }
}