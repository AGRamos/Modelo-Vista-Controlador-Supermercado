package Clientes.Modelo;


import java.sql.Date;


public class Reponedores {
    
    private String DNI, NOMBRE;
    private Date fecha;

    public Reponedores() {
    }

    public Reponedores(String DNI, String NOMBRE, Date fecha) {
        this.DNI = DNI;
        this.NOMBRE = NOMBRE;
        this.fecha = fecha;
    }
    
    

    public String getDNI() {
        return DNI;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

      

     
}