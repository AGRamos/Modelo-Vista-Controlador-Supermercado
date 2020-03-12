package Clientes.Modelo;


import java.sql.Date;


public class ReponenPasillos {
    
    private int ID_MAT;
    private String REP_DNI;
    private int CANTIDAD;
    private Date fecha;

    public ReponenPasillos(int ID_MAT, String REP_DNI, int CANTIDAD, Date fecha) {
        this.ID_MAT = ID_MAT;
        this.REP_DNI = REP_DNI;
        this.CANTIDAD = CANTIDAD;
        this.fecha = fecha;
    }
    
    public ReponenPasillos() {
    }

    public int getID_MAT() {
        return ID_MAT;
    }

    public String getREP_DNI() {
        return REP_DNI;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setID_MAT(int ID_MAT) {
        this.ID_MAT = ID_MAT;
    }

    public void setREP_DNI(String REP_DNI) {
        this.REP_DNI = REP_DNI;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    
}