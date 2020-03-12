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

public class ConsultasPasillos extends Conexion {

    public ArrayList<Pasillos> todoPasillos() {
        Connection con = conmySQL();
        String sql = "select * from pasillos";
        ArrayList<Pasillos> pasillos = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pasillos.add(new Pasillos(rs.getInt(1), rs.getString(2)));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pasillos;
    }

    public String insertar(Pasillos m) {
        Connection con = conmySQL();
        String sql = "insert into pasillos VALUES (?,?)";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(2, m.getDESCRIPCION());
            ps.setInt(1, m.getID_PAS());
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Pasillo insertado.";
            } else {
                res = "Pasillo no insertado.";
            }

            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String borrar(int cod) {
        Connection con = conmySQL();
        String sql = "DELETE FROM pasillos WHERE id_pas = ?";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Pasillo borrado.";
            } else {
                res = "Pasillo no borrado.";
            }
            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String modificarUsuario(Pasillos m) {
        Connection con = conmySQL();
        String sql = "update materiales set descripcion = ? where id_pas = ?";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(2, m.getDESCRIPCION());
            ps.setInt(1, m.getID_PAS());
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Pasillo modificado.";
            } else {
                res = "Pasillo no insertado.";
            }
            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public Pasillos buscarPorCod(int cod) {
        Connection con = conmySQL();
        String sql = "select * from pasillos where id_pas = ?";
        ResultSet rs;
        Pasillos m = new Pasillos();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            while (rs.next()) {
                m.setDESCRIPCION(rs.getString(2));
                m.setID_PAS(rs.getInt(1));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public int recogerIdMatMax() {
        Connection con = conmySQL();
        String sql = "select max(id_pas) from pasillos";
        int id = 0;
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
