package Clientes.Modelo;



public class Materiales {
    
    private int ID_MAT;
    private String NOMBRE, DESCRIPCION;
    private int ID_PAS;

    public Materiales(int ID_MAT, String NOMBRE, String DESCRIPCION, int ID_PAS) {
        this.ID_MAT = ID_MAT;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
        this.ID_PAS = ID_PAS;
    }

    public Materiales() {
    }

    public int getID_MAT() {
        return ID_MAT;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public int getID_PAS() {
        return ID_PAS;
    }

    public void setID_MAT(int ID_MAT) {
        this.ID_MAT = ID_MAT;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public void setID_PAS(int ID_PAS) {
        this.ID_PAS = ID_PAS;
    }
    
    
    
}