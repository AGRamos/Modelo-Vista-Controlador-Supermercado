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

public class ConsultasReponenPasillos extends Conexion {

    public ArrayList<ReponenPasillos> todoStock() {
        Connection con = conmySQL();
        String sql = "select * from rep_reponen_material";
        ArrayList<ReponenPasillos> reponenPasillos = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                reponenPasillos.add(new ReponenPasillos(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4)));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponenPasillos;
    }

    public String insertar(ReponenPasillos m) {
        Connection con = conmySQL();
        String sql = "insert into rep_reponen_material VALUES (?,?,?,?)";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getID_MAT());
            ps.setString(2, m.getREP_DNI());
            ps.setInt(3, m.getCANTIDAD());
            ps.setDate(4, m.getFecha());
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Stock insertado.";
            } else {
                res = "Stock no insertado.";
            }

            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String borrar(int cod, String dni, Date fecha) {
        Connection con = conmySQL();
        String sql = "DELETE FROM rep_reponen_material WHERE materiales_id_mat = ? and reponedores_dni = ? and fecha = ?";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            ps.setString(2, dni);
            ps.setDate(3, fecha);
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Stock borrado.";
            } else {
                res = "Stock no borrado.";
            }
            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String modificarStock(ReponenPasillos m) {
        Connection con = conmySQL();
        String sql = "update rep_reponen_material set cantidad = ? where materiales_id_mat = ? and reponedores_dni = ? and fecha = ?";
        String res = new String();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getCANTIDAD());
            ps.setInt(2, m.getID_MAT());
            ps.setString(3, m.getREP_DNI());
            ps.setDate(4, m.getFecha());
            int col = ps.executeUpdate();
            if (col != 0) {
                res = "Stock modificado.";
            } else {
                res = "Stock no modificado.";
            }
            con.close();
        } catch (SQLException ex) {
            res += ex.getMessage();
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public ReponenPasillos buscarPorCod(int cod, String dni, Date fecha) {
        Connection con = conmySQL();
        String sql = "select * from rep_reponen_material where materiales_id_mat = ? and reponedores_dni like ? and fecha = ?";
        ResultSet rs;
        ReponenPasillos m = new ReponenPasillos();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            ps.setString(2, dni);
            ps.setDate(3, fecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                m.setID_MAT(rs.getInt(1));
                m.setREP_DNI(rs.getString(2));
                m.setCANTIDAD(rs.getInt(3));
                m.setFecha(rs.getDate(4));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public String[] comReponedor() {
        Connection con = conmySQL();
        String sql = "select dni from reponedores";
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
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        String[] res = new String[combo.size()];
        for (String c : combo) {
            res[i] = c;
            i++;
        }
        return res;
    }
    
    public String[] comProductos() {
        Connection con = conmySQL();
        String sql = "select nombre from materiales";
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
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        String[] res = new String[combo.size()];
        for (String c : combo) {
            res[i] = c;
            i++;
        }
        return res;
    }
    
    public int recogerEnteroComMat(String comCom) {
        Connection con = conmySQL();
        String sql = "select id_mat from materiales where nombre like ?";
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
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String recogerNomComMat(int comCom) {
        Connection con = conmySQL();
        String sql = "select nombre from materiales where id_mat = ?";
        String id = new String();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, comCom);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getString(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasReponenPasillos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
}
