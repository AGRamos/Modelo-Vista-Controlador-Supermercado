/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author tarde
 */
public class ConsultasMateriales extends Conexion {

    public ArrayList<Materiales> todoMateriales() {
        Connection con = conmySQL();
        String sql = "select * from materiales";
        ArrayList<Materiales> materiales = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                materiales.add(new Materiales(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materiales;
    }

    public String[] combPasillos() {
        Connection con = conmySQL();
        String sql = "select descripcion from pasillos";
        ArrayList<String> combo = new ArrayList();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                combo.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        String[] res = new String[combo.size()];
        for (String c : combo) {
            res[i] = c;
            i++;
        }
        return res;
    }

    public String[] combPob() {
        Connection con = conmySQL();
        String sql = "select municipio from municipios";
        ArrayList<String> combo = new ArrayList();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                combo.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        String[] res = new String[combo.size()];
        for (String c : combo) {
            res[i] = c;
            i++;
        }
        return res;
    }

    public String[] combCom() {
        Connection con = conmySQL();
        String sql = "select comunidad from comunidades";
        ArrayList<String> combo = new ArrayList();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                combo.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        String[] res = new String[combo.size()];
        for (String c : combo) {
            res[i] = c;
            i++;
        }

        return res;
    }

    public String combComString(int cod) {
        Connection con = conmySQL();
        String sql = "select comunidad from comunidades where id = ?";
        String string = new String();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();

            while (rs.next()) {
                string = rs.getString(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return string;
    }

    public String combPobString(int cod) {
        Connection con = conmySQL();
        String sql = "select municipio from municipios where id = ?";
        String string = new String();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();

            while (rs.next()) {
                string = rs.getString(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }

        return string;
    }

    public String combPasilloString(int cod) {
        Connection con = conmySQL();
        String sql = "select descripcion from pasillos where id_pas = ?";
        String string = new String();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();

            while (rs.next()) {
                string = rs.getString(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }

        return string;
    }

    public String insertar(Materiales m) {
        Connection con = conmySQL();
        String sql = "insert into materiales VALUES (?,?,?,?)";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getID_MAT());
            ps.setString(2, m.getNOMBRE());
            ps.setString(3, m.getDESCRIPCION());
            ps.setInt(4, m.getID_PAS());
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Material insertado.";
            } else {
                res = "Material no insertado.";
            }

            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String borrar(int cod) {
        Connection con = conmySQL();
        String sql = "DELETE FROM materiales WHERE id_mat = ?";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Material borrado.";
            } else {
                res = "Material no borrado.";
            }
            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String modificarUsuario(Materiales m) {
        Connection con = conmySQL();
        String sql = "update materiales set nombre = ?, descripcion = ?, "
                + "pasillos_id_pas = ? where id_mat = ?";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, m.getNOMBRE());
            ps.setString(2, m.getDESCRIPCION());
            ps.setInt(3, m.getID_PAS());
            ps.setInt(4, m.getID_MAT());
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Material modificado.";
            } else {
                res = "Material no insertado.";
            }
            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public Materiales buscarPorCod(int cod) {
        Connection con = conmySQL();
        String sql = "select * from materiales where id_mat = ?";
        ResultSet rs;
        Materiales m = new Materiales();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            while (rs.next()) {
                m.setID_MAT(cod);
                m.setNOMBRE(rs.getString(2));
                m.setDESCRIPCION(rs.getString(3));
                m.setID_PAS(rs.getInt(4));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public int recogerEnteroCom(String comCom) {
        Connection con = conmySQL();
        String sql = "select id_pas from pasillos where descripcion like ?";
        int id = 0;
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, comCom);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int recogerIdMatMax() {
        Connection con = conmySQL();
        String sql = "select max(id_mat) from materiales";
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
            Logger.getLogger(ConsultasMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
