package Clientes.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultasLogin extends Conexion {

    public boolean compLog(String user, String pass) {
        Connection con = conmySQL();
        boolean res = false;
        String sql = "select usuario, password from usuarios where usuario like ? and password like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs;
            rs = ps.executeQuery();
            if (rs.next()) {
                res=true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
