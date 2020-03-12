package Clientes.Controlador;

import Clientes.Aplicacion.ModeloVistaControladorLogin;
import Clientes.Modelo.ConsultasLogin;
import Vista.VistaLogin;

public class AppMain {
    
    public static void main(String[] args) {
        ModeloVistaControladorLogin c = new ModeloVistaControladorLogin(new ConsultasLogin(), new VistaLogin());
        c.inicio();
    }
}
