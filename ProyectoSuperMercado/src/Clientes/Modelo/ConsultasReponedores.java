package Clientes.Modelo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultasReponedores extends Conexion {

    public ArrayList<Reponedores> todoReponedores() {
        Connection con = conmySQL();
        String sql = "select * from reponedores";
        ArrayList<Reponedores> reponedores = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                reponedores.add(new Reponedores(rs.getString(1), rs.getString(2), rs.getDate(3)));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasReponedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponedores;
    }

    public String insertar(Reponedores m) {
        Connection con = conmySQL();
        String sql = "insert into reponedores VALUES (?,?,?)";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, m.getDNI());
            ps.setString(2, m.getNOMBRE());
            ps.setDate(3, m.getFecha());
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Reponedor insertado.";
            } else {
                res = "Reponedor no insertado.";
            }

            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasReponedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String borrar(String cod) {
        Connection con = conmySQL();
        String sql = "DELETE FROM reponedores WHERE dni like ?";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Reponedor borrado.";
            } else {
                res = "Reponedor no borrado.";
            }
            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasReponedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String modificarUsuario(Reponedores m) {
        Connection con = conmySQL();
        String sql = "update reponedores set nombre = ?, fecha = ? where dni like ?";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(3, m.getDNI());
            ps.setString(1, m.getNOMBRE());
            ps.setDate(2, m.getFecha());
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Reponedor modificado.";
            } else {
                res = "Reponedor no insertado.";
            }
            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasReponedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public Reponedores buscarPorCod(String cod) {
        Connection con = conmySQL();
        String sql = "select * from reponedores where dni like ?";
        ResultSet rs;
        Reponedores m = new Reponedores();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            while (rs.next()) {
                m.setDNI(rs.getString(1));
                m.setNOMBRE(rs.getString(2));
                m.setFecha(rs.getDate(3));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasReponedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
}
