package Clientes.Modelo;



public class Pasillos {
    
    private int ID_PAS;
    private String DESCRIPCION;

    public Pasillos(int ID_PAS, String DESCRIPCION) {
        this.ID_PAS = ID_PAS;
        this.DESCRIPCION = DESCRIPCION;
    }

    public Pasillos() {
    }

    public int getID_PAS() {
        return ID_PAS;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setID_PAS(int ID_PAS) {
        this.ID_PAS = ID_PAS;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
    
    
    
}