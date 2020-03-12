/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author tarde
 */
public class Conexion {
    
    private final String db = "proyecto", user = "ejemplo", cont = "ejemplo";
    
    public Connection conmySQL() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + db, user, cont);
            System.out.println("Conectada.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede conectar con la base de datosº");
        }
        return conexion;
    }
}
